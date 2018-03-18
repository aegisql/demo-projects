package com.aegisql.demo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractReportBuilder implements Supplier<String> {

	protected UserInfo userInfo;
	protected final List<Balance> balance = new ArrayList<>();
	
	public void setUserInfo(UserInfo ui) {
		this.userInfo = ui;
	}

	public void addBalance(Balance balance) {
		this.balance.add(balance);
	}

	public abstract DateFormat getLocalDateFormat();
	public abstract String getGenderAddress(int gender);
	
}
