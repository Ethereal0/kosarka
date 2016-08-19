package com.kosarka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosarka.model.User;
import com.kosarka.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getUsername(Integer User_id) {
		User user = userRepository.findOne(User_id);
		return user;

	}
	public User setUsername(String username){
		User user = new User(username);
		return userRepository.save(user);
	}

}
