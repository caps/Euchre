package com.petecapra.euchre.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Author: petecapra
 * Date: 26/09/11
 */
public class CardTest {

	@Test
	public void sevenBeatsEightSameSuit() {
		assertThat(Card.SEVEN_HEARTS.beats(Card.EIGHT_HEARTS, Suit.CLUBS, Suit.HEARTS), equalTo(false));
		assertThat(Card.EIGHT_HEARTS.beats(Card.SEVEN_HEARTS, Suit.CLUBS, Suit.HEARTS), equalTo(true));
	}

	@Test
	public void kingBeatsQueenSameSuit() {
		assertThat(Card.KING_HEARTS.beats(Card.QUEEN_HEARTS, Suit.CLUBS, Suit.HEARTS), equalTo(true));
		assertThat(Card.QUEEN_HEARTS.beats(Card.KING_HEARTS, Suit.CLUBS, Suit.HEARTS), equalTo(false));
	}

	@Test
	public void rightBowerBeatsAceSameSuit() {
		assertThat(Card.JACK_HEARTS.beats(Card.ACE_HEARTS, Suit.HEARTS, Suit.HEARTS), equalTo(true));
		assertThat(Card.ACE_HEARTS.beats(Card.JACK_HEARTS, Suit.HEARTS, Suit.HEARTS), equalTo(false));
	}

	@Test
	public void leftBowerBeatsAceSameSuit() {
		assertThat(Card.JACK_DIAMONDS.beats(Card.ACE_HEARTS, Suit.HEARTS, Suit.HEARTS), equalTo(true));
		assertThat(Card.ACE_HEARTS.beats(Card.JACK_DIAMONDS, Suit.HEARTS, Suit.HEARTS), equalTo(false));
	}

	@Test
	public void trumpBeatsNonTrump() {
		assertThat(Card.SEVEN_HEARTS.beats(Card.ACE_CLUBS, Suit.HEARTS, Suit.HEARTS), equalTo(true));
		assertThat(Card.ACE_CLUBS.beats(Card.SEVEN_HEARTS, Suit.HEARTS, Suit.HEARTS), equalTo(false));
	}

	@Test
	public void isRightBowerReturnsCorrectly() {
		assertThat(Card.JACK_HEARTS.isRightBower(Suit.HEARTS), equalTo(true));
		assertThat(Card.JACK_DIAMONDS.isRightBower(Suit.HEARTS), equalTo(false));
		assertThat(Card.ACE_HEARTS.isRightBower(Suit.HEARTS), equalTo(false));
		assertThat(Card.JACK_HEARTS.isRightBower(Suit.CLUBS), equalTo(false));
		assertThat(Card.JACK_DIAMONDS.isRightBower(Suit.DIAMONDS), equalTo(true));
	}

	@Test
	public void cardLeadBeatsHigherNonTrumpNotSameSuit() {
		assertThat(Card.SEVEN_HEARTS.beats(Card.ACE_CLUBS, Suit.SPADES, Suit.HEARTS), equalTo(true));
		assertThat(Card.ACE_CLUBS.beats(Card.SEVEN_HEARTS, Suit.SPADES, Suit.HEARTS), equalTo(false));
	}

	@Test
	public void isLeftBowerReturnsCorrectly() {
		assertThat(Card.JACK_HEARTS.isLeftBower(Suit.HEARTS), equalTo(false));
		assertThat(Card.JACK_DIAMONDS.isLeftBower(Suit.HEARTS), equalTo(true));
		assertThat(Card.ACE_HEARTS.isLeftBower(Suit.HEARTS), equalTo(false));
		assertThat(Card.JACK_HEARTS.isLeftBower(Suit.CLUBS), equalTo(false));
		assertThat(Card.JACK_DIAMONDS.isLeftBower(Suit.DIAMONDS), equalTo(false));
	}

}
