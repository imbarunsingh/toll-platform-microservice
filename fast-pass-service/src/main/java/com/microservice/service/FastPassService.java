package com.microservice.service;

import com.microservice.model.Customer;

public interface FastPassService {
	
	public Customer getFastPassByCustomerId(String customerId);
	public Customer getFastPassByCustomerPhoneNumber(String phoneNumber);

}
