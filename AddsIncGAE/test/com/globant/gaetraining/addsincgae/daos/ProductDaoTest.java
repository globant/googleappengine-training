package com.globant.gaetraining.addsincgae.daos;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.Product;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class ProductDaoTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	private CampaignDao daoCampaign;

	@Before
	public void setUp() {
		this.helper.setUp();
		this.daoCampaign = new CampaignDao();
	}

	@After
	public void tearDown() {
		this.helper.tearDown();
	}

}
