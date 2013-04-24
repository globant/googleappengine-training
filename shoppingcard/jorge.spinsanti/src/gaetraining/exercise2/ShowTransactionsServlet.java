package gaetraining.exercise2;

import gaetraining.exercise2.model.Transaction;
import gaetraining.exercise2.service.TransactionService;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowTransactionsServlet extends HttpServlet {

	private static final DateFormat df = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss");

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		showView(req, resp);
	}

	private void showView(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");

		StringBuilder builder = new StringBuilder("Username: ")
				.append(username).append("\n");
		PrintWriter writer = resp.getWriter();
		for (Transaction tx : TransactionService.listTransactions(username)) {
			builder.append("Amount: ").append(tx.getAmount())
					.append(" | Balance After Tx: ")
					.append(tx.getBalanceAfterTransaction())
					.append(" | Timestamp: ")
					.append(df.format(tx.getTimestamp())).append("\n");
		}

		writer.println(builder.toString());
	}

}
