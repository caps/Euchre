package com.petecapra.euchre.model;

import java.util.*;

/**
 * Author: petecapra
 * Date: 24/09/11
 */
public class Deck {

	private List<Card> cards;

	public Deck(List<Card> cards) {
		this.cards = cards;
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList( new ArrayList<Card>(cards));
	}

	public Hand deal(Game game) {

		// always shuffle before dealing!
		shuffle();

		// create a new hand
		Hand hand = game.newHand();

		// deal five cards to each player
		for (Team.Player player : game.getPlayers()) {
			hand.setCards(player, useNextCards(5));
		}

		// set the up card
		hand.setUpCard(useNextCard());

		return hand;

	}

	private Set<Card> useNextCards(int amount) {
		Set<Card> nextCards = new HashSet<Card>();
		for(int i = 0; i < amount; i++) {
			nextCards.add(useNextCard());
		}
		return nextCards;
	}

	private Card useNextCard() {
		Card nextCard = cards.get(0);
		cards.remove(0);
		return nextCard;
	}

}