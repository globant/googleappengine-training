package com.globant.gaetraining.addsincgae.daos;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.globant.gaetraining.addsincgae.model.CampaignSummary;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class CampaingSummaryDaoTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());
	private GenericDao<CampaignSummary> dao;

	@Before
	public void setUp() {
		this.helper.setUp();
		this.dao = new GenericDao<CampaignSummary>();
	}

	@After
	public void tearDown() {
		this.helper.tearDown();
	}

	@Test
	public void persistCampaingSummaryTest() {

		CampaignSummary campaingSummary = new CampaignSummary();

		campaingSummary = this.dao.persist(campaingSummary);
		Assert.assertNotNull(campaingSummary);

	}

	@Test
	public void findByValidKeyTest() {

		CampaignSummary campaingSummary = new CampaignSummary();
		campaingSummary = this.dao.persist(campaingSummary);
		
		
		CampaignSummary result = this.dao.findByKey(campaingSummary.getKey(),
				CampaignSummary.class);
		Assert.assertNotNull(result);

	}

}
