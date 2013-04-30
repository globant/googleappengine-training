package com.globant.gaetraining.addsincgae.daos;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.Product;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class CampaignDaoTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());
	private CampaignDao dao;

	@Before
	public void setUp() {
		this.helper.setUp();
		this.dao = new CampaignDao();
	}

	@After
	public void tearDown() {
		this.helper.tearDown();
	}

	@Test
	public void persistCampaingTest() {
		Campaign camp = new Campaign();
		camp.setName("Mock");
		camp = dao.persist(camp);
		Assert.assertNotNull(camp.getKey());
	}

	@Test
	public void persistCampaignAndProducts() {
		Campaign camp = new Campaign();
		camp.setName("MockParent");
		camp.setProduct(new ArrayList<Product>());
		Product prod;
		for (int i = 0; i < 100; i++) {
			prod = new Product();
			prod.setName(Integer.toString(i));
			prod.setShortDescription("SD");
			prod.setLongDescription("Long description");
			prod.setUrl("http://jkjk.com/");
			camp.getProduct().add(prod);
		}
		Campaign cmp = dao.persist(camp);
		Assert.assertNotNull(camp.getKey());
		Assert.assertEquals(cmp.getProduct().size(), 100);
		for (int i = 0; i < 100; i++) {
			Assert.assertNotNull(cmp.getProduct().get(i).getKey());
		}
	}

	@Test
	public void findByValidKeyTest() {
		Campaign campaign = new Campaign();
		campaign = this.dao.persist(campaign);
		Campaign result = this.dao.findByKey(campaign.getKey(), Campaign.class);
		Assert.assertNotNull(result);

	}

	@Test
	public void findAllTest() {
		Campaign campaign = new Campaign();
		campaign.setName("Mc Donalds 2013");
		campaign = this.dao.persist(campaign);
		List<Campaign> result = this.dao.findAll(Campaign.class);
		Assert.assertNotNull(result);

	}
}
