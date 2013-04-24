package com.globant.training.gae.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.globant.training.gae.model.EndUser;
import com.globant.training.gae.model.ShoppingCard;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class DaoShoppingCardTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	private final String END_USER_NAME = "Christian";
	private final String CREDIT_CARD_ID = "AX54E20-534BCD";

	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	@Test
	public void persistValidShoppingCard() {

		EndUser user = new EndUser(END_USER_NAME.concat("2"));
		Key keyEndUser = Dao.INSTANCE.persistEntity(user.toEntity());

		ShoppingCard shoppingCard = new ShoppingCard(this.CREDIT_CARD_ID,
				new Double(123456789012345689.0), keyEndUser);
		DaoShopingCard.INSTANCE.persist(shoppingCard);

	}

	@Test
	public void listAllShoppingCards() {

		EndUser user = new EndUser(END_USER_NAME.concat("2"));
		Key keyEndUser = Dao.INSTANCE.persistEntity(user.toEntity());

		ShoppingCard shoppingCard = new ShoppingCard(this.CREDIT_CARD_ID,
				new Double(123456789012345689.0), keyEndUser);
		DaoShopingCard.INSTANCE.persist(shoppingCard);

		List<ShoppingCard> shopppingCards = null;
		shopppingCards = DaoShopingCard.INSTANCE.findAll();

		Assert.assertNotNull(shopppingCards);
	}
}
