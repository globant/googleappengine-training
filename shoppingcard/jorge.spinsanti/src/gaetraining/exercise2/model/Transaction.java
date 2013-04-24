package gaetraining.exercise2.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	
	public static final String KIND = "Transaction";
	
	private Date timestamp;
	private long balanceAfterTransaction;
	private long amount;
	private ShoppingCard shoppingCard;
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public long getBalanceAfterTransaction() {
		return balanceAfterTransaction;
	}
	
	public void setBalanceAfterTransaction(long balanceAfterTransaction) {
		this.balanceAfterTransaction = balanceAfterTransaction;
	}
	
	public long getAmount() {
		return amount;
	}
	
	public void setAmount(long amount) {
		this.amount = amount;
	}

	public ShoppingCard getShoppingCard() {
		return shoppingCard;
	}

	public void setShoppingCard(ShoppingCard shoppingCard) {
		this.shoppingCard = shoppingCard;
	}

}
