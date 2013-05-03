package com.globant.gaetraining.addsincgae.controllers;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.globant.gaetraining.addsincgae.controllers.rest.AdEventController;
import com.globant.gaetraining.addsincgae.model.Product;
import com.globant.gaetraining.addsincgae.services.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private static final Logger logger = Logger.getLogger(ProductController.class.getCanonicalName());
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String getCustomers(Map<String, Object> model) {

		List<Product> products = productService.getProducts();
		
		model.put("products", products);
		
		return "ProductList";
	}
	
	@RequestMapping(value="")
	public ModelAndView addProduct(){
		ModelAndView mav = new ModelAndView("productView");
		return mav;
	}
}
