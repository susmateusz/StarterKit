package com.capgemini.pokerHands;

import java.util.Comparator;

public class Card implements Comparable<Card>, Comparator<Card> {
	private int value;
	private char kind;

	public Card(String str) {
		setKind(str.charAt(str.length() - 1));
		str = str.substring(0, str.length() - 1);
		setValue(strToInt(str));
	}
	
	public Card(){
		setKind('X');
		setValue(-1);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public char getKind() {
		return kind;
	}

	public void setKind(char kind) {
		this.kind = kind;
	}

	private int strToInt(String str) {
		if (str.equals("T"))
			return 10;
		else if (str.equals("J"))
			return 11;
		else if (str.equals("Q"))
			return 12;
		else if (str.equals("K"))
			return 13;
		else if (str.equals("A"))
			return 14;
		else
			return Integer.parseInt(str);
	}

	private String intToStr(int val) {
		if ( val == 10 )
			return "T";
		else if (val == 11)
			return "J";
		else if (val == 12)
			return "Q";
		else if (val == 13)
			return "K";
		else if (val == 14)
			return "A";
		else
			return Integer.toString(val);
	}

	@Override
	public String toString() {
		return "" + intToStr(getValue()) + "" + kind;
	}

	public int compareTo(Card o) {
		return this.getValue() - o.getValue();
	}

	public int compare(Card o1, Card o2) {
		if(o1.getValue() > o2.getValue())
			return 1;
		else if (o1.getValue() < o2.getValue())
			return -1;
		else if (o1.getKind() > o2.getKind())
			return 1;
		else if (o1.getKind() < o2.getKind())
			return -1;
		else
			return 0;
	}

}
