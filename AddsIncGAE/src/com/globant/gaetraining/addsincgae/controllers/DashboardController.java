package com.globant.gaetraining.addsincgae.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globant.gaetraining.addsincgae.daos.CampaignDao;
import com.globant.gaetraining.addsincgae.daos.CustomerDao;
import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.Customer;
import com.globant.gaetraining.addsincgae.model.Product;
import com.globant.gaetraining.addsincgae.services.CampaignService;

@Controller
public class DashboardController {

	@Autowired
	private CampaignService campaignService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String listActiveCampaigns(Map<String, Object> model) {

		CustomerDao daoCustomer = new CustomerDao();
		List<Customer> customers = daoCustomer.findAll();

		List<Campaign> campaigns = this.campaignService
				.findActiveCampaignsByCustomerKey(customers.get(0).getKey());

		model.put("campaigns", campaigns);

		return "Dashboard";
	}

	@RequestMapping(value = "/dashboard/campaign/{campaignId}", method = RequestMethod.GET)
	public String showCampaignDetails(@PathVariable Long campaignId, Model model) {

		List<Object> results = this.campaignService
				.findCampaignWithStatisticsbyId(campaignId);

		Campaign campaign = (Campaign) results.get(0);
		// CampaignSummary campaignSummary = (CampaignSummary) results.get(1);

		model.addAttribute("campaign", campaign);
		// model.addAttribute("campaignSummary", campaignSummary);

		return "CampaignDetails";
	}

	@RequestMapping(value = "/dashboard/data", method = RequestMethod.GET)
	public String dummyData(Model model) {

		CustomerDao daoCustomer = new CustomerDao();

		Customer customer = new Customer();
		customer.setName("Test Man A!!");
		customer = daoCustomer.persist(customer);

		System.out.println("entrooooo");

		// Active campaign
		Campaign campA = new Campaign();
		campA.setName("MockParent A");
		campA.setProduct(new ArrayList<Product>());
		campA.setCustomerKey(customer.getKey());
		campA.setActive(true);

		Product prod;
		for (int i = 0; i < 100; i++) {
			prod = new Product(campA);
			prod.setName(Integer.toString(i));
			prod.setShortDescription("SD");
			prod.setLongDescription("Long description");
			prod.setUrl("http://jkjk.com/");
			campA.getProduct().add(prod);
		}

		CampaignDao dao = new CampaignDao();
		dao.persist(campA);

		List<Campaign> campanas = this.campaignService
				.findActiveCampaignsByCustomerKey(customer.getKey());

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
