package com.globant.training.gae.model;

import com.google.appengine.api.datastore.Entity;

public class EndUser {

	private String userName;

	public EndUser(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Entity toEntity() {

		Entity entity = new Entity(EndUser.class.getSimpleName(),
				this.getUserName());
		entity.setProperty("userName", this.getUserName());

		return entity;
	}

}
