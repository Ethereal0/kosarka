package com.kosarka.model;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue
	private Integer User_id;
	@Column(name="username")
	private String username;
	public User(){
		
	}
	public User(String username){
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
}
