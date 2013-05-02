package com.globant.gaetraining.addsincgae.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DistributionChannelSummary {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@Persistent
	private int totalHits;

	@Persistent
	private int totalViews;

	@Persistent
	private Key distributionChannelKey;

	@Persistent
	private CampaignSummary campaignSummary;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Key getDistributionChannelKey() {
		return distributionChannelKey;
	}

	public void setDistributionChannelKey(Key distributionChannelKey) {
		this.distributionChannelKey = distributionChannelKey;
	}

	public CampaignSummary getCampaignSummary() {
		return campaignSummary;
	}

	public void setCampaignSummary(CampaignSummary campaignSummary) {
		this.campaignSummary = campaignSummary;
	}

}
