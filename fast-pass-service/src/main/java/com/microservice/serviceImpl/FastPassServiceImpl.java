package com.microservice.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.microservice.model.Customer;
import com.microservice.service.FastPassService;

@Service
public class FastPassServiceImpl implements FastPassService {
	
	private static final Logger logger = LoggerFactory.getLogger(FastPassServiceImpl.class);

	@Override
	public Customer getFastPassByCustomerId(String customerId) {
		
		logger.info("FastPassServiceImpl::getFastPassByCustomerId");

		Predicate<Customer> predicate = customer -> customer.getCustomerId().equalsIgnoreCase(customerId);
		return findCustomerByPredicate(predicate);
	}

	@Override
	public Customer getFastPassByCustomerPhoneNumber(String phoneNumber) {
		
		logger.info("FastPassServiceImpl::getFastPassByCustomerPhoneNumber");
		
		Predicate<Customer> predicate = customer -> customer.getPhoneNumber().equalsIgnoreCase(phoneNumber);
		return findCustomerByPredicate(predicate);
	}

	private List<Customer> initCustomerList() {
		
		List<Customer> customersList = new ArrayList<Customer>();
		customersList.add(new Customer("101", "Arvind", "72040787456", 3030));
		customersList.add(new Customer("102", "Abhay", "72040787236", 2020));
		customersList.add(new Customer("103", "Prateek", "7204078", 1010));
		
		return customersList;
	}

	private Customer findCustomerByPredicate(Predicate<Customer> predicate) {

		List<Customer> customerList = initCustomerList();

		return customerList
				.stream()
				.filter(predicate)
				.findFirst()
				.get();
	}
}
