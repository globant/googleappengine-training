package com.globant.gaetraining.addsincgae.daos;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.Customer;
import com.globant.gaetraining.addsincgae.model.Product;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class CampaignDaoTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());
	private CampaignDao dao;
	private CustomerDao daoCustomer;

	@Before
	public void setUp() {
		this.helper.setUp();
		this.dao = new CampaignDao();
		this.daoCustomer = new CustomerDao();
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
		Product prod;
		for (int i = 0; i < 100; i++) {
			prod = new Product(camp);
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
	public void persistCampaignCustomerAndProducts() {

		Customer customer = new Customer();
		customer.setName("Test Man!!");
		customer = this.daoCustomer.persist(customer);

		Campaign camp = new Campaign();
		camp.setName("MockParent");
		camp.setCustomerKey(customer.getKey());

		Product prod;
		for (int i = 0; i < 100; i++) {
			prod = new Product(camp);
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

	@Test
	public void findActiveByCustomerKey() {

		Customer customer = new Customer();
		customer.setName("Test Man A!!");
		customer = this.daoCustomer.persist(customer);

		// Active campaign
		Campaign campA = new Campaign();
		campA.setName("MockParent A");
		campA.setProduct(new ArrayList<Product>());
		Product prod;
		for (int i = 0; i < 100; i++) {
			prod = new Product(campA);
			prod.setName(Integer.toString(i));
			prod.setShortDescription("SD");
			prod.setLongDescription("Long description");
			prod.setUrl("http://jkjk.com/");
			campA.getProduct().add(prod);
		}
		campA.setCustomerKey(customer.getKey());
		campA.setActive(true);

		// Inactive campaign
		Campaign campB = new Campaign();
		campB.setName("MockParent B");
		campB.setCustomerKey(customer.getKey());
		campB.setActive(false);

		// Active campaign without customer
		Campaign campC = new Campaign();
		campC.setName("MockParent C");
		campC.setActive(true);

		campA = dao.persist(campA);
		campB = dao.persist(campB);
		campC = dao.persist(campC);

		List<Campaign> campaigns = dao.findActiveByCustomerKey(customer
				.getKey());

		Assert.assertEquals(1, campaigns.size());
	}
}
