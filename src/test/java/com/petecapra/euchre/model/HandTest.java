package com.petecapra.euchre.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Author: petecapra
 * Date: 29/09/11
 */
public class HandTest {

	private Team australia = new Team("Australia");
	private Team spain = new Team("Spain");
	private Team.Player pete = australia.createPlayer("Pete");
	private Team.Player lauren = spain.createPlayer("Lauren");
	private Team.Player courtney = australia.createPlayer("Courtney");
	private Team.Player helios = spain.createPlayer("Helios");
	private Hand hand;

	@Before
	public void setupBeforeEachTest() {
		Game game = new Game(australia, spain, new GameConfiguration());
		hand = game.newHand();
		hand.setCallingTeam(australia);
		hand.setOppositionTeam(spain);
		hand.setTrump(Suit.HEARTS);

		// set player cards
		hand.setCards(pete, new HashSet<Card>(Arrays.asList(Card.JACK_HEARTS, Card.ACE_HEARTS, Card.KING_HEARTS, Card.QUEEN_HEARTS, Card.TEN_HEARTS)));
		hand.setCards(courtney, new HashSet<Card>(Arrays.asList(Card.JACK_SPADES, Card.ACE_SPADES, Card.KING_SPADES, Card.QUEEN_SPADES, Card.TEN_SPADES)));
		hand.setCards(lauren, new HashSet<Card>(Arrays.asList(Card.JACK_DIAMONDS, Card.ACE_DIAMONDS, Card.KING_DIAMONDS, Card.QUEEN_DIAMONDS, Card.TEN_DIAMONDS)));
		hand.setCards(helios, new HashSet<Card>(Arrays.asList(Card.JACK_CLUBS, Card.ACE_CLUBS, Card.KING_CLUBS, Card.QUEEN_CLUBS, Card.TEN_CLUBS)));

	}

	@Test
	public void isEuchredShouldReturnTrueWhenOppositionWinsFirstThreeTricks() {

		// use reflection to set trick score to zero and trick count to three
		ReflectionTestUtils.setField(hand, "trickScore", 0);
		ReflectionTestUtils.setField(hand, "trickCount", 3);

		// verify it's euchred
		assertThat(hand.isEuchred(), equalTo(true));

	}

	@Test
	public void isEuchredShouldReturnTrueWhenOppositionWinsMoreTricksThanCallingTeam() {

		// use reflection to set trick score to zero and trick count to three
		ReflectionTestUtils.setField(hand, "trickScore", 2);
		ReflectionTestUtils.setField(hand, "trickCount", 5);

		// verify it's euchred
		assertThat(hand.isEuchred(), equalTo(true));

	}

	@Test
	public void isEuchredShouldReturnFalseWhenCallingTeamWinsThreeTricks() {

		// use reflection to set trick score to zero and trick count to three
		ReflectionTestUtils.setField(hand, "trickScore", 3);
		ReflectionTestUtils.setField(hand, "trickCount", 5);

		// verify it's not euchred
		assertThat(hand.isEuchred(), equalTo(false));

	}

	@Test
	public void isFinishedShouldReturnTrueWhenIsEuchred() {

		// use reflection to set a euchre
		ReflectionTestUtils.setField(hand, "trickScore", 1);
		ReflectionTestUtils.setField(hand, "trickCount", 4);

		// verify the hand is finished
		assertThat(hand.isFinished(), equalTo(true));

	}

	// this is to test the case when the last card is indecisive to the hand score
	@Test
	public void isFinishedShouldReturnTrueWhenFourTricksHaveBeenPlayedAndCallingTeamHaveWonThree() {

		// use reflection to set calling team to win 3 out of 4 played
		ReflectionTestUtils.setField(hand, "trickScore", 3);
		ReflectionTestUtils.setField(hand, "trickCount", 4);

		// verify the hand is finished
		assertThat(hand.isFinished(), equalTo(true));

	}

	@Test
	public void isFinishedShouldReturnFalseWhenOnlyTwoTricksHaveBeenPlayed() {

		// use reflection to set trick count
		ReflectionTestUtils.setField(hand, "trickCount", 2);

		// verify the hand is not finished
		assertThat(hand.isFinished(), equalTo(false));

	}

	@Test
	public void isFinishedShouldReturnFalseWhenOnlyFourCardsHaveBeenPlayedAndBothTeamsAreEqual() {

		// use reflection to set trick count
		ReflectionTestUtils.setField(hand, "trickCount", 4);
		ReflectionTestUtils.setField(hand, "trickScore", 2);

		// verify the hand is not finished
		assertThat(hand.isFinished(), equalTo(false));

	}

	@Test
	public void getWinningPointsShouldReturnFourForALoner() {

		// set loner
		hand.setAlone(true);

		// use reflection to set trick count and score
		ReflectionTestUtils.setField(hand, "trickCount", 5);
		ReflectionTestUtils.setField(hand, "trickScore", 5);

		// verify winning points
		assertThat(hand.getWinningPoints(), equalTo(4));

	}

