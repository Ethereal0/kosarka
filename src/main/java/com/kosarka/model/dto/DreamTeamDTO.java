package com.kosarka.model.dto;

import java.util.List;

import org.jtransfo.DomainClass;

import com.kosarka.model.DreamTeam;

@DomainClass(domainClass = DreamTeam.class)
public class DreamTeamDTO {

	private Integer dTeamId;

	private List<PlayerTeamDTO> players;

	private Integer userId;

	private String name;

	public DreamTeamDTO() {

	}

	public DreamTeamDTO(Integer dTeamId, List<PlayerTeamDTO> players, Integer userId, String name) {
		this.dTeamId = dTeamId;
		this.players = players;
		this.userId = userId;
		this.name = name;
	}

	public Integer getdTeamId() {
		return dTeamId;
	}

	public void setdTeamId(Integer dTeamId) {
		this.dTeamId = dTeamId;
	}

	public List<PlayerTeamDTO> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerTeamDTO> players) {
		this.players = players;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
