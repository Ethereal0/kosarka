package com.kosarka.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosarka.model.dto.NewUserDTO;
import com.kosarka.service.UserService;
import com.kosarka.validator.ValidateNewUser;

@Controller
@RequestMapping("/api")
public class UserController {
	private final UserService userService;
	private final ValidateNewUser validateNewUser;

	@Autowired
	public UserController(UserService userService, ValidateNewUser validateNewUser) {
		this.userService = userService;
		this.validateNewUser = validateNewUser;
	}
	
	@PreAuthorize("permitAll")
	@ResponseBody
	@RequestMapping(value="/newuser", method = RequestMethod.POST)
	public ResponseEntity<List<ObjectError>> newUser(@RequestBody NewUserDTO newUserDTO, BindingResult bindingResult){
		validateNewUser.validate(newUserDTO, bindingResult);
		if (!bindingResult.hasErrors()) {
			userService.createUser(newUserDTO);
			return new ResponseEntity<List<ObjectError>>(bindingResult.getAllErrors(), HttpStatus.CREATED);
		}
		return new ResponseEntity<List<ObjectError>>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
	}
}

