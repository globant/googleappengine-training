package com.globant.gaetraining.addsincgae.daos;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;

public class GenericDao<T> {

	/**
	 * Persist an object of type T to the datastore
	 * 
	 * @param object
	 *            object to persist
	 * @return object persisted
	 */
	public T persist(T object) {

		PersistenceManager pm = PMF.getPM();

		try {
			object = pm.makePersistent(object);
		} finally {
			pm.close();
		}
		return object;

	}

	/**
	 * Delete an object from the datastore
	 * 
	 * @param object
	 *            object to delete
	 */
	public void delete(T object) {

		PersistenceManager pm = PMF.getPM();

		try {
			pm.deletePersistent(object);
		} finally {
			pm.close();
		}
	}

	/**
	 * Find an object by his {@link Key}
	 * 
	 * @param key
	 *            object's {@link Key} in the datastore
	 * @param classType
	 *            {@link Class} of the object to retrieve Ex:
	 *            CampaignSummary.class
	 * @return
	 */
	public T findByKey(Key key, Class classType) {

		PersistenceManager pm = PMF.getPM();

		T result = null;

		try {
			result = (T) pm.getObjectById(classType, key);
		} finally {
			pm.close();
		}

		return result;

	}

	public void update(T object) {
		throw new UnsupportedOperationException("Unimplemented method");
	}

}
