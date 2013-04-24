package com.globant.training.gae.business;

public enum EntitiesKinds {
	
	SHOPCARD("ShoppingCard"),
	USER("user"),
	TRAN("Transaction");
	
	private final String name;

	private EntitiesKinds(String name){
		this.name = name;
	}
			
}
