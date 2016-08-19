package com.kosarka.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlayerService {
	public PlayerService() {

	}
//	public List<PlayerDTO> getPlayers(){
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<List<PlayerDTO>> response = restTemplate.
//		exchange("http://localhost:8888/api/players", HttpMethod.GET, null, new ParameterizedTypeReference<List<PlayerDTO>>(){});
//		return response.getBody();
//	}

	public Object getPlayers(){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> response = restTemplate.
		exchange("http://localhost:8888/api/players", HttpMethod.GET, null, new ParameterizedTypeReference<Object>(){});
		return response.getBody();
	}

}
