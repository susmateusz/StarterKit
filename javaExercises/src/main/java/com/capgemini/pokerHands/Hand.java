package com.capgemini.pokerHands;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class Hand implements Comparable<Hand> {
	// TODO Comments
	// Constructor of TreeSet requires Comparator, so since class Card
	// implements comparator, an instance of this class have to be used in this
	// constructor
	private TreeSet<Card> set = new TreeSet<Card>(new Card());
	private Integer rankCard;
	private Integer rankSecondCard;
	/**
	 * actual highest rank
	 */
	private Rank rank = Rank.EMPTY;
	/**
	 * kickers are card which are not in any rank, but when ranks are equal they
	 * are compared with enemy's kickers
	 */
	public List<Integer> kickers = new ArrayList<Integer>();

	public Hand() {

	}

	/**
	 * args is list of strings with cards
	 * 
	 * @param args
	 */
	public Hand(String... args) {
		for (String s : args)
			addCard(new Card(s));
		findRank();
	}

	public Rank getRank() {
		return rank;
	}

	/**
	 * set rank
	 * 
	 * @param rank
	 */
	public void setRank(Rank rank) {
		// System.out.println(this.rank + " " + rank);
		this.rank = (this.rank.compareTo(rank) <= -1 || rank == null) ? rank : this.rank;
	}

	/**
	 * set rank and the rank card
	 * 
	 * @param rank
	 * @param rankValue
	 */
	public void setRank(Rank rank, Integer rankValue) {
		if (this.rank.compareTo(rank) <= -1 || rank == null) {
			this.rank = rank;
			setRankCard(rankValue);
		}
	}

	public void addCard(Card card) {
		if (set.size() <= 5)
			set.add(card);
	}

	@Override
	public String toString() {
		return "" + set;
	}

	public Integer getRankCard() {
		return rankCard;
	}

	public void setRankCard(Integer rankValue) {
		this.rankCard = rankValue;
	}

	public Integer getRankSecondCard() {
		return rankSecondCard;
	}

	public void setRankSecondCard(Integer rankSecondCard) {
		this.rankSecondCard = rankSecondCard;
	}

	/**
	 * clears hand
	 */
	public void clear() {
		set.clear();
		rank = Rank.EMPTY;
		setRankCard(null);
		setRankSecondCard(null);
		kickers = new ArrayList<Integer>();

	}

	/**
	 * flushes are sets which cards of different values: straight, flush,
	 * straight flush, royal flush
	 */
	public void findFlushes() {
		// Checking order of values
		boolean order = true;
		// Starting from first Card in set
		int value = set.first().getValue();
		// Checking if cards are in ascending order with no gaps
		for (Card card : set)
			if (card == set.first())
				continue;
			else if (card.getValue() == value + 1)
				value += 1;
			else
				order = false;
		// Checking if every card is in the same color
		boolean equalKind = true;
		char kind = set.first().getKind();
		for (Card card : set)
			if (card.getKind() != kind)
				equalKind = false;
		// Checking if highest card is Ace
		boolean ace = (set.last().getValue() == 14);
		// Determining flush
		if (ace && equalKind && order) {
			setRank(Rank.ROYAL_FLUSH);
		} else if (equalKind && order) {
			setRank(Rank.STRAIGHT_FLUSH);
		} else if (equalKind) {
			setRank(Rank.FLUSH);
		} else if (order) {
			setRank(Rank.STRAIGHT);
		} else
			setRank(Rank.EMPTY);
	}

	/**
	 * Looking for values which appears multiple times in hand
	 */
	public void findPairs() {
		// Copy of original set
		TreeSet<Card> tmp = new TreeSet<Card>(set);
		// Map of counted carts, where card instance is a key and the value is
		// its frequency in set
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		// Moving cards from set to map and computing frequency
		while (!tmp.isEmpty()) {
			Integer first = tmp.first().getValue();
			if (map.containsKey(first))
				map.put(first, map.get(first) + 1);
			else
				map.put(first, 1);
			tmp.pollFirst();
		}
		// for (Integer key : map.keySet())
		// System.out.println(key + " => " + map.get(key));
		// Checking rank for every element of set
		for (Integer key1 : map.keySet()) {
			if (map.get(key1) == 4) {
				// Found Four of a Kind
				setRank(Rank.FOUR_OF_KIND, key1);

			} else if (map.get(key1) == 3) {
				// Found Three of a Kind or Full
				for (Integer key2 : map.keySet()) {
					if (map.get(key2) == 2) {
						// Found Full
						setRank(Rank.FULL, key1);
						if (getRank() == Rank.FULL)
							setRankSecondCard(key2);
					} else
						// Found Three of a Kind
						setRank(Rank.THREE_OF_KIND, key1);
				}
			} else if (map.get(key1) == 2) {
				// Found one or two Pairs
				for (Integer key2 : map.keySet())
					if (map.get(key2) == 2 && key1 != key2) {
						setRank(Rank.TWO_PAIRS, Math.max(key1, key2));
						if (getRank() == Rank.TWO_PAIRS)
							setRankSecondCard(Math.min(key1, key2));
					}
				setRank(Rank.ONE_PAIR, key1);
			} else {
				// No other options, the best is the highest card
				setRank(Rank.HIGH_CARD, null);
			}
		}
	}

	/**
	 * finds order of cards: if exists, add rank card. if exists, add second
	 * rank card. add descending remainders cards
	 */
	private void findPriorityOfKickers() {
		if (getRank() != Rank.HIGH_CARD && getRankCard() != null)
			kickers.add(getRankCard());
		if ((getRank() == Rank.FULL || getRank() == Rank.TWO_PAIRS) && getRankSecondCard() != null)
			kickers.add(getRankSecondCard());
		for (Card c : set.descendingSet())
			if (getRankCard() == null || (getRankCard() != null && c.getValue() != getRankCard()))
				if (getRankSecondCard() == null || (getRankSecondCard() != null && c.getValue() != getRankSecondCard()))
					kickers.add(c.getValue());
	}

	public void findRank() {
		findPairs();
		if (getRank() == Rank.HIGH_CARD) {
			findFlushes();
		}
		findPriorityOfKickers();
	}

	public int compareTo(Hand o) {
		if (this.getRank().getValue() - o.getRank().getValue() != 0)
			return this.getRank().getValue() - o.getRank().getValue();
		int i = 0;
		while (this.kickers.get(i) - o.kickers.get(i) == 0)
			i++;
		return this.kickers.get(i) - o.kickers.get(i);
	}

}
