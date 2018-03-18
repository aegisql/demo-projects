package com.aegisql.demo;

import java.text.DateFormat;
import java.util.Locale;

public class BalanceReportBuilderEn extends AbstractReportBuilder {

	@Override
	public String get() {
		StringBuilder builder = new StringBuilder();
		builder
		.append("Dear ").append(addr).append(" ").append(name).append(" ").append(patronymic).append(",\n\n")
		.append("Remaining balance on your account #").append(account).append(" on ").append(reportTimestamp).append("\n")
		.append("Was ").append(summ).append(" ").append(currency).append(".")
		.append("\n\nYours, Bank 'Reactive'.\n");
		return builder.toString();
	}

	@Override
	public DateFormat getLocalDateFormat() {
		Locale locale = new Locale.Builder().setLanguage("en").build();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale);
		return df;
	}

}
