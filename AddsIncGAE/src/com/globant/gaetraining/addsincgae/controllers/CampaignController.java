package com.globant.gaetraining.addsincgae.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.Product;
import com.globant.gaetraining.addsincgae.services.CampaignService;
import com.globant.gaetraining.addsincgae.services.ProductService;

@Controller
public class CampaignController {
	
	@Autowired
	private CampaignService campaignService;
	
	@RequestMapping(value = "/campaign", method = RequestMethod.GET)
	public String addUser(Map<String, Object> model) {


		return "AddCampaign";
	}
	
	@RequestMapping(value = "/campaigns", method = RequestMethod.POST)
	public String addCampaignSubmit(@ModelAttribute("campaign") Campaign campaign,
			ModelMap model) {

		campaignService.addCampaign(campaign);

		return "redirect:/campaigns";
	}

	@RequestMapping(value = "/campaigns", method = RequestMethod.GET)
	public String getCampaigns(Map<String, Object> model) {

		List<Campaign> campaigns = campaignService.getCampaigns();
		
		model.put("campaigns", campaigns);
				
		return "CampaignList";
	}
	
	@RequestMapping("/campaign/{campaignId}")
	public String editCampaign(@PathVariable Long campaignId, Model model){
		
		Campaign campaign = campaignService.getCampaignAndProductsByCampaignId(campaignId);
		if(campaign.getProduct()!=null){
		for(Product prod : campaign.getProduct()){
			System.out.println("->"+prod.getName());
		}
		}
		model.addAttribute("campaign", campaign);
		return "AddCampaign";
	}
}
