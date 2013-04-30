package com.globant.gaetraining.addsincgae.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globant.gaetraining.addsincgae.model.DistributionChannel;
import com.globant.gaetraining.addsincgae.model.Product;
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
	
	@RequestMapping("/products")
	public List<Product> getProducts(){
		return homeService.getProducts();
	}
	
	@RequestMapping("/channels")
	public List<DistributionChannel> getChannels(){
		return homeService.getChannels();
	}
	
	
}
