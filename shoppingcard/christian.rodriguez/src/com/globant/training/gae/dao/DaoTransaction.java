package com.globant.training.gae.dao;

import java.util.ArrayList;
import java.util.List;

import com.globant.training.gae.model.Transaction;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

public enum DaoTransaction {

	INSTANCE;

	private DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();

	public Transaction persist(Transaction transaction) {

		Key key = Dao.INSTANCE.persistEntity(transaction.toEntity());
		if (key != null) {
			transaction.setKey(key);
			return transaction;
		}
		return null;

	}

	public List<Transaction> findAll() {

		Query query = new Query(Transaction.class.getSimpleName());

		query.addSort("timeStamp", SortDirection.DESCENDING);

		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withDefaults());

		List<Transaction> transactions = null;

		if (entities != null && !entities.isEmpty()) {
			transactions = new ArrayList<Transaction>();

			for (Entity transaction : entities) {
				transactions.add(new Transaction(transaction));
			}
		}

		return transactions;
	}

}
