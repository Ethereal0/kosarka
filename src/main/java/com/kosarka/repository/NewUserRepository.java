package com.kosarka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosarka.model.User;

public interface NewUserRepository extends JpaRepository <User, Integer> {

}
