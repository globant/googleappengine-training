package gaetraining.exercise2.service;

import gaetraining.exercise2.model.ShoppingCard;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Transaction;

public class TransactionService {

	private static final Logger logger = Logger
			.getLogger(TransactionService.class.getCanonicalName());
	private static final DateFormat df = new SimpleDateFormat(
			"yyyyMMdd HH:mm:ss");

	/**
	 * Register amount. If amount >= 0, it is a payment. Otherwise, it is a
	 * purchase.
	 */
	public static void registerAmount(String username, long amount) {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Transaction tx = ds.beginTransaction();

		try {
			Query q = new Query(ShoppingCard.KIND);
			q.setFilter(new FilterPredicate("endUser", FilterOperator.EQUAL,
					username));
			Entity shoppingCard = ds.prepare(q).asSingleEntity();

			long balance = (long) shoppingCard.getProperty("balance");

			Entity txEntity = new Entity(
					gaetraining.exercise2.model.Transaction.KIND,
					shoppingCard.getKey());
			txEntity.setProperty("timestamp", df.format(new Date()));
			long currentBalance = balance + amount;
			if (currentBalance < 0)
				throw new IllegalStateException(
						"Prepaid card has not cash enough");
			txEntity.setProperty("amount", amount);
			txEntity.setProperty("balanceAfterTransaction", currentBalance);
			txEntity.setProperty("cardCode", shoppingCard.getProperty("code"));

			ds.put(txEntity);

			shoppingCard.setProperty("balance", currentBalance);
			ds.put(shoppingCard);

			tx.commit();

		} catch (Exception e) {
			logger.throwing("TransactionDAO", "registerPayment", e);

		} finally {
			if (tx.isActive())
				tx.rollback();
		}
	}

	public static List<gaetraining.exercise2.model.Transaction> listTransactions(
			String username) {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query(ShoppingCard.KIND);
		q.setFilter(new FilterPredicate("endUser", FilterOperator.EQUAL,
				username));
		Entity shoppingCard = ds.prepare(q).asSingleEntity();

		q = new Query(gaetraining.exercise2.model.Transaction.KIND);
		q.setAncestor(shoppingCard.getKey()).addSort("timestamp",
				SortDirection.DESCENDING);

		List<gaetraining.exercise2.model.Transaction> txs = new ArrayList<gaetraining.exercise2.model.Transaction>();
		for (Entity e : ds.prepare(q).asIterable()) {
			gaetraining.exercise2.model.Transaction tx = new gaetraining.exercise2.model.Transaction();
			tx.setAmount((Long) e.getProperty("amount"));
			tx.setBalanceAfterTransaction((Long) e
					.getProperty("balanceAfterTransaction"));
			try {
				tx.setTimestamp(df.parse((String) e.getProperty("timestamp")));
			} catch (ParseException e1) {
				throw new RuntimeException(e1);
			}
			txs.add(tx);
		}

		return txs;

	}
}
