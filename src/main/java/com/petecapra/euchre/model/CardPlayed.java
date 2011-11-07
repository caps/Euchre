package com.petecapra.euchre.model;

/**
 * Author: petecapra
 * Date: 4/10/11
 */
public class CardPlayed {

	private final Team.Player player;
	private final Card card;

	public CardPlayed(final Team.Player player, final Card card) {
		this.player = player;
		this.card = card;
	}

	public Team.Player getPlayer() {
		return player;
	}

	public Card getCard() {
		return card;
	}

	public Suit getSuit() {
		return card.getSuit();
	}

	public boolean beats(Card otherCard, Suit trump, Suit lead) {
		return card.beats(otherCard, trump, lead);
	}

}
