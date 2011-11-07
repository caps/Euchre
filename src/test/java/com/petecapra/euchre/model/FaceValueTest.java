package com.petecapra.euchre.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Author: petecapra
 * Date: 26/09/11
 */
public class FaceValueTest {

	@Test
	public void beatsReturnsCorrectly() {
		assertThat(FaceValue.ACE.beats(FaceValue.KING), equalTo(true));
		assertThat(FaceValue.KING.beats(FaceValue.QUEEN), equalTo(true));
		assertThat(FaceValue.QUEEN.beats(FaceValue.JACK), equalTo(true));
		assertThat(FaceValue.JACK.beats(FaceValue.TEN), equalTo(true));
	}

}
