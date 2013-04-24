package gaetraining.exercise2.model;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class Mapper {

	public static final Entity createEndUserEntity(EndUser eu) {
		Entity e = new Entity(EndUser.KIND);
		e.setProperty("username", eu.getUsername());
		return e;
	}

	public static final Entity createShoppingCardEntity(ShoppingCard sc,
			Key parentKey) {
		Entity e = new Entity(ShoppingCard.KIND, parentKey);
		e.setProperty("code", sc.getCode());
		e.setProperty("balance", sc.getBalance());
		e.setProperty("endUser", sc.getEndUser().getUsername());

		return e;
	}
}
