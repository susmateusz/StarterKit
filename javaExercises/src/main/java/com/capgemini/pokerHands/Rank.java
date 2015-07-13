package com.capgemini.pokerHands;

/*
 * enum for ranks of cards. Ordered ascending from lowest ranks to highest
 */
public enum Rank {
	EMPTY(0), HIGH_CARD(1), ONE_PAIR(2), TWO_PAIRS(3), THREE_OF_KIND(4), STRAIGHT(5), FLUSH(6), FULL(7), FOUR_OF_KIND(
			8), STRAIGHT_FLUSH(9), ROYAL_FLUSH(10);

	/**
	 * field which keeps the value of rank
	 */
	private int value;

	/**
	 * constructor which create rank
	 * @param value weight of the rank
	 */
	Rank(int value) {
		this.value = value;
	}

	/**
	 * getter for value field
	 * @return value of enum
	 */
	public int getValue() {
		return value;
	}

//	public static Rank fromInt(int i) {
//		for (Rank r : Rank.values())
//			if (r.getValue() == i)
//				return r;
//		return null;
//	}
}
