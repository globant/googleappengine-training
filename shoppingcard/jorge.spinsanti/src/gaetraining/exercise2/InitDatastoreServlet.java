package gaetraining.exercise2;

import gaetraining.exercise2.model.EndUser;
import gaetraining.exercise2.model.Mapper;
import gaetraining.exercise2.model.ShoppingCard;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Transaction;

public class InitDatastoreServlet extends HttpServlet {

	private static final Logger logger = Logger
			.getLogger(InitDatastoreServlet.class.getCanonicalName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		initDatastore(req, resp);
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		initDatastore(req, resp);
	};

	private void initDatastore(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction tx = datastore.beginTransaction();
		try {
			// Create user1 entity based on the model
			EndUser user = new EndUser("user1");
			Entity userEntity = Mapper.createEndUserEntity(user);
			datastore.put(userEntity);

			// Create ShoppingCard for user 1
			ShoppingCard sc = new ShoppingCard("a0f5ssz37b", user);
			Entity scEntity = Mapper.createShoppingCardEntity(sc, userEntity.getKey());
			datastore.put(scEntity);
			
			tx.commit();
			
			tx = datastore.beginTransaction();
			
			// Create user2 entity based on the model
			EndUser user2 = new EndUser("user2");
			Entity user2Entity = Mapper.createEndUserEntity(user2);
			datastore.put(user2Entity);
			
			// Create ShoppingCard for user 2
			ShoppingCard sc2 = new ShoppingCard("bs3546szaa00", user2);
			sc2.addAmount(11002L);
			Entity sc2Entity = Mapper.createShoppingCardEntity(sc2, user2Entity.getKey());
			datastore.put(sc2Entity);
			
			tx.commit();
		} catch (Exception e) {
			logger.throwing(InitDatastoreServlet.class.getCanonicalName(),
					"initDatastore", e);
		} finally {
			if (tx.isActive())	
				tx.rollback();
		}

		resp.sendRedirect("index.html");
	}
}
