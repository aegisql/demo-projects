package com.aegisql.demo;

import java.math.BigDecimal;
import java.util.Date;

public class Balance {
	
	private final String account;
	private final BigDecimal balance;
	private final String currency;
	private final long timestamp;
	
	public Balance(String account, BigDecimal balance, String currency, long timestamp) {
		this.account = account;
		this.balance = balance;
		this.currency = currency;
		this.timestamp = timestamp;
	}

	public String getAccount() {
		return account;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public String getCurrency() {
		return currency;
	}

	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Balance [account=").append(account).append(", balance=").append(balance).append(", currency=")
				.append(currency).append(", timestamp=").append(new Date(timestamp)).append("]");
		return builder.toString();
	}

	
	
}
