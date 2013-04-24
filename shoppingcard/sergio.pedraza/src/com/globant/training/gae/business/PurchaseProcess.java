package com.globant.training.gae.business;

import java.util.Date;
import java.util.logging.Logger;

import org.mortbay.log.Log;

import com.globant.training.gae.persistence.IGenericDAO;
import com.globant.training.gae.persistence.TransactionDAO;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PurchaseProcess {

	
	private static final Logger logger = Logger.getLogger(PurchaseProcess.class.getCanonicalName());
	
	private IGenericDAO dao;

	
	public PurchaseProcess(){
		dao = new TransactionDAO();
	}
	
	public void savePurchase(String value, String userName) throws Exception{
		Key tmpKey = KeyFactory.createKey(EntitiesKinds.USER.toString(), userName);
		Entity user =  dao.getEntity(tmpKey);
		Entity shoppingCard = null;
		if(user == null){
			logger.info("User doesn't exist. It will be created");
			user =  new Entity(EntitiesKinds.USER.toString(), userName);
			user.setProperty("username", userName);
			shoppingCard = this.createShoppingCard(user);
		}
		else{
			tmpKey = KeyFactory.createKey(user.getKey(),EntitiesKinds.SHOPCARD.toString(), "prepaidCard");
			shoppingCard =  dao.getEntity(tmpKey);
			if( shoppingCard == null)
				shoppingCard = this.createShoppingCard(user);
		}
		
		
		Entity transaction =  new Entity(EntitiesKinds.TRAN.toString(), "transaction", shoppingCard.getKey());
		Integer intValue = Integer.parseInt(value);
		transaction.setProperty("amount", intValue);
		transaction.setProperty("date", new Date());
		int newBalance =(Integer.parseInt(shoppingCard.getProperty("balance").toString() ) - intValue);
		if(newBalance < 0)
			throw new Exception("Balance not enough");
		
		transaction.setProperty("balanceAfterTransaction", newBalance);
		
		shoppingCard.setProperty("balance", newBalance);
		
		dao.saveEntity(user);
		dao.saveEntity(shoppingCard);
		dao.saveEntity(transaction);
		logger.info("Transaction saved: value: "+ intValue);
	}
	
	
	private Entity createShoppingCard(Entity user){
		Entity shoppingCard = new Entity(EntitiesKinds.SHOPCARD.toString(), "prepaidCard",user.getKey());
		shoppingCard.setProperty("code", ((int)Math.random()*1000) +1 );
		shoppingCard.setProperty("balance",  ( ((int)Math.random()) *100 ) + 20);
		Log.info("Shopping Card created");
		return shoppingCard;
	}
}
