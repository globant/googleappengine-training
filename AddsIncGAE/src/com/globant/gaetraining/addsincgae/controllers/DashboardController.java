package com.globant.gaetraining.addsincgae.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globant.gaetraining.addsincgae.daos.CustomerDao;
import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.CampaignSummary;
import com.globant.gaetraining.addsincgae.model.Customer;
import com.globant.gaetraining.addsincgae.services.CampaignService;
import com.globant.gaetraining.addsincgae.services.ProductsSummaryCountryDTO;

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
				.findCampaignWithStatisticsById(campaignId);

		Campaign campaign = (Campaign) results.get(0);
		CampaignSummary campaignSummary = (CampaignSummary) results.get(1);
		Map<String, List<ProductsSummaryCountryDTO>> productCountryMap = (Map<String, List<ProductsSummaryCountryDTO>>) results
				.get(2);
		List<String> countries = (List<String>) results.get(3);

		model.addAttribute("campaign", campaign);
		model.addAttribute("campaignSummary", campaignSummary);
		model.addAttribute("productCountryMap", productCountryMap);

		return "CampaignDetails";
	}

}
