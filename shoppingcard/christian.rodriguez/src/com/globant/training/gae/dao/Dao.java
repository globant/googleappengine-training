package com.globant.training.gae.dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public enum Dao {

	INSTANCE;

	private DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();

	public Key persistEntity(Entity entity) {

		Key key = null;

		Transaction txn = datastore.beginTransaction();

		try {
			key = datastore.put(entity);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}

		return key;
	}

}
