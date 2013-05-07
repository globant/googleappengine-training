package com.globant.gaetraining.addsincgae.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.gaetraining.addsincgae.daos.CampaignDao;
import com.globant.gaetraining.addsincgae.daos.CampaignSummaryDao;
import com.globant.gaetraining.addsincgae.daos.ProductDao;
import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.CampaignSummary;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Service
public class CampaignService {

	@Autowired
	private CampaignDao campaignDao;

	@Autowired
	private CampaignSummaryDao campaignSummaryDao;
	
	@Autowired
	private ProductDao productDao;

	/**
	 * Find the active {@link Campaign}s for a specific customer
	 * 
	 * @param customerKey
	 *            {@link Key} of the customer
	 * @return {@link Campaign} found or null
	 */
	public List<Campaign> findActiveCampaignsByCustomerKey(Key customerKey) {
		return campaignDao.findActiveByCustomerKey(customerKey);
	}

	/**
	 * Find the {@link Campaign} associadte with the campaignId. This id is the
	 * equivalent to Key.id
	 * 
	 * @param campaignId
	 *            Id of the campaign
	 * @return {@link Campaign} with his {@link CampaignSummary} found or null
	 *         if one of both is null
	 */
	public List<Object> findCampaignWithStatisticsById(Object campaignId) {

		Campaign campaign = this.campaignDao.findByIdWithProducts(campaignId);

		CampaignSummary campaignSummary = this.campaignSummaryDao
				.findByCampaignKeyWithProductsAndDistrChannelsSummaries(campaign
						.getKey());
		
		List<String> countries = this.campaignDao
				.findCountriesByCampaignKey(campaign.getKey());

		List<Object> results = new ArrayList<>();

		if (campaign == null || campaignSummary == null) {
			return null;
		}

		results.add(campaign);
		results.add(campaignSummary);
		results.add(countries);

		return results;
	}

	public List<Campaign> getCampaigns() {
		return campaignDao.findAll(Campaign.class);
	}

	public void addCampaign(Campaign campaign) {
		campaignDao.persist(campaign);
	}

	public Campaign getCampaign(Long campaignId) {
		Key key = KeyFactory.createKey(Campaign.class.getSimpleName(), campaignId);
		return campaignDao.findByKey(key, Campaign.class, null);
	}

}
