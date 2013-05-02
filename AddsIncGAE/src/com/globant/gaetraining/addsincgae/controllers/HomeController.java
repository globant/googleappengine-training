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
		String[] prods = {
				"mock_product1__1","mock_product1__2",
				"mock_product2__1","mock_product2__2",
				"mock_product3__1","mock_product3__2",
				"mock_product4__1","mock_product4__2",
				"mock_product5__1","mock_product5__2",
				"mock_product6__1","mock_product6__2",
				"mock_product7__1","mock_product7__2",
				"mock_product8__1","mock_product8__2"};
		String[] channels = {"mock_distributionchannel2","mock_distributionchannel"};
		homeService.dummyEventTasks(channels,prods );
		
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
