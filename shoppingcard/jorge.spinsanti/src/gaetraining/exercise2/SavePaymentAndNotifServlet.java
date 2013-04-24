package gaetraining.exercise2;

import gaetraining.exercise2.service.TransactionService;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SavePaymentAndNotifServlet extends HttpServlet {

	private static final Logger logger = Logger
			.getLogger(SavePaymentAndNotifServlet.class.getCanonicalName());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		long amount = Long.parseLong(req.getParameter("amount"));
		logger.info(String.format(
				"save payment for username %s and amount %d", username,
				amount));
		TransactionService.registerAmount(username, amount);
	}
}
