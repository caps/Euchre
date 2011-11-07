package com.petecapra.euchre.model;

/**
 * Author: petecapra
 * Date: 4/10/11
 */
public class GameConfiguration {

	private Integer playToScore = 11;
	private FaceValue lowestCard = FaceValue.SEVEN;

	public Integer getPlayToScore() {
		return playToScore;
	}

	public void setPlayToScore(Integer playToScore) {
		this.playToScore = playToScore;
	}

	public FaceValue getLowestCard() {
		return lowestCard;
	}

	public void setLowestCard(FaceValue lowestCard) {
		this.lowestCard = lowestCard;
	}

}
