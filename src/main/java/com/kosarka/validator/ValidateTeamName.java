package com.kosarka.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kosarka.model.dto.DreamTeamDTO;
import com.kosarka.service.DreamTeamService;

@Component
public class ValidateTeamName implements Validator {
	@Autowired
	private DreamTeamService dreamTeamService;
	
	@Override public boolean supports (Class clazz){
		return DreamTeamDTO.class.equals(clazz);
	}
	
	@Override public void validate(Object obj, Errors e){
		DreamTeamDTO dt = (DreamTeamDTO) obj;
		if(dreamTeamService.getByName(dt.getName()) != null){
			 e.rejectValue("name", "EXIST");
		}
	}
}
