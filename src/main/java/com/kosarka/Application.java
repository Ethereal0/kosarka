package com.kosarka;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ImportResource(value = { "classpath:org/jtransfo/spring/jTransfoContext.xml" })
@RestController
public class Application {

	@Controller
	static class Routes {

		@RequestMapping(value = { "/", "/login", "/teams", "/teams/*","/players","/createteam","/register"})
		public String index() {
			return "forward:index.html";
		}

	}

	  @RequestMapping("/user")
	  public Principal user(Principal user) {
	    return user;
	  }


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}