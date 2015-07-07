package com.capgemini.pokerHands;

public enum Rank {
	// TODO Comments
	EMPTY(0), HIGH_CARD(1), ONE_PAIR(2), TWO_PAIRS(3), THREE_OF_KIND(4), STRAIGHT(5), FLUSH(6), FULL(7), FOUR_OF_KIND(8), STRAIGHT_FLUSH(9), ROYAL_FLUSH(10);

	private int value;

	Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static Rank fromInt(int i){
		for(Rank r : Rank.values())
			if (r.getValue() == i)
				return r;
		return null;
	}
}
