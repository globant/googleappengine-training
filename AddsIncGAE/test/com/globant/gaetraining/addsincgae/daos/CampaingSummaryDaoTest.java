package com.globant.gaetraining.addsincgae.daos;

import java.util.List;

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
	private CampaingSummaryDao dao;

	@Before
	public void setUp() {
		this.helper.setUp();
		this.dao = new CampaingSummaryDao();
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

	@Test
	public void findAllTest() {

		CampaignSummary campaingSummary = new CampaignSummary();
		campaingSummary.setTotalHits(10);
		campaingSummary = this.dao.persist(campaingSummary);

		List<CampaignSummary> result = this.dao.findAll(CampaignSummary.class);

		Assert.assertNotNull(result);

	}

}
