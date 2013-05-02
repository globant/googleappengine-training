package com.globant.gaetraining.addsincgae.daos;

import java.util.Arrays;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.CampaignSummary;

@Repository
public class CampaignSummaryDao extends GenericDao<CampaignSummary> {

	/**
	 * Find a {@link Campaign} by his campaign id. Load his products
	 * 
	 * @param campaignId
	 *            Id of the {@link Campaign}
	 * @return {@link Campaign}
	 */
	public CampaignSummary findByIdWithProductsAndDistrChannelsSummaries(
			Object campaignId) {

		return super.findById(campaignId, CampaignSummary.class,
				Arrays.asList("productSummary", "distributionChannelSummary"));
	}
}
