package com.kosarka.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosarka.model.User;
import com.kosarka.service.UserService;

@Controller
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@ResponseBody
	@RequestMapping("/res")
	public User getUser(){
		User user = userService.getUsername(1);
		return user;
	}
	
	@PreAuthorize("permitAll")
	@ResponseBody
	@RequestMapping(value="/setuser",method = RequestMethod.POST)
	public User createUser(@RequestBody String username){
		User user = userService.setUsername(username);
		return user;
	}
	
}
