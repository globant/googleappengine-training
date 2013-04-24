package gaetraining.exercise2;

import gaetraining.exercise2.service.TransactionService;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterPurchaseServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		showView(req, resp);
	}

	private void showView(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		long amount = Long.valueOf(req.getParameter("amount"));
		
		TransactionService.registerAmount(username, -amount);
		
		RequestDispatcher rd = req.getRequestDispatcher("/");
		rd.forward(req, resp);
	}

}
