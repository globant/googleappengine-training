package com.globant.gaetraining.addsincgae.model;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Campaign {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@Persistent
	private Date startDate;

	@Persistent
	private Date endDate;

	@Persistent
	private Product product;

	@Persistent
	private Key customerKey;

	@Persistent
	private List<Key> distributionChannelKeys;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Key getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(Key customerKey) {
		this.customerKey = customerKey;
	}

	public List<Key> getDistributionChannelKeys() {
		return distributionChannelKeys;
	}

	public void setDistributionChannelKeys(List<Key> distributionChannelKeys) {
		this.distributionChannelKeys = distributionChannelKeys;
	}

}
