package com.globant.gaetraining.addsincgae.daos;

import java.util.Collection;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;

public abstract class GenericDao<T> {

	private static final PersistenceManagerFactory pmfInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	protected PersistenceManager getPM() {
		return pmfInstance.getPersistenceManager();
	}

	/**
	 * Persist an object of type T to the datastore
	 * 
	 * @param object
	 *            object to persist
	 * @return object persisted
	 */
	public T persist(T object) {

		PersistenceManager pm = this.getPM();

		try {
			object = pm.makePersistent(object);
		} finally {
			pm.close();
		}
		return object;

	}
	
	/**
	 * Persist a collection of objects of type T to the datastore
	 * 
	 * @param objects
	 *            objects to persist
	 * @return objects persisted
	 */
	public Collection<T> persistAll(Collection<T> objects) {


		PersistenceManager pm = this.getPM();

		try {
			objects = pm.makePersistentAll(objects);
		} finally {
			pm.close();
		}
		return objects;

	}
	
	/**
	 * Persist an array of objects of type T to the datastore
	 * 
	 * @param objects
	 *            objects to persist
	 * @return objects persisted
	 */
	public T[] persistAll(T[] objects) {

		PersistenceManager pm = this.getPM();

		try {
			objects = pm.makePersistentAll(objects);
		} finally {
			pm.close();
		}
		return objects;

	}

	/**
	 * Delete an object from the datastore
	 * 
	 * @param object
	 *            object to delete
	 */
	public void delete(T object) {

		PersistenceManager pm = this.getPM();

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
	 * @param fetchGroups
	 *            {@link List} con los {@link String} de los fetchgroups que se
	 *            le quieren aplicar a la carga
	 * @return
	 */
	public T findByKey(Key key, Class classType, List<String> fetchGroups) {

		PersistenceManager pm = this.getPM();

		T result = null;

		if (fetchGroups != null && !fetchGroups.isEmpty()) {
			for (String fetchGroup : fetchGroups) {
				pm.getFetchPlan().addGroup(fetchGroup);
			}
		}

		try {
			result = (T) pm.getObjectById(classType, key);
		}catch(JDOObjectNotFoundException notfounde){
			result = null;
		} finally {
			pm.close();
		}

		return result;

	}

	/**
	 * Find an object by his id
	 * 
	 * @param id
	 *            object's id/name in the datastore
	 * @param classType
	 *            {@link Class} of the object to retrieve Ex:
	 *            CampaignSummary.class
	 * @param fetchGroups
	 *            {@link List} con los {@link String} de los fetchgroups que se
	 *            le quieren aplicar a la carga
	 * @return
	 */
	public T findById(Object id, Class classType, List<String> fetchGroups) {

		PersistenceManager pm = this.getPM();

		T result = null;

		if (fetchGroups != null && !fetchGroups.isEmpty()) {
			for (String fetchGroup : fetchGroups) {
				pm.getFetchPlan().addGroup(fetchGroup);
			}
		}

		try {
			result = (T) pm.getObjectById(classType, id);
		}catch(JDOObjectNotFoundException notfounde){
			result = null;
		} finally {
			pm.close();
		}

		return result;

	}

	/**
	 * Find all the entity of the specified class
	 * 
	 * @param classType
	 *            {@link Class} of the object to retrieve Ex:
	 *            CampaignSummary.class
	 * @return {@link List} with entities found
	 */
	public List<T> findAll(Class classType) {

		PersistenceManager pm = this.getPM();

		List<T> result = null;

		Query q = pm.newQuery(classType);

		try {

			result = (List<T>) q.execute();

		} finally {
			pm.close();
		}

		return result;
	}

	public void update(T object) {
		throw new UnsupportedOperationException("Unimplemented method");
	}

}
