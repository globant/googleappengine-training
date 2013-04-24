package com.mall;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterPurchaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String endUser = getEndUserName();

		Key endUserKey = KeyFactory.createKey("EndUser", endUser);
		
		String amount = req.getParameter("amount");
		String cardNumber = req.getParameter("cardNumber");

		String balanceAfterTransaction = updateCardBalance(endUserKey, amount,
				cardNumber);
		
		
		saveTransaction(endUserKey, amount, balanceAfterTransaction);

		showSuccess(resp);
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

	private String updateCardBalance(Key endUserKey, String amount,
			String cardNumber) {
		
		Query query = new Query("ShoppingCard", endUserKey);
		query.setFilter(new FilterPredicate("code", FilterOperator.EQUAL, cardNumber));
	
		Entity card = DatastoreServiceFactory.getDatastoreService()
				.prepare(query).asSingleEntity();
		
		//Create new card Hack
		if (card == null) {
			card = new Entity("ShoppingCard", endUserKey);
			card.setProperty("code", cardNumber);
			card.setProperty("balance", "10000");
			
			DatastoreServiceFactory.getDatastoreService().put(card);
		}

		String balanceAfterTransaction = card.getProperty("balance") + "+"
				+ amount;
		card.setProperty("balance", balanceAfterTransaction);
		
		DatastoreServiceFactory.getDatastoreService().put(card);
		return balanceAfterTransaction;
	}

	private void showSuccess(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=UTF-8");
		ServletOutputStream out = resp.getOutputStream();
		out.println("Purchase registered");
	}

	private void saveTransaction(Key endUserKey, String amount,
			String balanceAfterTransaction) {
		
		Entity transaction = new Entity("Transaction", endUserKey);
		
		transaction.setProperty("timeStamp", new Date());
		transaction.setProperty("amount", amount);
		transaction.setProperty("balanceAfterTransaction",
				balanceAfterTransaction);

		DatastoreServiceFactory.getDatastoreService().put(transaction);
	}
}