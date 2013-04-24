package com.globant.training.gae.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.backends.BackendServiceFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

@SuppressWarnings("serial")
public class RegisterPaymentServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String value = req.getParameter("value");
		String cardNumber = req.getParameter("card_number");

		if (value == null || cardNumber == null) {
			// Show Error null parameters
		}

		// Create Task and push it into Task Queue
		Queue queue = QueueFactory.getQueue("PaymentQueue");

		TaskOptions taskOptions = TaskOptions.Builder
				.withUrl("/process_payment")
				.param("cardNumber", cardNumber)
				.param("value", value)
				.header("Host",
						BackendServiceFactory.getBackendService()
								.getBackendAddress("payment-backend"))
				.method(Method.POST);

		queue.add(taskOptions);

		RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
		dispatcher.forward(req, resp);

	}

}
