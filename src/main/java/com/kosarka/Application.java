package com.kosarka;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

		@RequestMapping(value = { "/", "/teams", "/teams/*","/players","/createteam" })
		public String index() {
			return "forward:/index.html";
		}

	}

	@RequestMapping("/resource")
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "");
		return model;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}