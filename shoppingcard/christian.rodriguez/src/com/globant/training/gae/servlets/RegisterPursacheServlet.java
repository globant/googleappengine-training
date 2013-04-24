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
public class RegisterPursacheServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Double value = Double.parseDouble(req.getParameter("value"));
		String cardNumber = req.getParameter("card_number");

		if (value == null || cardNumber == null) {
			// Error null parameters
		}

		ShoppingCard shoppingCard = DaoShopingCard.INSTANCE
				.findByCode(cardNumber);

		if (shoppingCard != null) {

			Double balanceAfterTransaction = shoppingCard.getBalance() - value;

			if (balanceAfterTransaction > 0) {

				Transaction transaction = new Transaction(new Date(),
						balanceAfterTransaction, -value, shoppingCard.getKey());
				transaction = DaoTransaction.INSTANCE.persist(transaction);

				shoppingCard.setBalance(balanceAfterTransaction);
				shoppingCard = DaoShopingCard.INSTANCE.persist(shoppingCard);

			} else {
				// Error balance
			}

		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
		dispatcher.forward(req, resp);

	}

}
