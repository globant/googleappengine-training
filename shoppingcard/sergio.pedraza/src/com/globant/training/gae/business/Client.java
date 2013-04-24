package com.globant.training.gae.business;

import com.globant.training.gae.persistence.ClientDAO;
import com.globant.training.gae.persistence.GenericDAOImpl;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;



public class Client {
	
	private GenericDAOImpl dao;
	
	public Client(){
		dao = new ClientDAO();
	}
	
	public String getUserBalance(String userName) throws Exception{
		Key key = KeyFactory.createKey(EntitiesKinds.USER.toString(), userName);
		Entity user = dao.getEntity(key);
		if(user == null){
			throw new Exception("USer doen't exists");
		}
		
		key = KeyFactory.createKey(user.getKey(),EntitiesKinds.SHOPCARD.toString(), "prepaidCard");
		Entity shoppingCard = dao.getEntity(key);
		
		return shoppingCard.getProperty("balance").toString();
	}
	
}
