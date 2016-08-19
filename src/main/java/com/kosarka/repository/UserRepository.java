package com.kosarka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosarka.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	

}
