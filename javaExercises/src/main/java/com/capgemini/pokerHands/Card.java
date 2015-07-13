package com.capgemini.pokerHands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Class which represents one single card. Have fields for value and kind of
 * car.
 * 
 * @author MATSUS
 *
 */
public class Card implements Comparator<Card> {
	/**
	 * List which help in converting values between char and int representations
	 */
	private static List<Character> specialValues = new ArrayList<Character>(
			Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'));
	/**
	 * contains value of Card. Default -1, what mean that value is wrong
	 */
	private int value = -1;
	/**
	 * contains color of Card. Default 'X' (wrong card). Allowed values: H, C,
	 * S, D
	 */
	private char kind = 'X';

	/**
	 * Constructor from string
	 * 
	 * @param str
	 *            string with format VK where V-Value, K-Kind
	 */
	public Card(String str) {
		setKind(str.charAt(1));
		setValue(charToInt(str.charAt(0)));
	}

	/**
	 * Default constructor. Using when Comparator<T> create new object
	 */
	public Card() {
	}

	/**
	 * getter of value
	 * 
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * setter of value
	 * 
	 * @param value
	 */
	private void setValue(int value) {
		this.value = value;
	}

	/**
	 * getter of color
	 * 
	 * @return
	 */
	public char getKind() {
		return kind;
	}

	/**
	 * setter of color
	 * 
	 * @param kind
	 */
	private void setKind(char kind) {
		this.kind = kind;
	}

	/**
	 * char to int converter
	 * 
	 * @param c
	 *            char from set: 123456789TJQKA
	 * @return index int representation of card's value or -1 if not found
	 */
	private int charToInt(char c) {
		return specialValues.indexOf(c);
	}

	/**
	 * int to char converter
	 * 
	 * @param val
	 * @return char representation of val
	 */
	private char intToChar(int val) {
		return specialValues.get(val);
	}

	@Override
	public String toString() {
		return "" + intToChar(getValue()) + "" + kind;
	}

	/**
	 * using by comparator to compare 2 cards: if 2 cards have the same value
	 * then their colors are comapared comparing colors doesn't matter in poker,
	 * but is useful when checking if 2 cards are equal (for example in TreeSet
	 * they have to be different) From that reason comparing colors allows to
	 * using collections with cards as unique key. This is allowed because in
	 * one suit every card is unique
	 */
	public int compare(Card o1, Card o2) {
		return (o1.getValue() - o2.getValue() != 0) ? o1.getValue() - o2.getValue() : o1.getKind() - o2.getKind();
	}

}
