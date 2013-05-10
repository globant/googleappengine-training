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
	private Campaign campaign;
	
	@Unowned
	private Key campaignKey;
	
	@Persistent
	private BlobKey productPhoto;

	public Product() {

	}

	public Product(Campaign campaign) {
		this.campaign = campaign;
		this.campaignKey = campaign.getKey();
	}
	
	/**
	 * Contruts a Product object
	 * @param name
	 * @param shortDescription
	 * @param longDescription
	 * @param productUrl
	 * @param country
	 * @param productPhoto
	 * @param campaign
	 */
	public Product(String name, String shortDescription, 
			String longDescription, String productUrl, String country,
			BlobKey productPhoto, Campaign campaign){
		this(campaign);
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.url = productUrl;
		this.country = country;
		this.productPhoto = productPhoto;
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
		this.campaign.getProduct().add(this);
	}

	public BlobKey getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(BlobKey productPhoto) {
		this.productPhoto = productPhoto;
	}

	public Key getCampaignKey() {
		return campaignKey;
	}

	public void setCampaignKey(Key campaignKey) {
		this.campaignKey = campaignKey;
	}	

}
