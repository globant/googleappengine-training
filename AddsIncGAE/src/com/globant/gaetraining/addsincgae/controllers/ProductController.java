package com.globant.gaetraining.addsincgae.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.globant.gaetraining.addsincgae.controllers.binding.CampaignEditor;
import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.Product;
import com.globant.gaetraining.addsincgae.services.CampaignService;
import com.globant.gaetraining.addsincgae.services.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CampaignService campaignService;
	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Campaign.class, new CampaignEditor(this.campaignService));
    }
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String getCustomers(Map<String, Object> model) {

		List<Product> products = productService.getProducts();
		
		model.put("products", products);
		
		return "ProductList";
	}
	
	@RequestMapping(value="/addProduct", method = RequestMethod.GET)
	public String add(Model model){

		List<Campaign> campaigns = campaignService.getCampaigns();

		model.addAttribute("product", new Product());
		model.addAttribute("campaigns", campaigns);
		
		return "AddProduct";
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product, 
			ModelMap model) {

		productService.addProduct(product);
		
		return "redirect:/products";
	}
}
