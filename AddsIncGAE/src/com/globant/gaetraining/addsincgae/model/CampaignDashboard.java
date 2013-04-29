package com.globant.gaetraining.addsincgae.model;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

public class CampaignDashboard {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private Key campaingKey;

	@Persistent
	private int totalHits;

	@Persistent
	private int totalViews;

	@Persistent
	private List<DistributionChannelDashboard> distributionChannelDashboard;

	@Persistent
	private List<ProductDashboard> productDashboard;

	@Persistent
	private List<Event> event;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Key getCampaingKey() {
		return campaingKey;
	}

	public void setCampaingKey(Key campaingKey) {
		this.campaingKey = campaingKey;
	}

	public int getTotalHits() {
		return totalHits;
	}

	public void setTotalHits(int totalHits) {
		this.totalHits = totalHits;
	}

	public int getTotalViews() {
		return totalViews;
	}

	public void setTotalViews(int totalViews) {
		this.totalViews = totalViews;
	}

	public List<DistributionChannelDashboard> getDistributionChannelDashboard() {
		return distributionChannelDashboard;
	}

	public void setDistributionChannelDashboard(
			List<DistributionChannelDashboard> distributionChannelDashboard) {
		this.distributionChannelDashboard = distributionChannelDashboard;
	}

	public List<ProductDashboard> getProductDashboard() {
		return productDashboard;
	}

	public void setProductDashboard(List<ProductDashboard> productDashboard) {
		this.productDashboard = productDashboard;
	}

	public List<Event> getEvent() {
		return event;
	}

	public void setEvent(List<Event> event) {
		this.event = event;
	}

}
