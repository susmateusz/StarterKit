package com.capgemini.pokerHands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CardValue {
	DEUCE('2'), TREY('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'), EIGHT('8'), NINE('9'), TEN('T'), JACK(
			'J'), QUEEN('Q'), KING('K'), ACE('A');
	int value;

	/**
	 * List which help in converting values between char and int representations
	 */
	private static class Converter {
		private static final List<Character> intToChar = new ArrayList<Character>(
				Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'));
	}

	CardValue(char c) {
		value = Converter.intToChar.indexOf(c);
	}

	public int getValue() {
		return value;
	}

	
	public static CardValue fromString(Character c) {
		for (CardValue card : CardValue.values())
			if (Converter.intToChar.get(card.getValue()) == c)
				return card;
		return null;
	}
	
	@Override
	public String toString(){
		return Converter.intToChar.get(value).toString();
	}

}