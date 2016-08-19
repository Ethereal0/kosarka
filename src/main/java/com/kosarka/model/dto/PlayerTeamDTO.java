package com.kosarka.model.dto;

import org.jtransfo.DomainClass;
import org.jtransfo.NotMapped;

import com.kosarka.model.DreamTeam;
import com.kosarka.model.PlayerTeamModel;

@DomainClass(domainClass = PlayerTeamModel.class)
public class PlayerTeamDTO {
	private Integer teamId;
	@NotMapped
	private DreamTeam dreamTeam;
	private String playerId;
	
	public PlayerTeamDTO(){
		
	}
	
	public PlayerTeamDTO(Integer teamId, DreamTeam dreamTeam, String playerId) {
		this.teamId = teamId;
		this.dreamTeam = dreamTeam;
		this.playerId = playerId;
	}


	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public DreamTeam getDreamTeam() {
		return dreamTeam;
	}

	public void setDreamTeam(DreamTeam dreamTeam) {
		this.dreamTeam = dreamTeam;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	
}
