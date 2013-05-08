package com.globant.gaetraining.addsincgae.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.controllers.rest.AdEventController;
import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.DistributionChannel;
import com.globant.gaetraining.addsincgae.model.Product;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;

@Repository
public class ProductDao extends GenericDao<Product> {
	
	private static final Logger logger = Logger.getLogger(ProductDao.class.getCanonicalName());

	public List<Product> getProductsByKeyWordAndCampaignAndDistChannel(DistributionChannel distChannel,
							String keyword, int limit){
		PersistenceManager pm = this.getPM();
				
		Query query = pm.newQuery(Campaign.class);
		query.setFilter("distributionChannelKeys == channelNameParam");
		query.declareParameters("String channelNameParam");
		String chanName = distChannel.getKey().toString();
		List<Campaign> campaigns = (List<Campaign>) query.execute(distChannel.getKey());
		DatastoreService dataStoreService = DatastoreServiceFactory.getDatastoreService(); 
		List<Product> products = new ArrayList<Product>();
		
		for(Campaign tmpCampaign: campaigns){
			query = pm.newQuery(Product.class);
			query.setFilter("campaign == campaignParam");
			query.declareParameters(Campaign.class.getName() + " campaignParam");
			products.addAll((List<Product>)(query.execute(tmpCampaign)));
		}
		
		logger.log(Level.INFO, "The products list has been set");
		pm.close();
		return products;
	}
	
}
