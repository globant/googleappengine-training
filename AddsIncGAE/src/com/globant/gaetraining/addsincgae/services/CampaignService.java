package com.globant.gaetraining.addsincgae.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.gaetraining.addsincgae.daos.CampaignDao;
import com.globant.gaetraining.addsincgae.daos.CampaignSummaryDao;
import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.CampaignSummary;
import com.google.appengine.api.datastore.Key;

@Service
public class CampaignService {

	@Autowired
	private CampaignDao campaignDao;

	@Autowired
	private CampaignSummaryDao campaignSummaryDao;

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
	public List<Object> findCampaignWithStatisticsById(Object campaignId) {

		Campaign campaign = this.campaignDao.findById(campaignId);
		
		CampaignSummary campaignSummary = this.campaignSummaryDao
				.findByIdWithProductsAndDistrChannelsSummaries(campaignId);

		campaign.getProduct().size();

		List<Object> results = new ArrayList<>();

		results.add(campaign);
		results.add(campaignSummary);

		return results;
	}

	public List<Campaign> getCampaigns() {
		return campaignDao.findAll(Campaign.class);
	}

	public void addCampaign(Campaign campaign) {
		campaignDao.persist(campaign);
	}

}
