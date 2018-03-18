package com.aegisql.demo;

import java.text.DateFormat;
import java.util.Locale;

public class BalanceReportBuilderEn extends AbstractReportBuilder {

	@Override
	public String get() {
		StringBuilder builder = new StringBuilder();
		builder
		.append("Dear ").append(getGenderAddress(userInfo.getGender())).append(" ").append(userInfo.getLastName()).append(" ").append(",\n\n")
		.append("ACCOUNT\t\tBALANCE\tCURRENCY\tDATE").append("\n\n");
		for(Balance b:balance) {
			builder
			.append(hideAccount(b.getAccount())).append(" ")
			.append(b.getBalance()).append(" ").append(b.getCurrency()).append(" ").append(getLocalDateFormat().format(b.getTimestamp()))
			.append("\n")
			;
		}
		builder.append("\n\nYours, Bank 'Reactive'.\n");
		return builder.toString();
	}

	@Override
	public DateFormat getLocalDateFormat() {
		Locale locale = new Locale.Builder().setLanguage("en").build();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale);
		return df;
	}

	@Override
	public String getGenderAddress(int gender) {
		switch (gender) {
		case 1:
			return "Mr.";
		case 2:
			return "Mrs.";
		default:
			throw new RuntimeException("Unknown gender: "+gender);
		}
	}

}
