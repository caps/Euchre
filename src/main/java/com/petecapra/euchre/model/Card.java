package com.petecapra.euchre.model;

/**
 * Author: petecapra
 * Date: 24/09/11
 */
public enum Card {

	ACE_HEARTS(FaceValue.ACE, Suit.HEARTS),
	KING_HEARTS(FaceValue.KING, Suit.HEARTS),
	QUEEN_HEARTS(FaceValue.QUEEN, Suit.HEARTS),
	JACK_HEARTS(FaceValue.JACK, Suit.HEARTS),
	TEN_HEARTS(FaceValue.TEN, Suit.HEARTS),
	NINE_HEARTS(FaceValue.NINE, Suit.HEARTS),
	EIGHT_HEARTS(FaceValue.EIGHT, Suit.HEARTS),
	SEVEN_HEARTS(FaceValue.SEVEN, Suit.HEARTS),
	ACE_DIAMONDS(FaceValue.ACE, Suit.DIAMONDS),
	KING_DIAMONDS(FaceValue.KING, Suit.DIAMONDS),
	QUEEN_DIAMONDS(FaceValue.QUEEN, Suit.DIAMONDS),
	JACK_DIAMONDS(FaceValue.JACK, Suit.DIAMONDS),
	TEN_DIAMONDS(FaceValue.TEN, Suit.DIAMONDS),
	NINE_DIAMONDS(FaceValue.NINE, Suit.DIAMONDS),
	EIGHT_DIAMONDS(FaceValue.EIGHT, Suit.DIAMONDS),
	SEVEN_DIAMONDS(FaceValue.SEVEN, Suit.DIAMONDS),
	ACE_CLUBS(FaceValue.ACE, Suit.CLUBS),
	KING_CLUBS(FaceValue.KING, Suit.CLUBS),
	QUEEN_CLUBS(FaceValue.QUEEN, Suit.CLUBS),
	JACK_CLUBS(FaceValue.JACK, Suit.CLUBS),
	TEN_CLUBS(FaceValue.TEN, Suit.CLUBS),
	NINE_CLUBS(FaceValue.NINE, Suit.CLUBS),
	EIGHT_CLUBS(FaceValue.EIGHT, Suit.CLUBS),
	SEVEN_CLUBS(FaceValue.SEVEN, Suit.CLUBS),
	ACE_SPADES(FaceValue.ACE, Suit.SPADES),
	KING_SPADES(FaceValue.KING, Suit.SPADES),
	QUEEN_SPADES(FaceValue.QUEEN, Suit.SPADES),
	JACK_SPADES(FaceValue.JACK, Suit.SPADES),
	TEN_SPADES(FaceValue.TEN, Suit.SPADES),
	NINE_SPADES(FaceValue.NINE, Suit.SPADES),
	EIGHT_SPADES(FaceValue.EIGHT, Suit.SPADES),
	SEVEN_SPADES(FaceValue.SEVEN, Suit.SPADES);

	private final FaceValue faceValue;
	private final Suit suit;

	Card(FaceValue faceValue, Suit suit){
		this.faceValue = faceValue;
		this.suit = suit;
	}

	public Suit getSuit() {
		return suit;
	}

	public FaceValue getFaceValue() {
		return faceValue;
	}

	public boolean beats(Card card, Suit trump, Suit lead) {

		// if this is right bower
		if (isRightBower(trump)) {
			return true;
		}

		// if other is right bower
		if (card.isRightBower(trump)) {
			return false;
		}

		// if this is the left bower
		if (isLeftBower(trump)) {
			return true;
		}

		// if other card is left bower
		if (card.isLeftBower(trump)) {
			return false;
		}

		// if this is trump and other isn't it
		if (suit == trump && card.getSuit() != trump) {
			return true;
		}

		// if other card is trump and this isn't it
		if (card.getSuit() == trump && suit != trump) {
			return false;
		}

		// if this is same as lead suit and the other isn't
		if (suit == lead && card.getSuit() != lead) {
			return true;
		}

		// if other card is same as lead suit and this isn't
		if (card.getSuit() == lead && suit != lead) {
			return false;
		}

		// does it beat it on face value
		return faceValue.beats(card.getFaceValue());

	}

	public boolean isRightBower(Suit trump) {
		return suit == trump && faceValue == FaceValue.JACK;
	}

	public boolean isLeftBower(Suit trump) {
		return suit != trump && suit.getColour() == trump.getColour() && faceValue == FaceValue.JACK;
	}

	@Override
	public String toString() {
		return faceValue + " of " + suit;
	}

}