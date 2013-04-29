package com.globant.gaetraining.addsincgae.daos;

import java.util.List;

import com.globant.gaetraining.addsincgae.model.CampaignSummary;
import com.google.appengine.api.datastore.Key;

public class CampaingSummaryDao extends GenericDao<CampaignSummary> {

	public List<CampaignSummary> findByCustomerKey(Key customerKey) {
		return null;
	}

}
