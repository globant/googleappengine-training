package com.globant.gaetraining.addsincgae.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.DistributionChannel;
import com.globant.gaetraining.addsincgae.model.Product;
import com.google.appengine.api.datastore.Key;
import com.sun.org.apache.xerces.internal.impl.dtd.models.CMAny;

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
		System.out.println("Num camp -> " + campaigns.size());
		List<String> campaingsKeys = new ArrayList<String>();
		for(int i = 0; i < campaigns.size(); ++i ){
			campaingsKeys.add( campaigns.get(i).getKey().toString() );
		}
		
		query = pm.newQuery(Product.class, ":p.contains(campaign_key)");
		query.setFilter("");
		List<Product> products = (List<Product>)query.execute(campaingsKeys);
		
		System.out.println("Num prods -> " + products.size());
		for (Product prod : products) {
			System.out.println(prod.getName());
		}
		
//		Map<String, Object> parameters = new HashMap<String,Object>();
//		
//		query.executeWithMap(parameters);
		pm.close();
		return null;
	}
}
