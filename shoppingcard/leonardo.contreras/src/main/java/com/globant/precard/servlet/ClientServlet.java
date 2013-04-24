package com.globant.precard.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ClientServlet extends HttpServlet{

	private static final Logger logger = Logger.getLogger(ClientServlet.class.getCanonicalName());
	
	private static final long serialVersionUID = 791025983359577644L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String[] tokens = MicroFramework.getPathTokens(req);
		req.setAttribute("clientid", tokens[0]);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/client.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String[] tokens = MicroFramework.getPathTokens(req);
	}


}
