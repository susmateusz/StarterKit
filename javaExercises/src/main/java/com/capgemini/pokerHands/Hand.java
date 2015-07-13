package com.capgemini.pokerHands;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Class which represents Hand with 5 cards
 * 
 * @author MATSUS
 *
 */
public class Hand implements Comparable<Hand> {
	// Constructor of TreeSet requires Comparator, so since class Card
	// implements comparator, an instance of this class have to be used in this
	// constructor
	/**
	 * set of cards in hand.
	 */
	private Set<Card> set = new TreeSet<Card>(new Card());
	/**
	 * rankCard is the characteristic card for some of ranks, for example in
	 * 45AKTT, T is rankCard
	 */
	private Integer rankCard = null;
	/**
	 * if FULL and TWO_PAIRS there are 2 rank cards - more and less important
	 */
	private Integer rankSecondCard = null;
	/**
	 * actual highest rank
	 */
	private Rank rank = Rank.EMPTY;
	/**
	 * sorting cards by frequency descending and later by values descending
	 */
	private Set<Entry<Integer, Integer>> setSortedByValues = new TreeSet<Entry<Integer, Integer>>(
			new Comparator<Entry<Integer, Integer>>() {

				public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
					if (o2.getValue() - o1.getValue() != 0)
						return o2.getValue() - o1.getValue();
					else
						return o2.getKey() - o1.getKey();
				}
			});

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

	public Integer getRankSecondCard() {
		return rankSecondCard;
	}

	/**
	 * clears hand
	 */
	public void clear() {
		set.clear();
		rank = Rank.EMPTY;
		setRankCard(null);
		setRankSecondCard(null);
	}

	/**
	 * Looking for values which appears multiple times in hand
	 */
	public void findRank() {
		// Map of counted carts, key is card value and value is card frequency
		// in set
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		// Moving cards from set to map and computing frequency and check if
		// cards has the same color
		boolean equalKind = true;
		Iterator<Card> iter = set.iterator();
		char kind = 'X';
		while (iter.hasNext()) {
			Card card = iter.next();
			// frequency of values
			if (map.containsKey(card.getValue()))
				map.put(card.getValue(), map.get(card.getValue()) + 1);
			else
				map.put(card.getValue(), 1);
			// checking color
			if (kind == 'X')
				kind = card.getKind();
			else if (kind != card.getKind())
				equalKind = false;
		}
		setSortedByValues.addAll(map.entrySet());
		for (Entry<Integer, Integer> k : setSortedByValues)
			System.out.println(k.getKey() + " => " + k.getValue());
		// get necessary ranked cards
		Iterator<Entry<Integer, Integer>> iterator = setSortedByValues.iterator();
		Entry<Integer, Integer> firstRankCard = iterator.next();
		Entry<Integer, Integer> secondRankCard = iterator.next();
		Entry<Integer, Integer> lastRankCard = null;
		while (iterator.hasNext())
			lastRankCard = iterator.next();
		System.out.println(lastRankCard);
		// if cards have consecutive values
		boolean consecutiveValues = false;
		System.out.println(consecutiveValues + " " + firstRankCard.getKey() + " " + equalKind);
		if (lastRankCard != null)
			consecutiveValues = (firstRankCard.getKey() - lastRankCard.getKey() == 4);
		// Checking rank for every element of set
		if (firstRankCard.getValue() == 4) {
			setRank(Rank.FOUR_OF_KIND, firstRankCard.getKey());
		} else if (firstRankCard.getValue() == 3 && secondRankCard.getValue() == 2) {
			setRank(Rank.FULL, firstRankCard.getKey());
			setRankSecondCard(secondRankCard.getKey());
		} else if (firstRankCard.getValue() == 3) {
			setRank(Rank.THREE_OF_KIND, firstRankCard.getKey());
		} else if (firstRankCard.getValue() == 2 && secondRankCard.getValue() == 2) {
			setRank(Rank.TWO_PAIRS, firstRankCard.getKey());
			setRankSecondCard(secondRankCard.getKey());
		} else if (firstRankCard.getValue() == 2) {
			setRank(Rank.ONE_PAIR, firstRankCard.getKey());
		} else if (consecutiveValues && firstRankCard.getKey() == 14 && equalKind) {
			setRank(Rank.ROYAL_FLUSH);
		} else if (consecutiveValues && equalKind) {
			setRank(Rank.STRAIGHT_FLUSH);
		} else if (equalKind) {
			setRank(Rank.FLUSH);
		} else if (consecutiveValues) {
			setRank(Rank.STRAIGHT);
		} else {
			setRank(Rank.HIGH_CARD, null);
		}
	}

	public int compareTo(Hand o) {
		if (this.getRank().getValue() - o.getRank().getValue() != 0)
			return this.getRank().getValue() - o.getRank().getValue();
		Iterator<Entry<Integer, Integer>> it1 = this.setSortedByValues.iterator();
		Iterator<Entry<Integer, Integer>> it2 = o.setSortedByValues.iterator();
		Integer kicker1 = null;
		Integer kicker2 = null;
		while (it1.hasNext() && it2.hasNext() && kicker1 == kicker2) {
			kicker1 = it1.next().getKey();
			kicker2 = it2.next().getKey();
		}
		return kicker1 - kicker2;

	}

	/**
	 * set rank
	 * 
	 * @param rank
	 */
	private void setRank(Rank rank) {
		this.rank = (this.rank.compareTo(rank) <= -1 || rank == null) ? rank : this.rank;
	}

	/**
	 * set rank and the rank card
	 * 
	 * @param rank
	 * @param rankValue
	 */
	private void setRank(Rank rank, Integer rankValue) {
		if (this.rank.compareTo(rank) <= -1 || rank == null) {
			this.rank = rank;
			setRankCard(rankValue);
		}
	}

	private void setRankCard(Integer rankValue) {
		this.rankCard = rankValue;
	}

	private void setRankSecondCard(Integer rankSecondCard) {
		this.rankSecondCard = rankSecondCard;
	}

}
