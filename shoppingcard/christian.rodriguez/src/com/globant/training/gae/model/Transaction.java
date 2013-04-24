package com.globant.training.gae.model;

import java.util.Date;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class Transaction {

	private Date timeStamp;
	private Double balanceAfterTransaction;
	private Double amount;
	private Key shoppingCardKey;
	private Key key;

	public Transaction(Date timeStamp, Double balanceAfterTransaction,
			Double amount, Key shoppingCardKey) {
		super();
		this.timeStamp = timeStamp;
		this.balanceAfterTransaction = balanceAfterTransaction;
		this.amount = amount;
		this.shoppingCardKey = shoppingCardKey;
	}

	public Transaction(Entity entity) {
		super();
		this.timeStamp = (Date) entity.getProperty("timeStamp");
		this.balanceAfterTransaction = (Double) entity
				.getProperty("balanceAfterTransaction");
		this.amount = (Double) entity.getProperty("amount");
		this.shoppingCardKey = entity.getParent();
		this.key = entity.getKey();
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Double getBalanceAfterTransaction() {
		return balanceAfterTransaction;
	}

	public void setBalanceAfterTransaction(Double balanceAfterTransaction) {
		this.balanceAfterTransaction = balanceAfterTransaction;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Key getShoppingCardKey() {
		return shoppingCardKey;
	}

	public void setShoppingCardKey(Key shoppingCardKey) {
		this.shoppingCardKey = shoppingCardKey;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Entity toEntity() {

		Entity entity = new Entity(Transaction.class.getSimpleName(),
				this.getShoppingCardKey());

		entity.setProperty("timeStamp", this.getTimeStamp());
		entity.setProperty("balanceAfterTransaction",
				this.getBalanceAfterTransaction());
		entity.setProperty("amount", this.getAmount());

		return entity;
	}

}
