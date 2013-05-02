package com.globant.gaetraining.addsincgae.daos;

import java.util.Arrays;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.CampaignSummary;
import com.globant.gaetraining.addsincgae.model.DistributionChannel;
import com.globant.gaetraining.addsincgae.model.DistributionChannelSummary;
import com.globant.gaetraining.addsincgae.model.Product;
import com.globant.gaetraining.addsincgae.model.ProductSummary;

@Repository
public class CampaignSummaryDao extends GenericDao<CampaignSummary> {

	/**
	 * Find a {@link CampaignSummary} by his campaign id. Load his
	 * {@link ProductSummary} and {@link DistributionChannelSummary}
	 * 
	 * @param campaignId
	 *            Id of the {@link Campaign}
	 * @return {@link CampaignSummary}
	 */
	public CampaignSummary findByIdWithProductsAndDistrChannelsSummaries(
			Object campaignId) {

		return super.findById(campaignId, CampaignSummary.class,
				Arrays.asList("productSummary", "distributionChannelSummary"));
	}
}
