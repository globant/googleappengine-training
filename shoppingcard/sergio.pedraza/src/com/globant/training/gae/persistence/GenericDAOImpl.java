package com.globant.training.gae.persistence;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class GenericDAOImpl implements IGenericDAO {

	protected DatastoreService dataStore;

	protected MemcacheService keycache;

	public GenericDAOImpl() {
		dataStore = DatastoreServiceFactory.getDatastoreService();
		keycache = MemcacheServiceFactory.getMemcacheService();
	}

	@Override
	public void saveEntity(Entity e) {

		dataStore.put(e);

	}

	@Override
	public void deleteEntity(Key key) {
		Transaction txn = dataStore.beginTransaction();
		try {
			dataStore.delete(key);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}

	}

	@Override
	public Entity getEntity(Key key) {

		Entity e =  null;
		try {
			e = dataStore.get(key);
		} catch (EntityNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			return e;
		}
	}

}
