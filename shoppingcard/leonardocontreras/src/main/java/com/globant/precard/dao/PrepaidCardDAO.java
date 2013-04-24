package com.globant.precard.dao;


import java.util.Calendar;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public enum PrepaidCardDAO {
	INSTANCE;
	
	private static final String TRANSACTION_KIND = "Transaction";
	private static final String CARD_KIND = "PrepaidCard";
	private static final String USER_KIND = "FinalUser";
	
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	private static final Logger logger = Logger.getLogger(PrepaidCardDAO.class.getCanonicalName());
	
	public Iterable<Entity> getCardTransactions(String username, String cardCode){
		Key userKey = KeyFactory.createKey(USER_KIND, username);
		Key cardKey = KeyFactory.createKey(userKey,CARD_KIND, cardCode);
		Query query = new Query(TRANSACTION_KIND,cardKey);
		PreparedQuery pq = datastore.prepare(query);  
		return pq.asIterable();
	}
	
	public Iterable<Entity> getUserCards(String userName){
		Key userKey = KeyFactory.createKey(USER_KIND, userName);
		Query query = new Query(CARD_KIND,userKey);
		PreparedQuery pq = datastore.prepare(query);  
		return pq.asIterable();		
	}
	
	public Iterable<Entity> getUsers(){
		Query query = new Query(USER_KIND);
		PreparedQuery pq = datastore.prepare(query);  
		return pq.asIterable();
	}
	
	
	public Iterable<Entity> getCards(){
		Query query = new Query(CARD_KIND);
		PreparedQuery pq = datastore.prepare(query);  
		return pq.asIterable();
	}
	
	public boolean putTransaction(String userCode ,String cardcode, Long amount) {
		Key userKey = KeyFactory.createKey(USER_KIND, userCode);
		Key cardKey = KeyFactory.createKey(userKey,CARD_KIND, cardcode);

		Transaction trans = datastore.beginTransaction();
		try {
			Entity card = datastore.get(trans, cardKey);
			Entity transaction = new Entity(TRANSACTION_KIND, card.getKey());
			transaction.setProperty("timestamp", Calendar.getInstance().getTime());
			transaction.setProperty("amount", amount);
			Long cardBalance = (Long)card.getProperty("balance");
			logger.warning("cardbalance: "+String.valueOf(cardBalance));
			logger.warning("ammount: "+String.valueOf(amount));
			Long newBalance = cardBalance+amount;
			if (newBalance >= 0) {
				card.setProperty("balance", newBalance);
				transaction.setProperty("balanceAfterTransaction", newBalance);
				datastore.put(trans, transaction);
				datastore.put(trans, card);
			} else {
				return false;
			}

		} catch (EntityNotFoundException enfe) {
			trans.rollback();
			logger.severe("Card not found");
			logger.severe(enfe.toString());
			return false;
		}
		trans.commit();
		return true;

	}
	
	public void populateMock(){
		Entity leo = new Entity(USER_KIND,"leonardo.contreras@globant.com");
		Entity pablo = new Entity(USER_KIND,"pablo.roca@globant.com");
		Entity leoCard = new Entity(CARD_KIND,"cardleo",leo.getKey());
		leoCard.setProperty("balance", new Long(5000));
				
		Entity pabloCard = new Entity(CARD_KIND,"cardpablo",pablo.getKey());
		pabloCard.setProperty("balance", new Long(5000));
		
		
		Entity leoGift = new Entity(TRANSACTION_KIND,leoCard.getKey());
		leoGift.setProperty("amount", new Long(5000));
		leoGift.setProperty("balanceAfterTransaction", new Long(5000));
		leoGift.setProperty("timestamp", Calendar.getInstance().getTime());
		
		Entity pabloGift = new Entity(TRANSACTION_KIND,pabloCard.getKey());
		pabloGift.setProperty("amount", new Long(5000));
		pabloGift.setProperty("balanceAfterTransaction", new Long(5000));
		pabloGift.setProperty("timestamp", Calendar.getInstance().getTime());
		
		datastore.put(leo);
		datastore.put(leoCard);
		datastore.put(leoGift);
		datastore.put(pablo);
		datastore.put(pabloCard);
		datastore.put(pabloGift);
		
		
	}

}
