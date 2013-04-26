package com.globant.precard.model;

import com.globant.precard.dao.PrepaidCardDAO;

public enum TransactionService {
	INSTANCE;
	private PrepaidCardDAO dao = PrepaidCardDAO.INSTANCE;
	public Transaction[] getTransactions(String userName, String cardCode){
		return null;
	} 

}
