package com.globant.gaetraining.addsincgae.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/home")
public class HomeController {

	@RequestMapping(value={"","/"}, method = RequestMethod.GET)
	public String goGreetings(Map<String,Object>model){
		String greeting = "Hola gente";
		model.put("greet", greeting);
		System.out.println("Pasa HomeController");
		return "home";
	}
	
}
