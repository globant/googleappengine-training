package com.globant.training.gae.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.globant.training.gae.dao.Dao;
import com.globant.training.gae.dao.DaoShopingCard;
import com.globant.training.gae.model.EndUser;
import com.globant.training.gae.model.ShoppingCard;
import com.google.appengine.api.datastore.Key;

@SuppressWarnings("serial")
public class CreateExampleDataServlet extends HttpServlet {

	private final String END_USER_NAME = "Christian Rodriguez";
	private final String CREDIT_CARD_ID = "AX54E20-534BCD";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		EndUser user = new EndUser(END_USER_NAME);
		Key endUserKey = Dao.INSTANCE.persistEntity(user.toEntity());

		ShoppingCard shoppingCard = new ShoppingCard(this.CREDIT_CARD_ID,
				new Double(10000000.0), endUserKey);
		shoppingCard = DaoShopingCard.INSTANCE.persist(shoppingCard);

		RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
		dispatcher.forward(req, resp);
	}

}
