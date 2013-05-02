package com.globant.gaetraining.addsincgae.services;

import java.util.ArrayList;
import java.util.Calendar;

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
	
	public void populate(){
		//DistChannel
		DistributionChannel distChannel = new DistributionChannel();
		Key keyDist = KeyFactory.createKey("DistributionChannel", "mock_distributionchannel");
		distChannel.setKey(keyDist);
		distChannel.setName("Mockito");
		distChannel.setMediaType("TV");
		distChannel.setTemplate("<div><h4>{{name}}</h4><p>{{longdesc}}</p></div>");
		distChannelDao.persist(distChannel);
		
		
		//Campaign
		Campaign campaign = new Campaign();
		Key keyCamp = KeyFactory.createKey("Campaign", "mock_campaign");
		campaign.setKey(keyCamp);
		campaign.setName("Mock");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		campaign.setEndDate(cal.getTime());
		cal.add(Calendar.YEAR, -1);
		campaign.setStartDate(cal.getTime());
		campaign.setDistributionChannelKeys(new ArrayList<Key>());
		campaign.getDistributionChannelKeys().add(keyDist);
		campaign.setProduct(new ArrayList<Product>());
		
		//Product
		Key keyProduct = KeyFactory.createKey(campaign.getKey(), "Product", "mock_product");
		Product product = new Product();
		product.setKey(keyProduct);
		product.setName("Mockiproduct");
		product.setShortDescription("Short Desc");
		product.setLongDescription("The longer description here");
		product.setUrl("http://mock.globant.com/");
		
		productDao.persist(product);
		
//		Key keyProduct2 = KeyFactory.createKey(campaign.getKey(), "Product", "mock_product");
//		Product product2 = new Product();
//		product.setKey(keyProduct2);
//		product.setName("Mockiproduct 2");
//		product.setShortDescription("Short Desc 2");
//		product.setLongDescription("The longer description here 2");
//		product.setUrl("http://mock.globant.com/");
//		productDao.persist(product2);
		
		campaign.getProduct().add(product);
		campaignDao.persist(campaign);
		
	}



}
