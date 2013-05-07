package com.globant.gaetraining.addsincgae.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globant.gaetraining.addsincgae.daos.CampaignDao;
import com.globant.gaetraining.addsincgae.daos.CampaignSummaryDao;
import com.globant.gaetraining.addsincgae.daos.CustomerDao;
import com.globant.gaetraining.addsincgae.daos.DistributionChannelDao;
import com.globant.gaetraining.addsincgae.daos.ProductSummaryDao;
import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.CampaignSummary;
import com.globant.gaetraining.addsincgae.model.Customer;
import com.globant.gaetraining.addsincgae.model.DistributionChannel;
import com.globant.gaetraining.addsincgae.model.DistributionChannelSummary;
import com.globant.gaetraining.addsincgae.model.Product;
import com.globant.gaetraining.addsincgae.model.ProductSummary;
import com.google.appengine.api.datastore.Key;

@Controller
public class DummyDataLoader {

	@Autowired
	private CampaignDao daoCampaign = new CampaignDao();

	@Autowired
	private CustomerDao daoCustomer = new CustomerDao();

	@Autowired
	private DistributionChannelDao daoDistributionChannel = new DistributionChannelDao();

	@Autowired
	private CampaignSummaryDao daoCampaignSummary = new CampaignSummaryDao();

	@Autowired
	private ProductSummaryDao daoProductSummary = new ProductSummaryDao();

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public String loadDummyData(Map<String, Object> model)
			throws ParseException {

		Customer customer = this.loadCustomerData();
		Campaign campaign = this.loadCampaignData(customer);
		campaign = this.loadProductsData(campaign);
		campaign = this.loadDistributionChannelData(campaign);
		CampaignSummary campaignSummary = this
				.loadCampaignSummaryData(campaign);
		campaignSummary = this.loadProductSummaryData(campaignSummary,
				campaign.getProduct());
		campaignSummary = this.loadDistributionChannelSummaryData(
				campaignSummary, campaign.getDistributionChannelKeys());
		return "home";
	}

	private Customer loadCustomerData() {
		Customer customer = new Customer();
		customer.setName("Dummy Man !!!");
		customer = this.daoCustomer.persist(customer);
		return customer;
	}

	private Campaign loadCampaignData(Customer customer) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date startDate = formatter.parse("01/01/13");
		Date endDate = formatter.parse("12/31/13");

		Campaign campaign = new Campaign(customer.getKey());
		campaign.setName("Dummy campaign A");
		campaign.setCustomerKey(customer.getKey());
		campaign.setActive(true);
		campaign.setStartDate(startDate);
		campaign.setEndDate(endDate);
		campaign = this.daoCampaign.persist(campaign);
		return campaign;
	}

	private Campaign loadProductsData(Campaign campaign) {
		Product prod;
		for (int i = 0; i < 8; i++) {
			prod = new Product(campaign);
			prod.setName(Integer.toString(i));
			prod.setShortDescription("SD");
			prod.setLongDescription("Long description");
			prod.setUrl("http://jkjk.com/");
			campaign.getProduct().add(prod);
		}
		return this.daoCampaign.persist(campaign);
	}

	private Campaign loadDistributionChannelData(Campaign campaign) {
		for (int i = 0; i < 3; i++) {
			DistributionChannel distributionChannel = new DistributionChannel();
			distributionChannel.setName("Panasonic XLVC" + i);
			distributionChannel.setMediaType("TV" + i);
			distributionChannel.setTemplate("<a>{text}</a>");
			distributionChannel = daoDistributionChannel
					.add(distributionChannel);
			campaign.getDistributionChannelKeys().add(
					distributionChannel.getKey());
		}
		return this.daoCampaign.persist(campaign);
	}

	private CampaignSummary loadCampaignSummaryData(Campaign campaign) {
		CampaignSummary summary = new CampaignSummary();
		summary.setCampaignKey(campaign.getKey());
		summary.setName(campaign.getName());
		summary.setTotalHits(20);
		summary.setTotalViews(50);
		return this.daoCampaignSummary.persist(summary);

	}

	private CampaignSummary loadProductSummaryData(CampaignSummary summary,
			List<Product> products) {
		ProductSummary productSummary;
		for (Product product : products) {
			productSummary = new ProductSummary(summary);
			productSummary.setName(product.getName());
			productSummary.setProductKey(product.getKey());
			productSummary.setTotalHits(50);
			productSummary.setTotalViews(60);
			summary.getProductSummary().add(productSummary);
		}
		return this.daoCampaignSummary.persist(summary);
	}

	private CampaignSummary loadDistributionChannelSummaryData(
			CampaignSummary summary, List<Key> distributionChannels) {

		DistributionChannelSummary distributionChannelSummary;
		for (Key distributionChannelKey : distributionChannels) {
			distributionChannelSummary = new DistributionChannelSummary(summary);
			distributionChannelSummary
					.setName(distributionChannelKey.getName());
			distributionChannelSummary
					.setDistributionChannelKey(distributionChannelKey);
			distributionChannelSummary.setTotalHits(10);
			distributionChannelSummary.setTotalViews(20);
			summary.getDistributionChannelSummary().add(
					distributionChannelSummary);
		}
		return this.daoCampaignSummary.persist(summary);
	}

}
