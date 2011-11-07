package com.petecapra.euchre.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: petecapra
 * Date: 27/09/11
 */
public class DeckFactory {

	public static Deck create(FaceValue startingFaceValue) {

		List<Card> cards = new ArrayList<Card>();

		// add cards always used
		cards.addAll(Arrays.asList(Card.NINE_HEARTS, Card.TEN_HEARTS, Card.JACK_HEARTS, Card.QUEEN_HEARTS, Card.KING_HEARTS, Card.ACE_HEARTS));
		cards.addAll(Arrays.asList(Card.NINE_DIAMONDS, Card.TEN_DIAMONDS, Card.JACK_DIAMONDS, Card.QUEEN_DIAMONDS, Card.KING_DIAMONDS, Card.ACE_DIAMONDS));
		cards.addAll(Arrays.asList(Card.NINE_CLUBS, Card.TEN_CLUBS, Card.JACK_CLUBS, Card.QUEEN_CLUBS, Card.KING_CLUBS, Card.ACE_CLUBS));
		cards.addAll(Arrays.asList(Card.NINE_SPADES, Card.TEN_SPADES, Card.JACK_SPADES, Card.QUEEN_SPADES, Card.KING_SPADES, Card.ACE_SPADES));

		// if there is a starting value of seven
		if (startingFaceValue == FaceValue.SEVEN) {
			cards.addAll(Arrays.asList(Card.SEVEN_HEARTS, Card.EIGHT_HEARTS));
			cards.addAll(Arrays.asList(Card.SEVEN_DIAMONDS, Card.EIGHT_DIAMONDS));
			cards.addAll(Arrays.asList(Card.SEVEN_CLUBS, Card.EIGHT_CLUBS));
			cards.addAll(Arrays.asList(Card.SEVEN_SPADES, Card.EIGHT_SPADES));
		}

		return new Deck(cards);
	}

}
