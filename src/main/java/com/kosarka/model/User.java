package com.kosarka.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue
	private Integer User_id;
	
	private String username;
	
	private String password;
	
	private String role;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUser_id() {
		return User_id;
	}
	public void setUser_id(Integer user_id) {
		User_id = user_id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


	public User(){
		
	}
		
	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public User(String username){
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
}
