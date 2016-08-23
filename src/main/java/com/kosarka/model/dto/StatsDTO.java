package com.kosarka.model.dto;

import java.util.List;

public class StatsDTO {

	private List<PlayerDTO> players;

	public StatsDTO() {
		
	}

	public StatsDTO(List<PlayerDTO> players) {
		this.players = players;
	}

	public List<PlayerDTO> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerDTO> players) {
		this.players = players;
	}
}
