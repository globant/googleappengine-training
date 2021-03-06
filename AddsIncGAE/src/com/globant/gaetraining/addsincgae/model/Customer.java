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

@PersistenceCapable
public class Customer {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@Persistent
	private String legalName;

	@Persistent
	private List<Key> representative;

	@Persistent
	private List<Key> owners;

	@Persistent
	private String description;

	@Persistent
	private int employeesAmount;

	@NotPersistent
	private String keyString;
	
	@Persistent
	private BlobKey logo;

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

	
	public Key getKey(){
		return this.key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public List<Key> getRepresentative() {
		return representative;
	}

	public void setRepresentative(List<Key> representative) {
		this.representative = representative;
	}

	public List<Key> getOwners() {
		return owners;
	}

	public void setOwners(List<Key> owners) {
		this.owners = owners;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEmployeesAmount() {
		return employeesAmount;
	}

	public void setEmployeesAmount(int employeesAmount) {
		this.employeesAmount = employeesAmount;
	}

	public BlobKey getLogo() {
		return logo;
	}

	public void setLogo(BlobKey logo) {
		this.logo = logo;
	}
}
