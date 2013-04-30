package com.globant.gaetraining.addsincgae.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Event {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private char type;

	@Persistent
	private Key productKey;

	@Persistent
	private Key distributionChannelKey;

	@Persistent
	private String host;

	@Persistent
	private Date timeStamp;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
	
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
