package com.kosarka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosarka.model.PlayerTeamModel;

public interface PlayerRepository extends JpaRepository<PlayerTeamModel, Integer> {

}
