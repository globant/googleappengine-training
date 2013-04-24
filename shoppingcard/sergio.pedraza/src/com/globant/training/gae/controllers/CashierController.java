package com.globant.training.gae.controllers;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.globant.training.gae.business.PurchaseProcess;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

@SuppressWarnings("serial")
public class CashierController extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(CashierController.class.getCanonicalName());
	
	private PurchaseProcess purchaseProcess;
	
	@Override
	public void init(){
		purchaseProcess = new PurchaseProcess();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Cashier Controller");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String paymentValue = req.getParameter("paymentValue");
		String userName = req.getParameter("username");
		
		RequestDispatcher view = req.getRequestDispatcher("/result.jsp");
		String msg = "";
		
		if( paymentValue != null ){
			
			logger.info("Payment process starts");
			String address = req.getParameter("address");
			
			try {
				this.putTaskProcessPayment(address, userName, paymentValue);
				msg = "Payment in process. We will send you an email";
			} catch (Exception e) {
				msg = "It was a problem with payment process";
				logger.info("Transaction can't be processed");
				logger.log(Level.SEVERE, e.getMessage());
			}
		}else{
			
			logger.info("Purchase process starts");
			String purchaseValue = req.getParameter("purchaseValue");
			try {
				purchaseProcess.savePurchase(purchaseValue, userName);
				msg = "Purchase succesfull.";
			} catch (Exception e) {
				msg = "It was a problem with purchase process";
				logger.info("Transaction can't be processed");
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
		
		req.setAttribute("msgPostProc", msg);
		view.forward(req, resp);
	}
	
	private void putTaskProcessPayment(String address, String userName, String value){
		Queue queue = QueueFactory.getQueue("PurchaseQueue");
		TaskOptions taskOptions = TaskOptions.Builder.withUrl("/payment.do")
									.param("userName", userName)
									.param("value", value)
									.param("address", address)
									.method(Method.POST);
		queue.add(taskOptions);
		logger.info("Task added to queue");
	}
}
