package com.globant.precard.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.globant.precard.dao.PrepaidCardDAO;
import com.globant.precard.servlet.MicroFramework;
import com.google.appengine.api.backends.BackendServiceFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;



public class CashierServlet extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(CashierServlet.class.getCanonicalName());

	private static final long serialVersionUID = -7931385653539305200L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String[] tokens = MicroFramework.getPathTokens(req);
		req.setAttribute("cashierid", tokens[0]);
		if(tokens.length>1){
			if("seed".equals(tokens[1])){
				PrepaidCardDAO.INSTANCE.populateMock();
			}
		}
		
		
			
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cashier.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String[] tokens = MicroFramework.getPathTokens(req);
		req.setAttribute("cashierid", tokens[0]);
		
		if(tokens.length>1){
			if("payment".equals(tokens[1])){
				String[] cardCode = req.getParameter("card").split("-");
				String amount = req.getParameter("amount");
				PrepaidCardDAO.INSTANCE.putTransaction(cardCode[1],cardCode[0], Long.parseLong(amount));
				MicroFramework.sendMail(cardCode[1], "Transaction created", "Your card "+cardCode[0]+" was credited with "+amount+ " bucks");
			}else if("purchase".equals(tokens[1])){
				String[] cardCode = req.getParameter("card").split("-");
				String amount = req.getParameter("amount");
				if(PrepaidCardDAO.INSTANCE.putTransaction(cardCode[1],cardCode[0], -Long.parseLong(amount))){
					req.setAttribute("message", "Transaction done!");
				}else{
					req.setAttribute("message", "Transaction failed!");
				}
					;
			}else if("asyncpayment".equals(tokens[1])){
				StringBuilder  sb = new StringBuilder(req.getServletPath());
				sb.append("/");
				sb.append(tokens[0]);
				sb.append("/");
				sb.append("payment");

				  Queue queue = QueueFactory.getDefaultQueue();
				  TaskOptions taskOptions = TaskOptions.Builder.withUrl(sb.toString())
				                            .param("card", req.getParameter("card"))
				                            .param("amount", req.getParameter("amount"))
				                            .method(Method.POST);
				  queue.add(taskOptions);
			}
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cashier.jsp");
		dispatcher.forward(req, resp);
	}
	


}
