package com.globant.gaetraining.addsincgae.services;

import java.util.ArrayList;
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

	public List<Campaign> findActiveCampaignsByCustomerKey(Key customerKey) {
		return campaignDao.findActiveByCustomerKey(customerKey);
	}

	/**
	 * Find the {@link Campaign} associadte with the campaignId. This id is the
	 * equivalent to Key.id
	 * 
	 * @param campaignId
	 *            Id of the campaign
	 * @return {@link Campaign} found or null
	 */
	public List<Object> findCampaignWithStatisticsbyId(Object campaignId) {

		Campaign campaign = this.campaignDao.findByIdWithProducts(campaignId);

		campaign.getProduct().size();

		List<Object> results = new ArrayList<>();

		results.add(campaign);
		results.add(campaign);
		results.add(campaign);

		return results;
	}

	public List<Campaign> getCampaigns() {
		return campaignDao.findAll(Campaign.class);
	}

	public void addCampaign(Campaign campaign) {
		campaignDao.persist(campaign);
	}

}