	@Test
	public void getWinningPointsShouldReturnTwoForAEuchre() {

		// use reflection to set trick count and score
		ReflectionTestUtils.setField(hand, "trickCount", 5);
		ReflectionTestUtils.setField(hand, "trickScore", 2);

		// verify winning points
		assertThat(hand.getWinningPoints(), equalTo(2));

	}

	@Test
	public void getWinningPointsShouldReturnOneForAThreeCardLoner() {

		// set loner
		hand.setAlone(true);

		// use reflection to set trick count and score
		ReflectionTestUtils.setField(hand, "trickCount", 5);
		ReflectionTestUtils.setField(hand, "trickScore", 3);

		// verify winning points
		assertThat(hand.getWinningPoints(), equalTo(1));

	}

	@Test
	public void getWinningPointsShouldReturnOneForAThreeCardWin() {

		// use reflection to set trick count and score
		ReflectionTestUtils.setField(hand, "trickCount", 5);
		ReflectionTestUtils.setField(hand, "trickScore", 3);

		// verify winning points
		assertThat(hand.getWinningPoints(), equalTo(1));

	}

	@Test
	public void getWinningPointsShouldReturnTwoForAFiveCardWin() {

		// use reflection to set trick count and score
		ReflectionTestUtils.setField(hand, "trickCount", 5);
		ReflectionTestUtils.setField(hand, "trickScore", 5);

		// verify winning points
		assertThat(hand.getWinningPoints(), equalTo(2));

	}

	@Test(expected = IllegalStateException.class)
	public void getWinningPointsShouldThrowExceptionWhenHandIsNotFinished() {

		// use reflection to set trick count and score
		ReflectionTestUtils.setField(hand, "trickCount", 1);
		ReflectionTestUtils.setField(hand, "trickScore", 0);

		// verify that hand isn't finished
		assertThat(hand.isFinished(), equalTo(false));

		// now attempt to get winning ooints
		hand.getWinningPoints();

	}

	@Test
	public void getWinningTeamShouldReturnCallingTeamWhenWon() {

		// use reflection to set trick count and score
		ReflectionTestUtils.setField(hand, "trickCount", 5);
		ReflectionTestUtils.setField(hand, "trickScore", 5);

		// verify winning team is calling team
		assertThat(hand.getWinningTeam(), equalTo(hand.getCallingTeam()));

	}

	@Test
	public void getWinningTeamShouldReturnOppositionTeamWhenEuchred() {

		// use reflection to set trick count and score
		ReflectionTestUtils.setField(hand, "trickCount", 3);
		ReflectionTestUtils.setField(hand, "trickScore", 0);

		// verify winning team is opposition team
		assertThat(hand.getWinningTeam(), equalTo(hand.getOppositionTeam()));

	}

	@Test(expected = IllegalStateException.class)
	public void getWinningTeamShouldThrowExceptionWhenHandIsNotFinished() {

		// use reflection to set trick count and score
		ReflectionTestUtils.setField(hand, "trickCount", 1);
		ReflectionTestUtils.setField(hand, "trickScore", 0);

		// verify that hand isn't finished
		assertThat(hand.isFinished(), equalTo(false));

		// now attempt to get winning team
		hand.getWinningTeam();

	}

	@Test
	public void isFinishedShouldReturnTrueWhenFiveTricksHaveBeenPlayed() {

		// use reflection to set trick count to 5
		ReflectionTestUtils.setField(hand, "trickCount", 5);

		// verify the hand is finished
		assertThat(hand.isFinished(), equalTo(true));

	}

	@Test(expected = IllegalStateException.class)
	public void playTrickShouldThrowExceptionWhenLessThanThreeCards() {

		List<CardPlayed> cardsPlayed = new ArrayList<CardPlayed>();
		cardsPlayed.add(new CardPlayed(pete, Card.ACE_HEARTS));
		cardsPlayed.add(new CardPlayed(lauren, Card.SEVEN_HEARTS));

		// attempt to play two cards
		hand.playTrick(cardsPlayed);

	}

	@Test(expected = IllegalStateException.class)
	public void playTrickShouldThrowExceptionWhenMoreThanFourCards() {

		List<CardPlayed> cardsPlayed = new ArrayList<CardPlayed>();
		cardsPlayed.add(new CardPlayed(pete, Card.ACE_HEARTS));
		cardsPlayed.add(new CardPlayed(pete, Card.ACE_HEARTS));
		cardsPlayed.add(new CardPlayed(lauren, Card.SEVEN_HEARTS));
		cardsPlayed.add(new CardPlayed(courtney, Card.SEVEN_SPADES));
		cardsPlayed.add(new CardPlayed(helios, Card.KING_HEARTS));

		// attempt to play five cards
		hand.playTrick(cardsPlayed);

	}

