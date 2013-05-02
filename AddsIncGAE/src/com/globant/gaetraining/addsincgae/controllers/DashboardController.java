package com.globant.gaetraining.addsincgae.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globant.gaetraining.addsincgae.daos.CampaignDao;
import com.globant.gaetraining.addsincgae.daos.CustomerDao;
import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.Customer;
import com.globant.gaetraining.addsincgae.model.Product;


@Controller
@RequestMapping("/testt")
public class DashboardController {
	
	@Autowired
	CampaignDao dao;
	
	@Autowired
	CustomerDao daoCustomer;
	
	

	@RequestMapping(value={"","/"}, method = RequestMethod.GET)
	public String goGreetings(Map<String,Object>model){
		
		Customer customer = new Customer();
		customer.setName("Test Man A!!");
		customer = this.daoCustomer.persist(customer);
		
		System.out.println("entrooooo");
		
		// Active campaign
		Campaign campA = new Campaign();
		campA.setName("MockParent A");
		campA.setProduct(new ArrayList<Product>());
		Product prod;
		for (int i = 0; i < 100; i++) {
			prod = new Product(campA);
			prod.setName(Integer.toString(i));
			prod.setShortDescription("SD");
			prod.setLongDescription("Long description");
			prod.setUrl("http://jkjk.com/");
			campA.getProduct().add(prod);
		}
		campA.setCustomerKey(customer.getKey());
		campA.setActive(true);
		
		dao.persist(campA);
		
		List<Campaign> campanas = dao.findActiveByCustomerKey(customer.getKey());
		
		for (Campaign campaign : campanas) {
			System.out.println(campaign.getName());
			System.out.println("Products" + campaign.getProduct().size());
			
			for (Product product : campaign.getProduct()) {
				System.out.println("Product name" + product.getName());
			}
		}
		
		System.out.println("Paso");
		
		return "home";
	}
	
}
