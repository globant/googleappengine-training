package com.globant.gaetraining.addsincgae.daos;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.globant.gaetraining.addsincgae.model.Campaign;
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
	public void persistCampaignTest() {

		Campaign campaign = new Campaign();

		campaign = this.dao.persist(campaign);
		Assert.assertNotNull(campaign);

	}

	@Test
	public void findByValidKeyTest() {

		Campaign campaign = new Campaign();
		campaign = this.dao.persist(campaign);

		Campaign result = this.dao.findByKey(campaign.getKey(),
				Campaign.class);
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
