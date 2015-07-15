package com.capgemini.pokerHands;

public enum CardColor {
	HEART('H'), DIAMOND('D'), CLUB('C'), SPADE('S');

	Character color;
	private CardColor(char color) {
		this.color = color;
	}
	
	public Character getColor(){
		return this.color;
	}
	
	public static CardColor fromChar(char color){
		for(CardColor card : CardColor.values())
			if(card.getColor() == color)
				return card;
		return null;
	}
	
	@Override
	public String toString(){
		return getColor().toString();
	}
}
