package com.globant.gaetraining.addsincgae.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globant.gaetraining.addsincgae.model.Product;
import com.globant.gaetraining.addsincgae.services.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String getCustomers(Map<String, Object> model) {

		List<Product> products = productService.getProducts();
		
		model.put("products", products);
		
		return "ProductList";
	}
	

}
