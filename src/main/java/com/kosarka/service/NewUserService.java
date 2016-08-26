package com.kosarka.service;

import org.jtransfo.JTransfo;
import org.springframework.stereotype.Service;

import com.kosarka.repository.UserRepository;

@Service
public class NewUserService {
	private final UserRepository userRepository;
	private final JTransfo jTransfo;

	public NewUserService(UserRepository userRepository, JTransfo jTransfo) {
		this.userRepository = userRepository;
		this.jTransfo = jTransfo;
	}
}
