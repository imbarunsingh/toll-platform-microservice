package com.microservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.model.Customer;
import com.microservice.service.FastPassService;

/*You can force a bean to refresh its configuration - to pull updated values
from the Config Server.Needs spring-boot-actuator as dependency
hit the /actuator/refresh(POST) endpoint on this client to get the refresh on config changes*/
@RestController
@RefreshScope
public class FastPassController {

	private static final Logger logger = LoggerFactory.getLogger(FastPassController.class);

	@Autowired
	private FastPassService fastPassService;

	@GetMapping(path="/fast-pass", params= {"customerId"})
	public Customer getFastPassById(@RequestParam String customerId) {
		logger.info("Checking Allocation of fast lane for the customer based on ID: {}", customerId);
		return fastPassService.getFastPassByCustomerId(customerId);
	}
	
	@GetMapping(path="/fast-pass", params= {"phoneNumber"})
	public Customer getFastPassByPhoneNumber(@RequestParam String phoneNumber) {
		logger.info("Checking Allocation of fast lane for the customer based on phone number: {}", phoneNumber);
		return fastPassService.getFastPassByCustomerPhoneNumber(phoneNumber);
	}
}