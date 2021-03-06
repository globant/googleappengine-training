package com.globant.training.gae.dao;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.globant.training.gae.model.EndUser;
import com.globant.training.gae.model.ShoppingCard;
import com.globant.training.gae.model.Transaction;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class DaoTransactionTest {

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
	public void persistValidTrasaction() {

		EndUser user = new EndUser(END_USER_NAME.concat("3"));
		Key keyEndUser = Dao.INSTANCE.persistEntity(user.toEntity());

		ShoppingCard shoppingCard = new ShoppingCard(this.CREDIT_CARD_ID,
				new Double(123456789012345689.0), keyEndUser);
		Key keyUserCard = Dao.INSTANCE.persistEntity(shoppingCard.toEntity());

		Transaction transaction = new Transaction(new Date(), 123123213.00,
				1000.00, keyUserCard);
		Dao.INSTANCE.persistEntity(transaction.toEntity());

	}

	@Test
	public void listAllShoppingCards() {

		EndUser user = new EndUser(END_USER_NAME.concat("3"));
		Key keyEndUser = Dao.INSTANCE.persistEntity(user.toEntity());

		ShoppingCard shoppingCard = new ShoppingCard(this.CREDIT_CARD_ID,
				new Double(123456789012345689.0), keyEndUser);
		Key keyUserCard = Dao.INSTANCE.persistEntity(shoppingCard.toEntity());

		Transaction transaction = new Transaction(new Date(), 123123213.00,
				1000.00, keyUserCard);
		Dao.INSTANCE.persistEntity(transaction.toEntity());

		List<Transaction> transactions = null;
		transactions = DaoTransaction.INSTANCE.findAll();

		Assert.assertNotNull(transactions);
	}
}
