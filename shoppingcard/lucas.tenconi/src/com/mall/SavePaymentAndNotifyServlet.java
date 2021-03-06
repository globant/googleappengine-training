package com.mall;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SavePaymentAndNotifyServlet extends HttpServlet {

	private static final int CARD_LIMIT = 10000;
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		long amount = Long.valueOf(req.getParameter("amount"));
		String cardNumber = req.getParameter("cardNumber");
		String endUser = getEndUserName();
		Key endUserKey = KeyFactory.createKey("EndUser", endUser);
		Entity card = getUserCard(endUserKey, cardNumber);

		long balanceAfterTransaction = (long) card.getProperty("balance")
				+ amount;

		if (balanceAfterTransaction > CARD_LIMIT) {

			resp.setContentType("text/html;charset=UTF-8");
			ServletOutputStream out = resp.getOutputStream();
			out.println("card limit exceeded!");
			return;
		}

		Queue queue = QueueFactory.getDefaultQueue();
		TaskOptions taskOptions = TaskOptions.Builder
				.withUrl("/savePaymentAndNotify")
				.param("amount", req.getParameter("amount"))
				.param("cardNumber", req.getParameter("cardNumber"))
				.method(Method.PUT);
		queue.add(taskOptions);

		resp.setContentType("text/html;charset=UTF-8");
		ServletOutputStream out = resp.getOutputStream();
		out.println("Payment Enqueued");
	}

	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Transaction tx = DatastoreServiceFactory.getDatastoreService()
				.beginTransaction();

		long amount = Long.valueOf(req.getParameter("amount"));
		String cardNumber = req.getParameter("cardNumber");

		String endUser = getEndUserName();
		Key endUserKey = KeyFactory.createKey("EndUser", endUser);
		Entity card = getUserCard(endUserKey, cardNumber);

		// Create new card Hack
		if (card == null) {
			card = new Entity("ShoppingCard", endUserKey);
			card.setProperty("code", cardNumber);
			card.setProperty("balance", 0L);

			DatastoreServiceFactory.getDatastoreService().put(card);
		}

		long balanceAfterTransaction = (long) card.getProperty("balance")
				+ amount;
		card.setProperty("balance", balanceAfterTransaction);

		DatastoreServiceFactory.getDatastoreService().put(card);

		saveTransaction(card.getKey(), amount, balanceAfterTransaction);

		tx.commit();

		sendEmail();
	}

	private Entity getUserCard(Key endUserKey, String cardNumber) {
		Query query = new Query("ShoppingCard", endUserKey);
		query.setFilter(new FilterPredicate("code", FilterOperator.EQUAL,
				cardNumber));

		Entity card = DatastoreServiceFactory.getDatastoreService()
				.prepare(query).asSingleEntity();
		return card;
	}

	private String getEndUserName() {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String endUser = "anonymous";

		if (user != null) {
			endUser = user.getUserId();
		}
		return endUser;
	}

	private void sendEmail() {
		// TODO
	}

	private void saveTransaction(Key cardKey, long amount,
			long balanceAfterTransaction) {

		Entity transaction = new Entity("Transaction", cardKey);

		transaction.setProperty("timeStamp", new Date());
		transaction.setProperty("amount", amount);
		transaction.setProperty("balanceAfterTransaction",
				balanceAfterTransaction);

		DatastoreServiceFactory.getDatastoreService().put(transaction);
	}
}