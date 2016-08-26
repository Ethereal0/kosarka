package com.kosarka.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosarka.model.dto.DreamTeamDTO;
import com.kosarka.model.dto.DreamTeamDetailDTO;
import com.kosarka.service.DreamTeamService;
import com.kosarka.validator.ValidateTeamName;

@Controller
@RequestMapping("/api")
public class DreamTeamControler {
	private final DreamTeamService dreamTeamService;
	private final ValidateTeamName validateTeamName;

	@Autowired
	public DreamTeamControler(DreamTeamService dreamTeamService, ValidateTeamName validateTeamName) {
		this.dreamTeamService = dreamTeamService;
		this.validateTeamName = validateTeamName;
	}

	@ResponseBody
	@RequestMapping(value="/createDreamTeam", method = RequestMethod.POST)
	public ResponseEntity<List<ObjectError>> createTeam(@RequestBody DreamTeamDTO dreamTeamDTO, BindingResult bindingResult){
		validateTeamName.validate(dreamTeamDTO, bindingResult);
		if (!bindingResult.hasErrors()) {
			dreamTeamService.setDreamTeam(dreamTeamDTO);
			return new ResponseEntity<List<ObjectError>>(bindingResult.getAllErrors(), HttpStatus.CREATED);
		}
		return new ResponseEntity<List<ObjectError>>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@RequestMapping(value = "/dreamteams", method = RequestMethod.GET)
	public List<DreamTeamDetailDTO> getAllTeam() {
		return dreamTeamService.getAllTeam();
	}

	@ResponseBody
	@RequestMapping(value = "/dreamteams/{id}")
	public DreamTeamDetailDTO teamDetailDTO(@PathVariable("id") int id) {
		return dreamTeamService.getOne(id);
	}
}
