package com.globant.gaetraining.addsincgae.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;


import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.DistributionChannel;
import com.globant.gaetraining.addsincgae.model.Product;

@Repository
public class ProductDao extends GenericDao<Product> {
	
	private static final Logger logger = Logger.getLogger(ProductDao.class.getCanonicalName());

	public List<Product> getProductsByKeyWordAndCampaignAndDistChannel(DistributionChannel distChannel,
							String keyword, int limit){
		PersistenceManager pm = this.getPM();
				
		Query query = pm.newQuery(Campaign.class);
		query.setFilter("distributionChannelKeys == channelNameParam");
		query.declareParameters("String channelNameParam");
		List<Campaign> campaigns = (List<Campaign>) query.execute(distChannel.getKey());
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
	
	/**
	 * Updates the product in a campaign relationship
	 * @param campaign
	 * @param products
	 * @return
	 */
	@SuppressWarnings("finally")
	public Campaign updateProductsCampaignRelationship(Campaign campaign, List<Product> products){
		PersistenceManager pm = this.getPM();
		Campaign campaignUpdated = null;
		try{
			campaignUpdated = pm.getObjectById(Campaign.class, campaign.getKey().getId());
			campaignUpdated.setProduct(products);
		}finally{
			pm.close();
			return campaignUpdated;
		}
	}
	
}
