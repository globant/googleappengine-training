package com.globant.gaetraining.addsincgae.model;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
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
	private Key campaignKey;
	private String campaign_key;
	
	@Persistent
	private BlobKey photo_path;

	public Product() {

	}

	public Product(Campaign campaign) {
		this.campaign = campaign;
		this.campaignKey = campaign.getKey();
	}
	
	public Product(String name, String shortDescription, 
			String longDescription, String productUrl, String country,
			BlobKey productPhoto, Campaign campaign){
		this(campaign);
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.url = productUrl;
		this.country = country;
	}

	public Key getKey() {
		return key;
	}

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
		this.campaignKey = campaign.getKey();
		this.campaign.getProduct().add(this);
	}

	public String getCampaign_key() {
		return campaign_key;
	}

	public void setCampaign_key(String campaign_key) {
		this.campaign_key = campaign_key;
	}

	public BlobKey getPhoto_path() {
		return photo_path;
	}

	public void setPhoto_path(BlobKey photo_path) {
		this.photo_path = photo_path;
	}	

}
