package com.kosarka.service;

import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosarka.model.DreamTeam;
import com.kosarka.model.PlayerTeamModel;
import com.kosarka.model.dto.DreamTeamDTO;
import com.kosarka.model.dto.PlayerTeamDTO;
import com.kosarka.repository.DreamTeamRepository;
import com.kosarka.repository.PlayerRepository;

@Service
public class DreamTeamService {
	private final DreamTeamRepository dreamTeamRepository;
	private final PlayerRepository playerRepository;
	private final JTransfo jTransfo;

	@Autowired
	public DreamTeamService(DreamTeamRepository dreamTeamRepository, JTransfo jTransfo, PlayerRepository playerRepository){
		this.jTransfo = jTransfo;
		this.dreamTeamRepository = dreamTeamRepository;
		this.playerRepository = playerRepository;
	}
	
	public DreamTeamDTO setDreamTeam(DreamTeamDTO dreamTeamDTO){
		//dreamTeamDTO.setUserId(5);
		DreamTeam dreamTeam = jTransfo.convertTo(dreamTeamDTO, DreamTeam.class);
		dreamTeam.setPlayers(null);
		dreamTeamRepository.save(dreamTeam);
		for (PlayerTeamDTO playerTeam : dreamTeamDTO.getPlayers()) {
			PlayerTeamModel player = jTransfo.convertTo(playerTeam, PlayerTeamModel.class);
			player.setDreamTeam(dreamTeam);
			playerRepository.save(player);
		}
		return jTransfo.convert(dreamTeam, new DreamTeamDTO());
	}

}
