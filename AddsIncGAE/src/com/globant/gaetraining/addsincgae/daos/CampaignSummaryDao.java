package com.globant.gaetraining.addsincgae.daos;

import java.util.List;

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

		List<CampaignSummary> results = null;
		CampaignSummary result = null;

		Query query = this.getPM().newQuery(CampaignSummary.class);

		query.setFilter("campaignKey == campaingKeyParam");

		query.declareParameters("com.google.appengine.api.datastore.Key customerKeyParam");

		this.getPM().getFetchPlan().addGroup("productSummary");
		this.getPM().getFetchPlan().addGroup("distributionChannelSummary");

		try {
			results = ((List<CampaignSummary>) query.execute(campaignKey));
			result = results.get(0);
		} finally {
			this.getPM().close();
		}

		return result;
	}
}
