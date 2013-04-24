package com.globant.training.gae.model;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class ShoppingCard {

	private String code;
	private Double balance;
	private Key endUserKey;
	private Key key;

	public ShoppingCard(String code, Double balance, Key endUserKey) {
		super();
		this.code = code;
		this.balance = balance;
		this.endUserKey = endUserKey;
	}

	public ShoppingCard(Entity entity) {
		super();
		this.code = (String) entity.getProperty("code");
		this.balance = (Double) entity.getProperty("balance");
		this.endUserKey = entity.getParent();
		this.key = entity.getKey();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Key getEndUserKey() {
		return endUserKey;
	}

	public void setEndUserKey(Key endUserKey) {
		this.endUserKey = endUserKey;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Entity toEntity() {

		Entity entity = new Entity(ShoppingCard.class.getSimpleName(),
				this.getCode(), this.getEndUserKey());

		entity.setProperty("code", this.getCode());
		entity.setProperty("balance", this.getBalance());

		return entity;
	}

}
