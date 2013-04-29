package com.globant.gaetraining.addsincgae.model;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class CampaignSummary {

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
	private List<DistributionChannelSummary> distributionChannelSummary;

	@Persistent
	private List<ProductSummary> productSummary;

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

	public List<DistributionChannelSummary> getDistributionChannelSummary() {
		return distributionChannelSummary;
	}

	public void setDistributionChannelSummary(
			List<DistributionChannelSummary> distributionChannelSummary) {
		this.distributionChannelSummary = distributionChannelSummary;
	}

	public List<ProductSummary> getProductSummary() {
		return productSummary;
	}

	public void setProductSummary(List<ProductSummary> productSummary) {
		this.productSummary = productSummary;
	}

}
