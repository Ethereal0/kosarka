package com.kosarka.service;

import java.util.ArrayList;
import java.util.List;

import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosarka.model.DreamTeam;
import com.kosarka.model.PlayerTeamModel;
import com.kosarka.model.dto.DreamTeamDTO;
import com.kosarka.model.dto.DreamTeamDetailDTO;
import com.kosarka.model.dto.PlayerDTO;
import com.kosarka.model.dto.PlayerTeamDTO;
import com.kosarka.repository.DreamTeamRepository;
import com.kosarka.repository.PlayerRepository;

@Service
public class DreamTeamService {
	private final DreamTeamRepository dreamTeamRepository;
	private final PlayerRepository playerRepository;
	private final JTransfo jTransfo;
	private final StatsService statsService;

	@Autowired
	public DreamTeamService(DreamTeamRepository dreamTeamRepository, JTransfo jTransfo,
			PlayerRepository playerRepository, StatsService statsService) {
		this.jTransfo = jTransfo;
		this.dreamTeamRepository = dreamTeamRepository;
		this.playerRepository = playerRepository;
		this.statsService = statsService;
		
	}

	public DreamTeamDTO setDreamTeam(DreamTeamDTO dreamTeamDTO) {
		// dreamTeamDTO.setUserId(5);
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

	public List<DreamTeamDetailDTO> getAllTeam() {
		List<DreamTeamDetailDTO> dreamTeamDetailDTOs = new ArrayList<DreamTeamDetailDTO>();
		List<DreamTeam> dreamTeamAll = dreamTeamRepository.findAll();
		for (DreamTeam dt : dreamTeamAll) {
			DreamTeamDetailDTO dreamTeamDto = setTeam(dt);
			dreamTeamDetailDTOs.add(dreamTeamDto);
		}
		return dreamTeamDetailDTOs;
	}
	public DreamTeamDetailDTO getOne(int id){
		DreamTeam dreamTeam  = dreamTeamRepository.findOne(id);
		DreamTeamDetailDTO dreamTeamDto = setTeam(dreamTeam);
		return dreamTeamDto;
	}
	private DreamTeamDetailDTO setTeam(DreamTeam dreamTeam){
		DreamTeamDetailDTO dreamTeamDetailDTO = new DreamTeamDetailDTO();
		dreamTeamDetailDTO.setDreamTeam(jTransfo.convertTo(dreamTeam, DreamTeamDTO.class));
		List<PlayerDTO> players = new ArrayList<PlayerDTO>();
		int teamEff = 0;
		for (PlayerTeamModel playerTeam : dreamTeam.getPlayers()) {
			PlayerDTO playerDTO = statsService.findOne(playerTeam.getPlayerId());
			if  (playerDTO != null) {
				int playerEff = statsService.setEff(playerDTO);
				playerDTO.setEff(playerEff);
				players.add(playerDTO);
				teamEff += playerEff;
			}
		}
		dreamTeamDetailDTO.setPlayers(players);
		dreamTeamDetailDTO.setTeamEff(teamEff);
		return dreamTeamDetailDTO;
	}
}
