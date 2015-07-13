package com.capgemini.pokerHands;

import java.util.Comparator;
import java.util.HashSet;
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
	private Set<Card> set = new HashSet<Card>();
	/**
	 * actual highest rank
	 */
	private Rank rank = Rank.EMPTY;
	/**
	 * sorting cards descending by frequency and later descending by values
	 */
	private Set<Entry<Integer, Integer>> setSortedByValues = new TreeSet<Entry<Integer, Integer>>(
			new Comparator<Entry<Integer, Integer>>() {

				public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
					return (o2.getValue() - o1.getValue() != 0) ? o2.getValue() - o1.getValue()
							: o2.getKey() - o1.getKey();
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

	/**
	 * returns actual rank value
	 * 
	 * @return
	 */
	public Rank getRank() {
		return rank;
	}

	/**
	 * adds card to hand
	 * 
	 * @param card
	 */
	public void addCard(Card card) {
		if (set.size() <= 5)
			set.add(card);
	}

	@Override
	public String toString() {
		return "" + set;
	}

	/**
	 * clears hand
	 */
	public void clear() {
		set.clear();
		rank = Rank.EMPTY;
	}

	/**
	 * Finds rank of cards in hand. Assumes that Hand has 5 cards.
	 */
	public void findRank() {
		// Map of counted carts with cards value as key and card frequency in
		// set as value
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		// Moving cards from set to map, computing frequency and check if
		// cards has the same color
		Iterator<Card> iter = set.iterator();
		// flag which remains true if all cards have the same color
		boolean equalKind = true;
		// default card color, changed in first iteration
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
		// sorting cards
		setSortedByValues.addAll(map.entrySet());
		// get necessary ranked cards
		Iterator<Entry<Integer, Integer>> iterator = setSortedByValues.iterator();
		Entry<Integer, Integer> firstRankCard = iterator.next();
		Entry<Integer, Integer> secondRankCard = iterator.next();
		Entry<Integer, Integer> lastRankCard = null;
		while (iterator.hasNext())
			lastRankCard = iterator.next();
		// if cards have consecutive values
		boolean consecutiveValues = false;
		// checked only if lastRankedCard was set, using only when all cards has
		// the same frequency
		if (lastRankCard != null)
			consecutiveValues = (firstRankCard.getKey() - lastRankCard.getKey() == 4);
		// Checking rank
		if (firstRankCard.getValue() == 4)
			setRank(Rank.FOUR_OF_KIND);
		else if (firstRankCard.getValue() == 3 && secondRankCard.getValue() == 2)
			setRank(Rank.FULL);
		else if (firstRankCard.getValue() == 3)
			setRank(Rank.THREE_OF_KIND);
		else if (firstRankCard.getValue() == 2 && secondRankCard.getValue() == 2)
			setRank(Rank.TWO_PAIRS);
		else if (firstRankCard.getValue() == 2)
			setRank(Rank.ONE_PAIR);
		else if (consecutiveValues && firstRankCard.getKey() == 14 && equalKind)
			setRank(Rank.ROYAL_FLUSH);
		else if (consecutiveValues && equalKind)
			setRank(Rank.STRAIGHT_FLUSH);
		else if (equalKind)
			setRank(Rank.FLUSH);
		else if (consecutiveValues)
			setRank(Rank.STRAIGHT);
		else
			setRank(Rank.HIGH_CARD);

	}

	/**
	 * Comaparing hands. Use private field setSortedByValues of both hands
	 */
	public int compareTo(Hand o) {
		// when ranks are different, the stronger wins
		if (this.getRank().getValue() - o.getRank().getValue() != 0)
			return this.getRank().getValue() - o.getRank().getValue();
		// if ranks are equals, kickers are compared
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

}
