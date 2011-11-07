package com.petecapra.euchre.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: petecapra
 * Date: 4/10/11
 */
public class Game {

	private Map<Team, Integer> teamScores = new HashMap<Team, Integer>();
	private List<Team.Player> players = new ArrayList<Team.Player>();
	private GameConfiguration gameConfiguration;

	public Game(Team team1, Team team2, GameConfiguration gameConfiguration) {
		this.gameConfiguration = gameConfiguration;

		// assert that teams have players
		if (team1.getPlayers() == null || team1.getPlayers().size() != 2) {
			throw new IllegalArgumentException("Team 1 must have two players");
		}
		if (team2.getPlayers() == null || team2.getPlayers().size() != 2) {
			throw new IllegalArgumentException("Team 2 must have two players");
		}

		// initialise scores
		teamScores.put(team1, 0);
		teamScores.put(team2, 0);

		// set up players
		players.addAll(team1.getPlayers());
		players.addAll(team2.getPlayers());

	}

	public Integer getScore(Team team) {
		return teamScores.get(team);
	}

	public boolean isFinished() {
		for(Integer teamScore : teamScores.values()) {
			if (teamScore >= gameConfiguration.getPlayToScore()) {
				return true;
			}
		}
		return false;
	}

	public Hand newHand() {
		return new Hand(this);
	}

	public void increaseScore(Team team, int score) {
		teamScores.put(team, getScore(team) + score);
	}

	public List<Team.Player> getPlayers() {
		return players;
	}

}
