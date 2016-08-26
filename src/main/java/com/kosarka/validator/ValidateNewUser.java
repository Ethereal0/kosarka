package com.kosarka.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kosarka.model.dto.NewUserDTO;
import com.kosarka.service.UserService;

@Component	
public class ValidateNewUser implements Validator {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class clazz) {
		return NewUserDTO.class.equals(clazz);
	}
	@Override
    public void validate(Object obj, Errors e) {
        NewUserDTO u = (NewUserDTO) obj;
        if (u.getUsername() == null || u.getUsername().isEmpty()) {
            e.rejectValue("username", "username prazan");
        } else if (u.getPassword() == null || u.getPassword().isEmpty()) {
            e.rejectValue("password", "PasswordEmpty");
        } else if (u.getConfirmPassword() == null || u.getConfirmPassword().isEmpty()) {
            e.rejectValue("confirmPassword", "ConfirmPassworEmpty");
        } else if (userService.getByUsername(u.getUsername()) != null) { 
            e.rejectValue("username", "EXIST");
        } else if (!u.getPassword().equals(u.getConfirmPassword())) {
            e.rejectValue("password", "negativevalue");
        } 
    }
}
