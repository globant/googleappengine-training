package com.globant.gaetraining.addsincgae.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globant.gaetraining.addsincgae.services.HomeService;


@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	HomeService homeService;

	@RequestMapping(value={"","/"}, method = RequestMethod.GET)
	public String goGreetings(Map<String,Object>model){
		String greeting = "Hola gente";
		model.put("greet", greeting);
		System.out.println("Pasa HomeController");
		homeService.populate();
		
		return "home";
	}
	
}
