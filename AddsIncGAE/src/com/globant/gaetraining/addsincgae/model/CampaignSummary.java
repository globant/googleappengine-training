package com.globant.gaetraining.addsincgae.model;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.FetchGroups;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
@FetchGroups({
		@FetchGroup(name = "distributionChannelSummary", members = { @Persistent(name = "distributionChannelSummary") }),
		@FetchGroup(name = "productSummary", members = { @Persistent(name = "productSummary") }) })
public class CampaignSummary {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@Persistent
	private Key campaignKey;

	@Persistent
	private int totalHits;

	@Persistent
	private int totalViews;

	@Persistent(mappedBy = "campaignSummary")
	private List<DistributionChannelSummary> distributionChannelSummary;

	@Persistent(mappedBy = "campaignSummary")
	private List<ProductSummary> productSummary;

	@NotPersistent
	private String keyString;

	public void setKey(Key key) {
		this.key = key;
		this.keyString= KeyFactory.keyToString(key);
	}

	public String getKeyString() {
		if(keyString == null & key !=null){
			keyString= KeyFactory.keyToString(key);
		}
		return keyString;
	}

		public CampaignSummary() {
		this.distributionChannelSummary = new ArrayList<>();
		this.productSummary = new ArrayList<>();
	}

	public Key getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Key getCampaignKey() {
		return campaignKey;
	}

	public void setCampaignKey(Key campaignKey) {
		this.campaignKey = campaignKey;
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
