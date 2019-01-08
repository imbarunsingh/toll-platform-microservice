package com.microservice.feign.fallback;

import org.springframework.stereotype.Component;

import com.microservice.feign.client.FastPassWebFeignClient;
import com.microservice.model.Customer;

@Component
public class FastPassWebHystrixFallback implements FastPassWebFeignClient {

	@Override
	public Customer getFastPassById(String customerId) {
		return getFastPassCustomerDetailFallback(customerId, "");
	}

	@Override
	public Customer getFastPassByPhoneNumber(String phoneNumber) {
		return getFastPassCustomerDetailFallback("", phoneNumber);
	}

	public Customer getFastPassCustomerDetailFallback(String customerId, String phoneNumber) {

		Customer cust = new Customer();
		cust.setCustomerId(customerId);
		cust.setCurrentBalance(0);
		cust.setFullName("");
		cust.setPhoneNumber(phoneNumber);
		return cust;
	}
}