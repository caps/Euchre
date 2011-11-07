package com.petecapra.euchre.model;

/**
 * Author: petecapra
 * Date: 24/09/11
 */
public enum FaceValue {

	ACE(14),
	KING(13),
	QUEEN(12),
	JACK(11),
	TEN(10),
	NINE(9),
	EIGHT(8),
	SEVEN(7);

	private int value;

	FaceValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public boolean beats(FaceValue faceValue) {
		return value > faceValue.getValue();
	}

}
