package com.kosarka.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="players")
public class PlayerTeamModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer teamId;
	private String playerId;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "Dteam_id")
	private DreamTeam dreamTeam;

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public DreamTeam getDreamTeam() {
		return dreamTeam;
	}

	public void setDreamTeam(DreamTeam dreamTeam) {
		this.dreamTeam = dreamTeam;
	}
	
}