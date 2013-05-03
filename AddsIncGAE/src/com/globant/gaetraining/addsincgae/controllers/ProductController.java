package com.globant.gaetraining.addsincgae.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.Product;
import com.globant.gaetraining.addsincgae.services.CampaignService;
import com.globant.gaetraining.addsincgae.services.ProductService;
import com.google.appengine.api.datastore.KeyFactory;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CampaignService campaignService;
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String getCustomers(Map<String, Object> model) {

		List<Product> products = productService.getProducts();
		
		model.put("products", products);
		
		return "ProductList";
	}
	
	@RequestMapping(value="/addProduct", method = RequestMethod.GET)
	public String add(Model model){
		//TODO Assign a campaign defined by the user
		Campaign campaign = null;
		List<Campaign> campaigns = campaignService.getCampaigns();
		if (campaigns.isEmpty()) {
			campaign = new Campaign();
			campaign.setKey(KeyFactory.createKey("campaign", 1L));
		} else {
			campaign = campaigns.get(0);
		}
		
		Product p = new Product(campaign);
		model.addAttribute("product", p);
		
		return "AddProduct";
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product,
			ModelMap model) {

		productService.addProduct(product);

		return "redirect:/products";
	}
}
