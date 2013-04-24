package gaetraining.exercise2.model;

public class ShoppingCard {
	
	public static final String KIND = "ShoppingCard";
	
	private String code;
	private long balance;
	private EndUser endUser;
	
	public ShoppingCard(String code, EndUser user) {
		this.code = code;
		balance = 0;
		this.endUser = user;
	}
	
	public String getCode() {
		return code;
	}
	
	public long getBalance() {
		return balance;
	}
	
	public EndUser getEndUser() {
		return endUser;
	}
	
	public void addAmount(long amount) {
		if (amount + balance < 0)
			throw new IllegalStateException("Balance is negative!");
		balance = balance + amount;
	}
}
