package com.aegisql.demo.service.reactive;

import com.aegisql.demo.Balance;

public interface ReactiveBillingService {
	Balance getBalance(String account);
}
