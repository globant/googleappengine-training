package com.globant.gaetraining.addsincgae.model;

import java.util.Date;

import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;

public class Event {

	@Persistent
	private char type;

	@Persistent
	private Key productKey;

	@Persistent
	private Key distributionChannelKey;

	@Persistent
	private String country;

	@Persistent
	private String host;

	@Persistent
	private Date timeStamp;

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public Key getProductKey() {
		return productKey;
	}

	public void setProductKey(Key productKey) {
		this.productKey = productKey;
	}

	public Key getDistributionChannelKey() {
		return distributionChannelKey;
	}

	public void setDistributionChannelKey(Key distributionChannelKey) {
		this.distributionChannelKey = distributionChannelKey;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
