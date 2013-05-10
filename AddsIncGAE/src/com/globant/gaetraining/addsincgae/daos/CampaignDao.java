package com.globant.gaetraining.addsincgae.daos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.Customer;
import com.globant.gaetraining.addsincgae.model.Product;
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

		query.setFilter("customerKey == customerKeyParam && "
				+ " active == activeParam");

		query.declareParameters("com.google.appengine.api.datastore.Key customerKeyParam, "
				+ "boolean activeParam");

		try {
			results = (List<Campaign>) query.execute(customerKey, true);
		} finally {
			this.getPM().close();
		}

		return results;

	}

	/**
	 * @see GenericDao#findById(Object, Class)
	 * @param campaignId
	 *            Id of the {@link Campaign}
	 * @return {@link Campaign}
	 */
	public Campaign findById(Object campaignId) {

		return super.findById(campaignId, Campaign.class, null);
	}

	/**
	 * Find a {@link Campaign} by his campaign id. Load his products
	 * 
	 * @param campaignId
	 *            Id of the {@link Campaign}
	 * @return {@link Campaign}
	 */
	public Campaign findByIdWithProducts(Object campaignId) {

		return super.findById(campaignId, Campaign.class,
				Arrays.asList("products"));
	}

	/**
	 * Find a the countries associated to the {@link Product} of a
	 * {@link Campaign}
	 * 
	 * @param campaignKey
	 *            {@link Key} of the {@link Campaign}
	 * @return {@link List} of {@link String} with the countries associated to
	 *         the {@link Product} of the {@link Campaign}
	 */
	public List<String> findCountriesByCampaignKey(Campaign campaign) {

		List<String> results = null;

		Query query = this.getPM().newQuery(Product.class);
		query.setResult("DISTINCT country");
		query.setFilter("campaign == campaignParam");
		query.declareParameters(Campaign.class.getName()+" campaignParam");

		try {
			results = (List<String>) query.execute(campaign);
		} finally {
			this.getPM().close();
		}

		return results;
	}
	
	@SuppressWarnings("finally")
	public Campaign getCampaingAndProductsById(Object campaignId){
		PersistenceManager pm = this.getPM();
		Campaign campaign = null;
		try{
			pm.getFetchPlan().addGroup("products");
			
			campaign = pm.getObjectById(Campaign.class, campaignId);
			List<Product> products = new ArrayList<Product>();
			for(Product prod : campaign.getProduct()){
				Product tmp = pm.getObjectById(Product.class, prod.getKey().getId());
				products.add(tmp);
			}
			campaign.setProduct(products);
		}finally{
			pm.close();
			return campaign;
		}
	}
	
}
