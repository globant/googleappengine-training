package com.globant.gaetraining.addsincgae.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.model.CampaignSummary;
import com.google.appengine.api.datastore.Key;

@Repository
public class CampaignSummaryDao extends GenericDao<CampaignSummary> {

	public List<CampaignSummary> findByCustomerKey(Key customerKey) {
		return null;
	}

}
