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

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String goGreetings(Map<String, Object> model) {
		String greeting = "Hola gente";
		model.put("greet", greeting);
		System.out.println("Pasa HomeController");
		homeService.populate();
		
		long[][] prods = {
				{2000001L,3000011L},
				{2000001L,3000012L},
				{2000002L,3000021L},
				{2000002L,3000022L},
				{2000003L,3000031L},
				{2000003L,3000032L},
				{2000004L,3000041L},
				{2000004L,3000042L},
				{2000005L,3000051L},
				{2000005L,3000052L},
				{2000006L,3000061L},
				{2000006L,3000062L},
				{2000007L,3000071L},
				{2000007L,3000072L},
				{2000008L,3000081L},
				{2000008L,3000082L}};
		long[] channels = {
						1000000L,
						1000001L };
		homeService.dummyEventTasks(channels, prods);
		return "home";
	}

	@RequestMapping("/products")
	public List<Product> getProducts() {
		return homeService.getProducts();
	}

	@RequestMapping("/channels")
	public List<DistributionChannel> getChannels() {
		return homeService.getChannels();
	}

}
