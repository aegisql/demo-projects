package com.aegisql.demo;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.util.function.Supplier;

public abstract class AbstractReportBuilder implements Supplier<String> {

	protected String addr;
	protected String name;
	protected String patronymic;
	protected String account;
	protected String currency;
	protected String reportTimestamp;
	protected BigDecimal summ;

	
	public void setAddress(String suffix) {
		this.addr = suffix;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public void setAccount(String account) {
		StringBuilder sb = new StringBuilder(account);
		for(int i = 0; i < sb.length()-4; i++) {
			sb.setCharAt(i, '*');
		}
		this.account = sb.toString();
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public abstract DateFormat getLocalDateFormat();
	
	public void setReportTimestamp(Date reportTimestamp) {
		DateFormat df = getLocalDateFormat();
		this.reportTimestamp = df.format(reportTimestamp);
	}

	public void setSumm(BigDecimal summ) {
		this.summ = summ.setScale(2);
	}

	
}
