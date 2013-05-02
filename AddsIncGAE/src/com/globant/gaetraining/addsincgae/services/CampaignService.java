package com.globant.gaetraining.addsincgae.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.gaetraining.addsincgae.daos.CampaignDao;
import com.globant.gaetraining.addsincgae.model.Campaign;
import com.google.appengine.api.datastore.Key;

@Service
public class CampaignService {

	@Autowired
	private CampaignDao campaignDao;

	public List<Campaign> getActiveCampaignsByCustomerKey(Key customerKey) {
		return campaignDao.findActiveByCustomerKey(customerKey);
	}

	public List<Campaign> getCampaigns() {
		return campaignDao.findAll(Campaign.class);
	}

	public void addCampaign(Campaign campaign) {
		campaignDao.persist(campaign);
	}

}