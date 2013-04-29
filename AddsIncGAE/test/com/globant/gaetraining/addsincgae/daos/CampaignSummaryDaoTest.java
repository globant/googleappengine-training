package com.globant.gaetraining.addsincgae.daos;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.globant.gaetraining.addsincgae.model.CampaignSummary;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class CampaignSummaryDaoTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());
	private CampaignSummaryDao dao;

	@Before
	public void setUp() {
		this.helper.setUp();
		this.dao = new CampaignSummaryDao();
	}

	@After
	public void tearDown() {
		this.helper.tearDown();
	}

	@Test
	public void persistCampaignSummaryTest() {

		CampaignSummary campaignSummary = new CampaignSummary();

		campaignSummary = this.dao.persist(campaignSummary);
		Assert.assertNotNull(campaignSummary);

	}

	@Test
	public void findByValidKeyTest() {

		CampaignSummary campaignSummary = new CampaignSummary();
		campaignSummary = this.dao.persist(campaignSummary);

		CampaignSummary result = this.dao.findByKey(campaignSummary.getKey(),
				CampaignSummary.class);
		Assert.assertNotNull(result);

	}

	@Test
	public void findAllTest() {

		CampaignSummary campaignSummary = new CampaignSummary();
		campaignSummary.setTotalHits(10);
		campaignSummary = this.dao.persist(campaignSummary);

		List<CampaignSummary> result = this.dao.findAll(CampaignSummary.class);

		Assert.assertNotNull(result);

	}

}
