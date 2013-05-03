package com.globant.gaetraining.addsincgae.model;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable
public class Product {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@Persistent
	private List<String> productFamily;

	@Persistent
	private String shortDescription;

	@Persistent
	private String longDescription;

	@Persistent
	private String url;

	@Persistent
	private String country;

	@Persistent
	@Unowned
	private Campaign campaign;

	@Persistent
	private String campaign_key;

	public Product() {

	}

	public Product(Campaign campaign) {
		this.campaign = campaign;
		this.campaign_key = campaign.getKey().toString();
	}

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

	public List<String> getProductFamily() {
		return productFamily;
	}

	public void setProductFamily(List<String> productFamily) {
		this.productFamily = productFamily;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
		this.campaign_key = campaign.getKey().toString();
		this.campaign.getProduct().add(this);
	}

}
