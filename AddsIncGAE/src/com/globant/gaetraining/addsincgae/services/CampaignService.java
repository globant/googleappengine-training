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

	/**
	 * Find the active campaigns associated to the specified {@link Customer}
	 * 
	 * @param customerKey
	 *            {@link Key} of the cus
	 * @return
	 */
	public List<Campaign> findActiveCampaignsByCustomerKey(Key customerKey) {
		return this.campaignDao.findActiveByCustomerKey(customerKey);
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

		Campaign campaign = this.campaignDao.findById(campaignId);

		List<Object> results = new ArrayList<>();

		results.add(campaign);
		results.add(campaign);
		results.add(campaign);

		return results;
	}
}
