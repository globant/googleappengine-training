package com.globant.training.gae.business;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.globant.training.gae.persistence.IGenericDAO;
import com.globant.training.gae.persistence.TransactionDAO;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PaymentProcess {
	
	private static final Logger logger = Logger.getLogger(PaymentProcess.class.getCanonicalName());
	
	private IGenericDAO dao;
	
	private Entity user;
	
	private Entity shoppingCard;
	
	public PaymentProcess(){
		dao = new TransactionDAO();
	}
	
	public void savePayment(String toAddress, String userName, String value) throws Exception{
		Key key = KeyFactory.createKey(EntitiesKinds.USER.toString(), userName);
		user = dao.getEntity(key);
		if(user == null){
			logger.info("User doesn't exist. It will be created");
			this.createUserAndShoppingCard(userName);
		}else{
			key = KeyFactory.createKey(user.getKey(),EntitiesKinds.SHOPCARD.toString(), "prepaidCard");
			shoppingCard = dao.getEntity(key);
		}
		
		int intValue = Integer.parseInt(value);
		
		key = KeyFactory.createKey(user.getKey(),EntitiesKinds.SHOPCARD.toString(), "prepaidCard");
		
		int balance = Integer.parseInt(shoppingCard.getProperty("balance").toString());
		shoppingCard.setProperty("balance", balance+intValue);
		
		Entity transaction =  new Entity(EntitiesKinds.TRAN.toString(), "transaction", shoppingCard.getKey());
		transaction.setProperty("amount", intValue);
		transaction.setProperty("date", new Date());
		
		dao.saveEntity(shoppingCard);
		dao.saveEntity(transaction);
		logger.info("Transaction saved: value: "+ intValue);
		this.sendEmail(toAddress, userName, intValue);
	}
	
	private void createUserAndShoppingCard(String userName){
		user =  new Entity(EntitiesKinds.USER.toString(), userName);
		user.setProperty("username", userName);
		
		shoppingCard = new Entity(EntitiesKinds.SHOPCARD.toString(), "prepaidCard",user.getKey());
		shoppingCard.setProperty("code", ((int)Math.random()*1000) +1 );
		shoppingCard.setProperty("balance", 0);
		logger.info("User created");
		dao.saveEntity(user);
	}
	
	
	public void sendEmail(String toAddress, String userName, int value)
		      throws IOException {

		    Properties props = new Properties();
		    Session session = Session.getDefaultInstance(props, null);
		    String subject = "Payment result";
		    StringBuilder msgBody =  new StringBuilder();
		    try {
		      Message msg = new MimeMessage(session);

		      msgBody.append("Your payment was successfuly. The amount was: ");
		      msgBody.append(String.valueOf(value));
		      
		      msg.setFrom(new InternetAddress("sergio.pedraza@globant.com"));
		      InternetAddress to = new InternetAddress(toAddress);
		      msg.addRecipient(Message.RecipientType.TO, to);		      
		      msg.setSubject(subject);
		      msg.setText(msgBody.toString());
		      Transport.send(msg, new InternetAddress[] { to });

		    } catch (AddressException addressException) {
		    	logger.info("Address Exception , mail could not be sent");
		    } catch (MessagingException messageException) {
		      logger.info("Messaging Exception , mail could not be sent");
		    }
		  }
}
