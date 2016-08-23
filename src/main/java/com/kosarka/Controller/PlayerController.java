package com.kosarka.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosarka.service.PlayerService;

@RestController
@RequestMapping("/api")
public class PlayerController {
	private final PlayerService playerService;
	
	@Autowired
	public PlayerController(PlayerService playerService){
		this.playerService = playerService;
	}
	
	@RequestMapping("/players")
	public Object getPlayers(){
		return playerService.getPlayers();
	}

}
