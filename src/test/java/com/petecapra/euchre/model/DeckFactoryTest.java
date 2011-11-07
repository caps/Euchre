package com.petecapra.euchre.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Author: petecapra
 * Date: 27/09/11
 */
public class DeckFactoryTest {

	@Test
	public void createReturnsCorrectDeck() {

		// create new deck where lowest card is seven
		Deck deck = DeckFactory.create(FaceValue.SEVEN);

		// verify the size of the deck
		assertThat(deck.getCards().size(), equalTo(32));

		// verify that the deck is correct
		for(Card card : deck.getCards()) {
			assertThat(card.getFaceValue().getValue() >= FaceValue.SEVEN.getValue(), equalTo(true));
		}

	}

}
