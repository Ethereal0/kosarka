package com.kosarka.model.dto;

import java.util.List;

public class DreamTeamDetailDTO {
	private DreamTeamDTO dreamTeam;
	private List<PlayerDTO> players;
	private int teamEff;
	public int getTeamEff() {
		return teamEff;
	}

	public void setTeamEff(int teamEff) {
		this.teamEff = teamEff;
	}

	public DreamTeamDTO getDreamTeam() {
		return dreamTeam;
	}

	public void setDreamTeam(DreamTeamDTO dreamTeam) {
		this.dreamTeam = dreamTeam;
	}

	public List<PlayerDTO> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerDTO> players) {
		this.players = players;
	}

	public DreamTeamDetailDTO(DreamTeamDTO dreamTeam, List<PlayerDTO> players) {
		this.dreamTeam = dreamTeam;
		this.players = players;
	}

	public DreamTeamDetailDTO() {

	}

}
