package com.aegisql.demo;

import java.text.DateFormat;
import java.util.Locale;

public class BalanceReportBuilderRu extends AbstractReportBuilder {

	@Override
	public String get() {
		StringBuilder builder = new StringBuilder();
		builder
		.append("Уважаем").append(getGenderAddress(userInfo.getGender())).append(" ").append(userInfo.getFirstName()).append(" ").append(userInfo.getMiddleName()).append(",\n\n")
		.append("СЧЕТ\t\tСУММА\tВАЛЮТА\tДАТА").append("\n");
		for(Balance b:balance) {
			builder
			.append(hideAccount(b.getAccount())).append("\t\t")
			.append(b.getBalance()).append("\t").append(b.getCurrency()).append("\t").append(getLocalDateFormat().format(b.getTimestamp()))
			.append("\n")
			;
		}
		builder.append("\n\nВаш, Банк 'Реактивный'.\n");
		return builder.toString();
	}

	@Override
	public DateFormat getLocalDateFormat() {
		Locale locale = new Locale.Builder().setLanguage("ru").build();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale);
		return df;
	}

	@Override
	public String getGenderAddress(int gender) {
		switch (gender) {
		case 1:
			return "ый";
		case 2:
			return "ая";
		default:
			throw new RuntimeException("Unknown gender: "+gender);
		}
	}

}
