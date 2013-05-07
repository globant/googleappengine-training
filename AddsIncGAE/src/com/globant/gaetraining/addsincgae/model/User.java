package com.globant.gaetraining.addsincgae.model;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class User {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String userName;
	
	@Persistent
	private List<String> roles;
	
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

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}


}
