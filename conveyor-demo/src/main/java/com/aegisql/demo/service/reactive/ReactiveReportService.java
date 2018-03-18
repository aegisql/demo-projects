package com.aegisql.demo.service.reactive;

public interface ReactiveReportService {
	void sendReportByEmail(String report, String email, String subject );
}
