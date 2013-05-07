package com.globant.gaetraining.addsincgae.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.FetchGroups;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
@FetchGroups({ @FetchGroup(name = "products", members = { @Persistent(name = "product") }) })
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
	private boolean active;

	@Persistent(mappedBy = "campaign")
	private List<Product> product;

	@Persistent
	private Key customerKey;

	@Persistent
	private List<Key> distributionChannelKeys;

	public Campaign(Key customerKey) {
		this.product = new ArrayList<Product>();
		this.distributionChannelKeys = new ArrayList<Key>();
		this.customerKey = customerKey;
		this.active = true;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
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

	/**
	 * Calculate the number of days left of the campaign
	 * 
	 * @return the number of days left for the campaign. -1 if there was a
	 *         problem doing the calculation
	 */
	public int getDaysLeft() {

		if (this.getEndDate() == null) {
			return -1;
		}

		return (int) ((new Date().getTime() - this.getEndDate().getTime()) / (1000 * 60 * 60 * 24));
	}

}
