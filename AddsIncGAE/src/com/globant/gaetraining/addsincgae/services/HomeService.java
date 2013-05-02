package com.globant.gaetraining.addsincgae.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.gaetraining.addsincgae.daos.CampaignDao;
import com.globant.gaetraining.addsincgae.daos.DistributionChannelDao;
import com.globant.gaetraining.addsincgae.daos.ProductDao;
import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.DistributionChannel;
import com.globant.gaetraining.addsincgae.model.Product;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Service
public class HomeService {

	@Autowired
	CampaignDao campaignDao;

	@Autowired
	DistributionChannelDao distChannelDao;

	@Autowired
	ProductDao productDao;

	public void populate() {
		
		String templateChannel = "<div><h4>{product.name}</h4><p>{product.shortDescription}</p><p>{product.longDescription}</p><p><a href="+"{product.navigationURL}"+">Product URL Navigation</a></p><p><a href="+"{product.displayBreadcrumURL}"+">Display Product</a></p></div>"; 
		// DistChannel
		DistributionChannel distChannel = new DistributionChannel();
		Key keyDist = KeyFactory.createKey("DistributionChannel",
				"mock_distributionchannel");
		distChannel.setKey(keyDist);
		distChannel.setName("Mockito");
		distChannel.setMediaType("TV");
		distChannel
				.setTemplate(templateChannel);
		distChannelDao.persist(distChannel);

		DistributionChannel distChannel2 = new DistributionChannel();
		Key keyDist2 = KeyFactory.createKey("DistributionChannel",
				"mock_distributionchannel 2");
		distChannel.setKey(keyDist2);
		distChannel.setName("Mockito 2");
		distChannel.setMediaType("Web");
		distChannel
				.setTemplate(templateChannel);
		distChannelDao.persist(distChannel2);

		for (int i = 1; i <= 8; ++i) {
			// Campaign
			Campaign campaign = new Campaign();
			Key keyCamp = KeyFactory.createKey("Campaign",
					"mock_campaign" + i);
			campaign.setKey(keyCamp);
			campaign.setName("Mock " + i);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 30);
			campaign.setEndDate(cal.getTime());
			cal.add(Calendar.YEAR, -1);
			campaign.setStartDate(cal.getTime());
			campaign.setDistributionChannelKeys(new ArrayList<Key>());
			campaign.getDistributionChannelKeys().add(
					i % 2 == 0 ? keyDist : keyDist2);
			campaign.setProduct(new ArrayList<Product>());

			for (int j = 1; j < 3; ++j) {
				// Product
				Key keyProduct = KeyFactory.createKey(campaign.getKey(),
						"Product", "mock_product_" + i + "_" + j);
				Product product = new Product(campaign);
				product.setKey(keyProduct);
				product.setName("Mockiproduct_" + i + "_" + j);
				product.setShortDescription("Short Desc_" + i + "_" + j);
				product.setLongDescription("The long description here " + i + "_" + j);
				product.setUrl("http://mock.globant.com/");
				campaign.getProduct().add(product);

			}
			campaignDao.persist(campaign);
		}

	}

	public List<Product> getProducts() {
		return productDao.findAll(Product.class);

	}

	public List<DistributionChannel> getChannels() {
		return distChannelDao.findAll(DistributionChannel.class);
	}

}
