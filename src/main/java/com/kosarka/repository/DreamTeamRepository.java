package com.kosarka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kosarka.model.DreamTeam;

public interface DreamTeamRepository extends JpaRepository<DreamTeam, Integer> {
	

}
