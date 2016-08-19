package com.kosarka.model.dto;

import org.jtransfo.DomainClass;

import com.kosarka.model.PlayerTeamModel;

@DomainClass(domainClass = PlayerTeamModel.class)
public class PlayerDTO {
	
	private Long id;
	
	private String firstName;
	
	private String lastname;
	
	private String team;
	
	private String leag;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getLeag() {
		return leag;
	}
	public void setLeag(String leag) {
		this.leag = leag;
	}


}
