package com.globant.gaetraining.addsincgae.daos;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.DistributionChannel;
import com.globant.gaetraining.addsincgae.model.Product;

@Repository
public class ProductDao extends GenericDao<Product> {

	public List<Product> getProductsByKeyWordAndCampaignAndDistChannel(DistributionChannel distChannel,
							String keyword, int limit){
		PersistenceManager pm = this.getPM();
				
		Query query = pm.newQuery(Campaign.class);
		query.setFilter("distributionChannelKeys == channelNameParam");
		query.declareParameters("String channelNameParam");
		String chanName = distChannel.getKey().toString();
		List<Campaign> campaigns = (List<Campaign>) query.execute(distChannel.getKey());
		
		List<String> campaingsKeys = new ArrayList<String>();
		for(int i = 0; i < campaigns.size(); ++i ){
			campaingsKeys.add( campaigns.get(i).getKey().toString() );
		}
		
		query = pm.newQuery(Product.class, ":p1.contains(campaign_key)");
		query.setRange(0,limit);
		List<Product> products = (List<Product>)query.execute(campaingsKeys);
		
		
		pm.close();
		return products;
	}
	
}
