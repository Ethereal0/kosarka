package com.kosarka.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosarka.model.dto.DreamTeamDTO;
import com.kosarka.service.DreamTeamService;

@Controller
public class DreamTeamControler {
	private final DreamTeamService dreamTeamService;
	
	@Autowired
	public DreamTeamControler(DreamTeamService dreamTeamService){
		this.dreamTeamService = dreamTeamService;
	}
	
	@ResponseBody
	@RequestMapping(value="/createDreamTeam", method = RequestMethod.POST)
	public DreamTeamDTO createTeam(@RequestBody DreamTeamDTO dreamTeamDTO){
		return dreamTeamService.setDreamTeam(dreamTeamDTO);
	}
}
