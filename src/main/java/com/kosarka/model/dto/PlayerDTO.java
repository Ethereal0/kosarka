package com.kosarka.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerDTO {
	
	private String id;
	
	@JsonProperty(value="firstname")
	private String firstName;
	
	private String lastname;
	
	private String team;
	
	private String leag;
	
	@JsonProperty(value="pos")
	private List<String> position;

	@JsonProperty(value="gp")
	private int gamesPlayed;
	
	@JsonProperty(value="min")
	private int minutes;
	
	@JsonProperty(value="pts")
	private int points;
	
	@JsonProperty(value="reb")
	private int rebounds;
	
	@JsonProperty(value="asts")
	private int assists;
	
	@JsonProperty(value="stl")
	private int steals;
	
	@JsonProperty(value="blk")
	private int block;
	
	@JsonProperty(value="fga")
	private int fgAttemp;
	
	@JsonProperty(value="fgm")
	private int fgMade;
	
	@JsonProperty(value="fta")
	private int ftAttemp;
	
	@JsonProperty(value="ftm")
	private int ftMade;
	
	@JsonProperty(value="to")
	private int turnOver;
	
	private int eff;
	
	
	public int getEff() {
		return eff;
	}

	public void setEff(int eff) {
		this.eff = eff;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getRebounds() {
		return rebounds;
	}

	public void setRebounds(int rebounds) {
		this.rebounds = rebounds;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getSteals() {
		return steals;
	}

	public void setSteals(int steals) {
		this.steals = steals;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public int getFgAttemp() {
		return fgAttemp;
	}

	public void setFgAttemp(int fgAttemp) {
		this.fgAttemp = fgAttemp;
	}

	public int getFgMade() {
		return fgMade;
	}

	public void setFgMade(int fgMade) {
		this.fgMade = fgMade;
	}

	public int getFtAttemp() {
		return ftAttemp;
	}

	public void setFtAttemp(int ftAttemp) {
		this.ftAttemp = ftAttemp;
	}

	public int getFtMade() {
		return ftMade;
	}

	public void setFtMade(int ftMade) {
		this.ftMade = ftMade;
	}

	public int getTurnOver() {
		return turnOver;
	}

	public void setTurnOver(int turnOver) {
		this.turnOver = turnOver;
	}
}
	
	
