package com.petecapra.euchre.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Author: petecapra
 * Date: 26/09/11
 */
public class SuitTest {

	@Test
	public void suitsHaveCorrectColours() {
		assertThat(Suit.HEARTS.getColour(), equalTo(Colour.RED));
		assertThat(Suit.DIAMONDS.getColour(), equalTo(Colour.RED));
		assertThat(Suit.CLUBS.getColour(), equalTo(Colour.BLACK));
		assertThat(Suit.SPADES.getColour(), equalTo(Colour.BLACK));
	}

}
