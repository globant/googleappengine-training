package com.globant.training.gae.controllers;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.globant.training.gae.business.Client;

@SuppressWarnings("serial")
public class ClientController extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(ClientController.class.getCanonicalName());
	
	private Client client;
	
	@Override
	public void init(){
		client =  new Client();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String userName =  req.getParameter("userName");
		RequestDispatcher view = req.getRequestDispatcher("/result.jsp");
		String msg = "";
		try {
			logger.info("Getting user balance, for " + userName);
			StringBuilder sb = new StringBuilder("Your balance is ");
			sb.append(client.getUserBalance(userName));
			msg = sb.toString();
		} catch (Exception e) {
			logger.info("It was an error");
			msg = "We have an error getting your balance";
			logger.log(Level.SEVERE, e.getMessage());
		}
		
		req.setAttribute("msgPostProc", msg);
		view.forward(req, resp);
	}
}
