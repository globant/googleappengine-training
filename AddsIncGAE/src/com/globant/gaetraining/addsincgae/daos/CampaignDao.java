package com.globant.gaetraining.addsincgae.daos;

import java.util.Date;
import java.util.List;

import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.Customer;
import com.google.appengine.api.datastore.Key;

@Repository
public class CampaignDao extends GenericDao<Campaign> {

	/**
	 * find all active {@link Campaign} associated to a {@link Customer}
	 * 
	 * @param customerKey
	 *            {@link Key} of the customer
	 * @return {@link List} with the {@link Campaign} found or null
	 */
	@SuppressWarnings("unchecked")
	public List<Campaign> findActiveByCustomerKey(Key customerKey) {

		List<Campaign> results = null;

		Query query = this.getPM().newQuery(Campaign.class);

		query.setFilter("customerKey == customerKeyParam");
		query.setFilter("startDate < currentDateParam");
		query.setFilter("endDate > currentDateParam");

		query.declareImports("import java.util.Date");
		query.declareParameters("String customerKeyParam, Date currentDateParam");

		results = (List<Campaign>) query.execute(customerKey, new Date());

		return results;

	}
}
