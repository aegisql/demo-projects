package com.aegisql.demo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.aegisql.demo.service.traditional.BillingService;
import com.aegisql.demo.service.traditional.ReportService;
import com.aegisql.demo.service.traditional.UserService;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;


public class ReportBuilderUsingTraditionalServices {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testTraditionalService() {
		UserService userService = mock(UserService.class);
		when(userService.getUserInfo("11111111")).thenReturn(new UserInfo(1, "Иван", "Иванович", "Петров","ivan.ivanovich@moscow.ru"));
		when(userService.getUserInfo("22222222")).thenReturn(new UserInfo(2, "Jane", "","Johnson", "jane.johnson@newyork.us"));
		
		BillingService billlingService = mock(BillingService.class);
		when(billlingService.getBalance("11111111")).thenReturn(new Balance("11111111",BigDecimal.valueOf(100.5),"РУБ",System.currentTimeMillis()));
		when(billlingService.getBalance("22222222")).thenReturn(new Balance("22222222",BigDecimal.valueOf(201.33),"USD",System.currentTimeMillis()));

		ReportService reportService = new ReportService() {
			public void sendReportByEmail(String report, String email, String subject) {
				System.out.println("mailto:"+email);
				System.out.println("Subject:"+subject);
				System.out.println(report);
			}};
		
		List<String> accounts = Arrays.asList("11111111","22222222");
		
		accounts.forEach(account->{
			UserInfo ui = userService.getUserInfo(account);
			Balance  b = billlingService.getBalance(account);
			AbstractReportBuilder builder = new BalanceReportBuilderRu();
			builder.setUserInfo(ui);
			builder.addBalance(b);
			reportService.sendReportByEmail(builder.get(), ui.getEmail(), "Balance Report");
		});
		
	}

}
