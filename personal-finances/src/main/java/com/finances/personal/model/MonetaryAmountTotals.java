package com.finances.personal.model;

import com.finances.personal.core.model.ValueObject;

public class MonetaryAmountTotals extends ValueObject {

	public static final String CREDITS = "credits";
	public static final String DEBITS = "debits";
	public static final String BALANCE = "balance";
	
	private float credits;
	private float debits;
	private float balance;
	
	public final float getCredits() {
		return credits;
	}
	
	public final void setCredits(float credits) {
		lock(CREDITS);
		this.credits = credits;
	}

	public final float getDebits() {
		return debits;
	}

	public final void setDebits(float debits) {
		lock(DEBITS);
		
		this.debits = debits;
	}

	public final float getBalance() {
		return balance;
	}

	public final void setBalance(float balance) {
		lock(BALANCE);
		
		this.balance = balance;
	}
	
}