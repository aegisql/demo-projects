package com.aegisql.demo;

import java.text.DateFormat;
import java.util.Locale;

public class BalanceReportBuilderRu extends AbstractReportBuilder {

	@Override
	public String get() {
		StringBuilder builder = new StringBuilder();
		builder
		.append("Уважаем").append(addr).append(" ").append(name).append(" ").append(patronymic).append(",\n\n")
		.append("Остаток на вашем счету №").append(account).append(" на ").append(reportTimestamp).append("\n")
		.append("Составил ").append(summ).append(" ").append(currency).append(".")
		.append("\n\nВаш, Банк 'Реактивный'.\n");
		return builder.toString();
	}

	@Override
	public DateFormat getLocalDateFormat() {
		Locale locale = new Locale.Builder().setLanguage("ru").build();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale);
		return df;
	}

}
