package gaetraining.exercise2;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

public class RegisterPaymentServlet extends HttpServlet {

	private static final Logger logger = Logger
			.getLogger(RegisterPaymentServlet.class.getCanonicalName());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		showView(req, resp);
	}

	private void showView(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String amount = req.getParameter("amount");

		logger.info(String.format(
				"Queueing payment for username %s and amount %s", username,
				amount));
		Queue queue = QueueFactory.getQueue("PaymentQueue");
		TaskOptions to = TaskOptions.Builder.withUrl("/savePaymentAndNotif")
				.param("username", username)
				.param("amount", amount)
				.method(Method.POST);
		queue.add(to);

		RequestDispatcher rd = req.getRequestDispatcher("/");
		rd.forward(req, resp);
	}
}
