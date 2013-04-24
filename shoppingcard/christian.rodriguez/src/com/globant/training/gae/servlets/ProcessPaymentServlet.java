package com.globant.training.gae.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.globant.training.gae.dao.DaoShopingCard;
import com.globant.training.gae.dao.DaoTransaction;
import com.globant.training.gae.model.ShoppingCard;
import com.globant.training.gae.model.Transaction;

@SuppressWarnings("serial")
public class ProcessPaymentServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String cardNumber = req.getParameter("cardNumber");
		Double amount = Double.valueOf(req.getParameter("value"));

		ShoppingCard shoppingCard = DaoShopingCard.INSTANCE
				.findByCode(cardNumber);

		if (shoppingCard != null) {

			Double balanceAfterTransaction = shoppingCard.getBalance() + amount;

			Transaction transaction = new Transaction(new Date(),
					balanceAfterTransaction, amount, shoppingCard.getKey());
			transaction = DaoTransaction.INSTANCE.persist(transaction);

			shoppingCard.setBalance(balanceAfterTransaction);
			shoppingCard = DaoShopingCard.INSTANCE.persist(shoppingCard);
			
			// Send mail

		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
		dispatcher.forward(req, resp);

	}

}
