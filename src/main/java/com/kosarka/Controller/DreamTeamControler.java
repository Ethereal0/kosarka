package com.kosarka.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosarka.model.dto.DreamTeamDTO;
import com.kosarka.model.dto.DreamTeamDetailDTO;
import com.kosarka.service.DreamTeamService;

@Controller
@RequestMapping("/api")
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
	@ResponseBody
	@RequestMapping(value="/dreamteams", method = RequestMethod.GET)
	public List<DreamTeamDetailDTO> getAllTeam(){
		return dreamTeamService.getAllTeam();
	}
	@ResponseBody
	@RequestMapping(value="/dreamteams/{id}")
	public DreamTeamDetailDTO teamDetai(@PathVariable("id") int id){
		return dreamTeamService.getOne(id);
	}
}
