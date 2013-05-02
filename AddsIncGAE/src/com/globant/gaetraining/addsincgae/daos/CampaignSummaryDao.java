package com.globant.gaetraining.addsincgae.daos;

import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.CampaignSummary;
import com.globant.gaetraining.addsincgae.model.DistributionChannelSummary;
import com.globant.gaetraining.addsincgae.model.ProductSummary;
import com.google.appengine.api.datastore.Key;

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
	public CampaignSummary findByCampaignKeyWithProductsAndDistrChannelsSummaries(
			Key campaignKey) {

		CampaignSummary result = null;

		Query query = this.getPM().newQuery(CampaignSummary.class);

		query.setFilter("campaingKey == campaingKeyParam");

		query.declareParameters("com.google.appengine.api.datastore.Key customerKeyParam");

		this.getPM().getFetchPlan().addGroup("productSummary");
		this.getPM().getFetchPlan().addGroup("distributionChannelSummary");

		try {
			result = (CampaignSummary) query.execute(campaignKey);
		} finally {
			this.getPM().close();
		}

		return result;
	}
}
