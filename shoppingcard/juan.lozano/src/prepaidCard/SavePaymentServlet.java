package prepaidCard;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SavePaymentServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Queue queue = QueueFactory.getDefaultQueue();
		TaskOptions taskOptions = TaskOptions.Builder.withUrl("/payment").param(
				"amount", req.getParameter("amount")).param("cardNumber", req.getParameter("cardNumber")).method(Method.PUT);
		queue.add(taskOptions);
		
		resp.sendRedirect("/prepaidcard.jsp");
	}

	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		String endUser = "anonymous";
		if (user != null) {
			endUser = user.getUserId();
		}

		Key endUserKey = KeyFactory.createKey("EndUser", endUser);
		
		String cardNumber = req.getParameter("cardNumber");
		Query query = new Query("ShoppingCard", endUserKey);
		query.setFilter(new FilterPredicate("code", FilterOperator.EQUAL, cardNumber));
		
		Entity card = DatastoreServiceFactory.getDatastoreService().prepare(query).asSingleEntity();
		
		if (card == null) {
			card = new Entity("ShoppingCard", endUserKey);
			card.setProperty("code", cardNumber);
			card.setProperty("balance", "0");
			
			DatastoreServiceFactory.getDatastoreService().put(card);
		}
		
		String amount = req.getParameter("amount");
		
		String balance = (String) card.getProperty("balance");
		String balanceAfterTransaction = String.valueOf(Integer.parseInt(balance) + Integer.parseInt(amount));
		card.setProperty("balance", balanceAfterTransaction);
		card.setProperty("balance", balanceAfterTransaction);
		
		DatastoreServiceFactory.getDatastoreService().put(card);
	
				
		Entity transaction = new Entity("Transaction", endUserKey);
		transaction.setProperty("timeStamp", new Date());
		transaction.setProperty("amount", amount);
		transaction.setProperty("balanceAfterTransaction", balanceAfterTransaction);
		DatastoreServiceFactory.getDatastoreService().put(transaction);		
		
	}
	
}