package com.petecapra.euchre.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Author: petecapra
 * Date: 26/09/11
 */
public class DeckTest {

	private Team australia = new Team("Australia");
	private Team spain = new Team("Spain");
	private Game game;

	@Before
	public void setupBeforeEachTest() {
		australia.createPlayer("Pete");
		spain.createPlayer("Lauren");
		australia.createPlayer("Courtney");
		spain.createPlayer("Helios");
		game  = new Game(australia, spain, new GameConfiguration());
	}

	@Test
	public void shuffleGeneratesRandomDeck() {

		// create new deck where the lowest card is seven
		Deck deck = DeckFactory.create(FaceValue.SEVEN);

		// get a copy of the unshuffled deck cards
		List<Card> unshuffledCards = deck.getCards();

		// verify the size of the deck before shuffle
		assertThat(unshuffledCards.size(), equalTo(32));

		// now shuffle the deck
		deck.shuffle();

		// get a copy of the shuffled deck of cards
		List<Card> shuffledCards = deck.getCards();

		// verify the size of the deck is unchanged
		assertThat(unshuffledCards.size(), equalTo(shuffledCards.size()));

		// iterate through both decks and verify that at least one card is in a different position
		int different = 0;
		for (int i = 0; i < shuffledCards.size(); i++) {
			if (!unshuffledCards.get(i).equals(shuffledCards.get(i))) {
				different++;
			}
		}
		assertThat(different, not(0));

	}

	@Test
	public void dealShouldReturnFiveCardsEach() {

		// create a new deck
		Deck deck = DeckFactory.create(FaceValue.SEVEN);

		// now deal
		Hand hand = deck.deal(game);

		// verify that each player has exactly five cards
		for(Team.Player player : game.getPlayers()) {
			assertThat(hand.getCards(player).size(), equalTo(5));
		}

	}

	@Test
	public void dealShouldReturnUniqueCards() {

		// create a new deck
		Deck deck = DeckFactory.create(FaceValue.SEVEN);

		// now hand
		Hand hand = deck.deal(game);

		// verify all cards are different
		Set<Card> cards = new HashSet<Card>();

		// add turned over card
		cards.add(hand.getUpCard());

		// get all cards
		for (Team.Player player : game.getPlayers()) {
			for(Card playerCard : hand.getCards(player)) {
				if (cards.contains(playerCard)) {
					fail("Duplicate card found!");
				}
				cards.add(playerCard);
			}
		}

	}

}