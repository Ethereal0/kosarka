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
            e.rejectValue("username", "Username field is empty!");
        } else if (u.getPassword() == null || u.getPassword().isEmpty()) {
            e.rejectValue("password", "Password field is empty!");
        } else if (u.getConfirmPassword() == null || u.getConfirmPassword().isEmpty()) {
            e.rejectValue("confirmPassword", "Confirm password field is empty!");
        } else if (userService.getByUsername(u.getUsername()) != null) { 
            e.rejectValue("username", "User already exist!");
        } else if (!u.getPassword().equals(u.getConfirmPassword())) {
            e.rejectValue("password", "Password does not match the confirm password!");
        } 
    }
}
