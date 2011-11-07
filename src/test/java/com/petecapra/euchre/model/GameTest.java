package com.petecapra.euchre.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

	private Game game;
	private Team australia = new Team("Australia");
	private Team spain = new Team("Spain");
	private Team.Player pete = australia.createPlayer("Pete");
	private Team.Player lauren = spain.createPlayer("Lauren");
	private Team.Player courtney = australia.createPlayer("Courtney");
	private Team.Player helios = spain.createPlayer("Helios");

	@Before
	public void setupBeforeEachTest() {
		GameConfiguration gameConfiguration = new GameConfiguration();
		gameConfiguration.setPlayToScore(3);
		game = new Game(australia, spain, gameConfiguration);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createGameWithNullTeam1PlayersShouldThrowException() {
		new Game(mock(Team.class), spain, new GameConfiguration());
	}

	@Test(expected = IllegalArgumentException.class)
	public void createGameWithNullTeam2PlayersShouldThrowException() {
		new Game(australia, mock(Team.class), new GameConfiguration());
	}

	@Test(expected = IllegalArgumentException.class)
	public void createGameWithIncorrectTeam1PlayerSizeShouldThrowException() {
		Team team1 = mock(Team.class);
		when(team1.getPlayers()).thenReturn(Arrays.asList(pete));
		new Game(team1, spain, new GameConfiguration());
	}

	@Test(expected = IllegalArgumentException.class)
	public void createGameWithIncorrectTeam2PlayerSizeShouldThrowException() {
		Team team2 = mock(Team.class);
		when(team2.getPlayers()).thenReturn(Arrays.asList(lauren, helios, courtney));
		new Game(australia, team2, new GameConfiguration());
	}

	@Test
	public void getScoreShouldReturnZeroBeforeGameStarts() {
		assertThat(game.getScore(australia), equalTo(0));
		assertThat(game.getScore(spain), equalTo(0));
	}

	@Test
	public void getScoreShouldReturnCorrectlyWhenGameIsInProgress() {

		playHandWinForAustralia();

		// verify scores
		assertThat(game.getScore(australia), equalTo(1));
		assertThat(game.getScore(spain), equalTo(0));

	}

	@Test
	public void getScoreShouldReturnCorrectlyWhenGameIsWon() {

		// we're only playing to three in this test so this will win the game
		playHandWinForAustralia();
		playHandWinForAustralia();
		playHandWinForAustralia();

		// verify scores
		assertThat(game.getScore(australia), equalTo(3));
		assertThat(game.getScore(spain), equalTo(0));

	}

	@Test
	public void isGameFinishedShouldReturnFalseBeforeGameStarts() {
		assertThat(game.isFinished(), equalTo(false));
	}

	@Test
	public void isGameFinishedShouldReturnFalseWhenGameIsInProgress() {

		playHandWinForAustralia();

		// verify that game is not finished
		assertThat(game.isFinished(), equalTo(false));

	}

	@Test
	public void isGameFinishedShouldReturnTrueWhenGameIsWon() {

		// only playing to three so this will win the game for australia
		playHandWinForAustralia();
		playHandWinForAustralia();
		playHandWinForAustralia();

		// verify game is finished
		assertThat(game.isFinished(), equalTo(true));

	}

	@Test
	public void increaseScoreShouldUpdateScoreCorrectly() {

		// verify game scores
		assertThat(game.getScore(australia), equalTo(0));
		assertThat(game.getScore(spain), equalTo(0));

		// increase australia's score
		game.increaseScore(australia, 2);

		// verify game scores
	    assertThat(game.getScore(australia), equalTo(2));
		assertThat(game.getScore(spain), equalTo(0));

	}

	private void playHandWinForAustralia() {
		game.increaseScore(australia, 1);
	}

}