package com.globant.gaetraining.addsincgae.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class DistributionChannel {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String name;
	
	@Persistent
	private String mediaType;
	
	@Persistent
	private String template;
	
	@Persistent
	private String country;

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

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
