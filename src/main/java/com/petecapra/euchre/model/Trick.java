package com.petecapra.euchre.model;

/**
 * Author: petecapra
 * Date: 27/09/11
 */
public class Trick {

	private int cardsPlayed = 0;
	private Suit lead;
	private CardPlayed winner;
	private final Suit trump;

	public Trick(Suit trump) {
		this.trump = trump;
	}

	public void play(CardPlayed cardPlayed) {

		// if this is the first card played in the trick
		if (isFirstCardInTrick()) {
			lead = cardPlayed.getSuit();
			winner = cardPlayed;

			// if this card beats the current winner, update the new winner
		} else if (cardPlayed.beats(winner.getCard(), trump, lead)) {
			winner = cardPlayed;
		}

		// note that the cards played has been increased
		cardsPlayed++;

	}

	private boolean isFirstCardInTrick() {
		return cardsPlayed == 0;
	}

	public CardPlayed getWinner() {
		if (!isFinished()) {
			throw new IllegalStateException("Trick still in progress. A winner has not yet been determined!");
		}
		return winner;
	}

	public CardPlayed getCurrentWinner() {
		return winner;
	}

	public boolean isFinished() {
		return cardsPlayed == 4;
	}


	public Team getWinningTeam() {
		return winner.getPlayer().getTeam();
	}
}
