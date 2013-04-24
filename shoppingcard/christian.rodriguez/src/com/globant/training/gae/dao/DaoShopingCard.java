package com.globant.training.gae.dao;

import java.util.ArrayList;
import java.util.List;

import com.globant.training.gae.model.ShoppingCard;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

public enum DaoShopingCard {

	INSTANCE;

	private DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();

	public ShoppingCard persist(ShoppingCard shoppingCard) {

		Key key = Dao.INSTANCE.persistEntity(shoppingCard.toEntity());
		if (key != null) {
			shoppingCard.setKey(key);
			return shoppingCard;
		}
		return null;

	}

	public ShoppingCard findByCode(String value) {

		Query query = new Query(ShoppingCard.class.getSimpleName());

		query.addFilter("code", FilterOperator.EQUAL, value);

		List<Entity> results = datastore.prepare(query).asList(
				FetchOptions.Builder.withDefaults());
		if (!results.isEmpty()) {
			Entity entity = (Entity) results.remove(0);
			return new ShoppingCard(entity);
		}

		return null;
	}

	public List<ShoppingCard> findAll() {

		Query query = new Query(ShoppingCard.class.getSimpleName());

		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withDefaults());

		List<ShoppingCard> shoppingCards = null;

		if (entities != null && !entities.isEmpty()) {
			shoppingCards = new ArrayList<ShoppingCard>();

			for (Entity shoppingCard : entities) {
				shoppingCards.add(new ShoppingCard(shoppingCard));
			}
		}

		return shoppingCards;
	}

}
