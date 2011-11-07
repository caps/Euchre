package com.petecapra.euchre.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: petecapra
 * Date: 26/09/11
 */
public class Team {

	private String name;
	private List<Player> players;

	public Team(String name) {
		this.name = name;
		this.players = new ArrayList<Player>();
	}

	public String getName() {
		return name;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Player createPlayer(String name) {
		Player player = new Player(name);
		players.add(player);
		return player;
	}

	public class Player {

	private String name;

	private Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Team getTeam() {
		return Team.this;
	}

}

}
