package com.globant.gaetraining.addsincgae.model;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

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
	private List<User> representative;

	@Persistent
	private List<User> owners;

	@Persistent
	private String description;

	@Persistent
	private int employeesAmount;

	public void setKey(Key key) {
		this.key = key;
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

	public List<User> getRepresentative() {
		return representative;
	}

	public void setRepresentative(List<User> representative) {
		this.representative = representative;
	}

	public List<User> getOwners() {
		return owners;
	}

	public void setOwners(List<User> owners) {
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
}
