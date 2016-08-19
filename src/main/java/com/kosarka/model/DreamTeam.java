package com.kosarka.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="dreamteam")
public class DreamTeam {
	@Id
	@GeneratedValue
	private Integer dTeamId;
	private Integer userId;
	private String name;
	@OneToMany(mappedBy = "dreamTeam", cascade = CascadeType.ALL)
	private List<PlayerTeamModel> players;
	
	public DreamTeam(){
		
	}

	public DreamTeam(Integer dTeamId, Integer userId, String name, List<PlayerTeamModel> players) {
		this.dTeamId = dTeamId;
		this.userId = userId;
		this.name = name;
		this.players = players;
	}
	

	public Integer getdTeamId() {
		return dTeamId;
	}

	public void setdTeamId(Integer dTeamId) {
		this.dTeamId = dTeamId;
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

	public List<PlayerTeamModel> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerTeamModel> players) {
		this.players = players;
	}
}
