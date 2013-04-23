package com.mall;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

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

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Queue queue = QueueFactory.getDefaultQueue();
		TaskOptions taskOptions = TaskOptions.Builder.withUrl("/savePaymentAndNotify").param(
				"amount", req.getParameter("amount")).method(Method.PUT);
		queue.add(taskOptions);
		
		resp.setContentType("text/html;charset=UTF-8"); 
		ServletOutputStream out = resp.getOutputStream(); 
		out.println("Payment Enqueued"); 
	}

	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String endUser = "anonymous";

		if (user != null) {
			endUser = user.getUserId();
		}

		Key endUserKey = KeyFactory.createKey("EndUser", endUser);
		String amount = req.getParameter("amount");

		Entity transaction = new Entity("Transaction", endUserKey);
		transaction.setProperty("timeStamp", new Date());
		transaction.setProperty("amount", amount);

		DatastoreServiceFactory.getDatastoreService().put(transaction);
	}
}