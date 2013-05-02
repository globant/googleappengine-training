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
	public String getProducts(Map<String, Object> model) {

		List<Product> products = productService.getProducts();
		
		model.put("products", products);
		
		return "ProductList";
	}
	
	@RequestMapping(value="/addProduct", method = RequestMethod.GET)
	public String add(Model model){
		Campaign c = new Campaign();
		c.setKey(KeyFactory.createKey("campaign", 1L));
		Product p = new Product(c);
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