	@Test
	public void trickCountShouldIncreaseWhenTrickPlayed() {

		// verify trick count
		assertThat(hand.getTrickCount(), equalTo(0));

		// play a trick
		playFirstTrickWinForAustralia(hand);

		// verify trick count has increased
		assertThat(hand.getTrickCount(), equalTo(1));

	}

	@Test
	public void trickScoreShouldIncreaseWhenCallingTeamWins() {

		// verify trick score
		assertThat(hand.getTrickScore(), equalTo(0));

		// play a trick
		playFirstTrickWinForAustralia(hand);

		// verify trick score has increased
		assertThat(hand.getTrickScore(), equalTo(1));

	}

	@Test
	public void trickScoreShouldNotIncreaseWhenOppositionTeamWins() {

		// verify trick score
		assertThat(hand.getTrickScore(), equalTo(0));

		// play a trick
		playFirstTrickWinForSpain(hand);

		// verify trick score has not increased
		assertThat(hand.getTrickScore(), equalTo(0));

	}

	@Test
	public void gameScoreShouldIncreaseWhenGameIsFinished() {

		// verify game scores
		assertThat(hand.getGame().getScore(australia), equalTo(0));
		assertThat(hand.getGame().getScore(spain), equalTo(0));

		// play out a winning hand for australia
		playFirstTrickWinForAustralia(hand);
		playSecondTrickWinForAustralia(hand);
		playFirstTrickWinForSpain(hand);
		playThirdTrickWinForAustralia(hand);

		// verify score is increased for australia, but not spain
		assertThat(hand.getGame().getScore(australia), equalTo(1));
		assertThat(hand.getGame().getScore(spain), equalTo(0));

	}

	@Test
	public void playTrickShouldRemoveCardFromHand() {

		// verify player cards
		assertThat(hand.getCards(pete).contains(Card.JACK_HEARTS), equalTo(true));
		assertThat(hand.getCards(courtney).contains(Card.ACE_SPADES), equalTo(true));
		assertThat(hand.getCards(lauren).contains(Card.ACE_DIAMONDS), equalTo(true));
		assertThat(hand.getCards(helios).contains(Card.ACE_CLUBS), equalTo(true));

		// play first trick
		playFirstTrickWinForAustralia(hand);

		// verify player cards have been removed
		assertThat(hand.getCards(pete).contains(Card.JACK_HEARTS), equalTo(false));
		assertThat(hand.getCards(courtney).contains(Card.ACE_SPADES), equalTo(false));
		assertThat(hand.getCards(lauren).contains(Card.ACE_DIAMONDS), equalTo(false));
		assertThat(hand.getCards(helios).contains(Card.ACE_CLUBS), equalTo(false));

	}

	// plays out a single win for Australia
	private void playFirstTrickWinForAustralia(Hand hand) {
		List<CardPlayed> cardsPlayed = new ArrayList<CardPlayed>();
		cardsPlayed.add(new CardPlayed(pete, Card.JACK_HEARTS));
		cardsPlayed.add(new CardPlayed(lauren, Card.ACE_DIAMONDS));
		cardsPlayed.add(new CardPlayed(courtney, Card.ACE_SPADES));
		cardsPlayed.add(new CardPlayed(helios, Card.ACE_CLUBS));
		hand.playTrick(cardsPlayed);
	}

	// plays out a single win for Australia
	private void playSecondTrickWinForAustralia(Hand hand) {
		List<CardPlayed> cardsPlayed = new ArrayList<CardPlayed>();
		cardsPlayed.add(new CardPlayed(pete, Card.KING_HEARTS));
		cardsPlayed.add(new CardPlayed(lauren, Card.KING_DIAMONDS));
		cardsPlayed.add(new CardPlayed(courtney, Card.KING_SPADES));
		cardsPlayed.add(new CardPlayed(helios, Card.KING_CLUBS));
		hand.playTrick(cardsPlayed);
	}

	// plays out a single win for Australia
	private void playThirdTrickWinForAustralia(Hand hand) {
		List<CardPlayed> cardsPlayed = new ArrayList<CardPlayed>();
		cardsPlayed.add(new CardPlayed(pete, Card.QUEEN_HEARTS));
		cardsPlayed.add(new CardPlayed(lauren, Card.QUEEN_DIAMONDS));
		cardsPlayed.add(new CardPlayed(courtney, Card.QUEEN_SPADES));
		cardsPlayed.add(new CardPlayed(helios, Card.QUEEN_CLUBS));
		hand.playTrick(cardsPlayed);
	}

	// plays out a single win for Spain
	private void playFirstTrickWinForSpain(Hand hand) {
		List<CardPlayed> cardsPlayed = new ArrayList<CardPlayed>();
		cardsPlayed.add(new CardPlayed(pete, Card.ACE_HEARTS));
		cardsPlayed.add(new CardPlayed(lauren, Card.JACK_DIAMONDS));
		cardsPlayed.add(new CardPlayed(courtney, Card.JACK_SPADES));
		cardsPlayed.add(new CardPlayed(helios, Card.JACK_CLUBS));
		hand.playTrick(cardsPlayed);
	}

}
