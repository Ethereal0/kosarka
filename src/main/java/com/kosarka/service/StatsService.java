package com.kosarka.service;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosarka.model.dto.PlayerDTO;
import com.kosarka.model.dto.StatsDTO;

@Service
public class StatsService {

	public List<PlayerDTO> stats;

	public StatsService() throws JsonParseException, JsonMappingException, IOException {
		this.stats = getStats();
	}

	public List<PlayerDTO> getStats() throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<StatsDTO> response = restTemplate.exchange("http://localhost:8888/api/stats", HttpMethod.GET,
				null, new ParameterizedTypeReference<StatsDTO>() {
				});

		return response.getBody().getPlayers();
	}

	public PlayerDTO findOne(String playerId) {
		return this.stats.stream().filter(player -> player.getId().equals(playerId)).findFirst().orElse(null);
	}

	public int setEff(PlayerDTO playerDTO) {
		return playerDTO.getPoints() + playerDTO.getRebounds() + playerDTO.getAssists() + playerDTO.getSteals()
				+ playerDTO.getBlock() - ((playerDTO.getFgAttemp() - playerDTO.getFgMade())
						+ (playerDTO.getFtAttemp() - playerDTO.getFtMade()) + playerDTO.getTurnOver());

	}
}
