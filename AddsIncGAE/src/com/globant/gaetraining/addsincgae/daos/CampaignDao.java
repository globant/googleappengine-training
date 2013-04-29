package com.globant.gaetraining.addsincgae.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.Customer;
import com.google.appengine.api.datastore.Key;

@Repository
public class CampaignDao extends GenericDao<Customer> {

	public List<Campaign> findActiveByCustomerKey(Key customerKey) {
		return null;
	}
}