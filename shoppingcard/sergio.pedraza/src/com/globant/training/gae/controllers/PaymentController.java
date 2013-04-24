package com.globant.training.gae.controllers;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.globant.training.gae.business.PaymentProcess;

@SuppressWarnings("serial")
public class PaymentController extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(PaymentController.class.getCanonicalName());
	
	private PaymentProcess paymentProcess;
	
	@Override
	public void init(){
		paymentProcess = new PaymentProcess();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Payment Controller");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String userName = req.getParameter("userName");
		String value = req.getParameter("value");
		String address = req.getParameter("address");
		
		RequestDispatcher view = req.getRequestDispatcher("/result.jsp");
		String msg = "";
		try {
			paymentProcess.savePayment(address, userName, value);
			msg = "Payment in process";
		} catch (Exception e) {
			msg = "Error with payment";
			logger.info("Transaction can't be processed");
			e.printStackTrace();
		}
		
		req.setAttribute("msgPostProc", msg);
		view.forward(req, resp);		
	}
}
