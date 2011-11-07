package com.petecapra.euchre.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author: petecapra
 * Date: 26/09/11
 */
public class Hand {

	private static final int POINTS_EUCHRE = 2;
	private static final int POINTS_TEAM_CALLED_ALL = 2;
	private static final int POINTS_ALONE_CALLED_ALL = 4;
	private static final int POINTS_WIN = 1;

	private Game game;
	private Card upCard;
	private Suit trump;
	private Team callingTeam;
	private Team oppositionTeam;
	private int trickScore = 0;
	private int trickCount = 0;
	private boolean alone = false;
	private Map<Team.Player, Set<Card>> cards;

	public Hand(Game game) {
		this.game = game;
		cards = new HashMap<Team.Player, Set<Card>>();
	}

	public Team getCallingTeam() {
		return callingTeam;
	}

	public int getTrickScore() {
		return trickScore;
	}

	public int getTrickCount() {
		return trickCount;
	}

	public boolean isAlone() {
		return alone;
	}

	public void setAlone(boolean alone) {
		this.alone = alone;
	}

	public Game getGame() {
		return game;
	}

	public Team getOppositionTeam() {
		return oppositionTeam;
	}

	public boolean isEuchred() {
		return trickCount - trickScore >= 3;
	}

	public boolean isFinished() {

		// hand is finished if calling team is euchred
		if (isEuchred()) {
			return true;
		}

		// hand is finished if all five tricks have been played
		if (trickCount == 5) {
			return true;
		}

		// hand is finished if calling team have won three and opposition team has won one
		if (trickScore == 3 && trickCount == 4) {
			return true;
		}

		// otherwise return false
		return false;
	}

	public int getWinningPoints() {

		// assert that hand is finished
		boolean finished = isFinished();
		assert finished : "Hand is still in progress";
		if (!finished) {
			throw new IllegalStateException("Hand is still in progress");
		}

		if (isEuchred()) {
			return POINTS_EUCHRE;
		} else if (trickScore == 5 && !isAlone()) {
			return POINTS_TEAM_CALLED_ALL;
		} else if (trickScore == 5 && isAlone()) {
			return POINTS_ALONE_CALLED_ALL;
		} else {
			return POINTS_WIN;
		}

	}

	public Team getWinningTeam() {

		// assert that hand is finished
		boolean finished = isFinished();
		assert finished : "Hand is still in progress";
		if (!finished) {
			throw new IllegalStateException("Hand is still in progress");
		}

		if (isEuchred()) {
			return oppositionTeam;
		} else {
			return callingTeam;
		}

	}

	public void playTrick(List<CardPlayed> cardsPlayed) {

		// assert that at least three cards are being played
		assert cardsPlayed != null : "Must between three and four cards";
		assert cardsPlayed.size() >= 3 : "Must between three and four cards";
		assert cardsPlayed.size() <= 4 : "Must between three and four cards";
		if (cardsPlayed.size() < 3 || cardsPlayed.size() > 4) {
			throw new IllegalStateException("Must play between three and four cards");
		}

		Trick trick = new Trick(trump);

		// update trick count
		trickCount++;

		// play all cards
		for(CardPlayed cardPlayed : cardsPlayed) {

			// remove the card from the hand
			removeCard(cardPlayed);

			// play the card in the trick
			trick.play(cardPlayed);

		}

		// determine if the calling team won
		if (trick.getWinningTeam().equals(callingTeam)) {
			trickScore++;
		}

		// if the hand is finished update the game score
		if (isFinished()) {
			game.increaseScore(getWinningTeam(), getWinningPoints());
		}

	}

	public Card getUpCard() {
		return upCard;
	}

	public void setUpCard(Card upCard) {
		this.upCard = upCard;
	}

	public void setTrump(Suit trump) {
		this.trump = trump;
	}

	public void setCallingTeam(Team callingTeam) {
		this.callingTeam = callingTeam;
	}

	public void setOppositionTeam(Team oppositionTeam) {
		this.oppositionTeam = oppositionTeam;
	}

	public void setCards(Team.Player player, Set<Card> cards) {
		this.cards.put(player, cards);
	}

	public Set<Card> getCards(Team.Player player) {
		return cards.get(player);
	}

	private void removeCard(CardPlayed cardPlayed) {
		if (!getCards(cardPlayed.getPlayer()).contains(cardPlayed.getCard())) {
			throw new IllegalStateException("Player " + cardPlayed.getPlayer() + " does not have a " + cardPlayed.getCard());
		}
		getCards(cardPlayed.getPlayer()).remove(cardPlayed.getCard());
	}

}
