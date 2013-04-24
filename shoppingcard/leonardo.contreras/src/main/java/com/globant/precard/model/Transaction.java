package com.globant.precard.model;

import java.util.Date;

public class Transaction {

	private String id;
	private Date timestamp;
	private Integer balanceAfterTransaction;
	private Integer ammount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getBalanceAfterTransaction() {
		return balanceAfterTransaction;
	}

	public void setBalanceAfterTransaction(Integer balanceAfterTransaction) {
		this.balanceAfterTransaction = balanceAfterTransaction;
	}

	public Integer getAmmount() {
		return ammount;
	}

	public void setAmmount(Integer ammount) {
		this.ammount = ammount;
	}

}
