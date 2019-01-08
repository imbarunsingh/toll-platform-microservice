package com.microservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.feign.client.FastPassWebFeignClient;
import com.microservice.model.Customer;

/*You can force a bean to refresh its configuration - to pull updated values
from the Config Server.Needs spring-boot-actuator as dependency
hit the /actuator/refresh(POST) endpoint on this client to get the refresh on config changes*/
@Controller
@RefreshScope
@RequestMapping("/fast-pass")
public class FastPassWebController {

	private static final Logger logger = LoggerFactory.getLogger(FastPassWebController.class);

	@Autowired
	private FastPassWebFeignClient fastPassWebFeignClient;
	
	@GetMapping(path="/customer-details", params= {"customerId"})
	public String getFastPassDetailsByCustomerId(@RequestParam String customerId, Model m) {
		
		logger.info("Getting the fast pass customer details on the console for customer with Id: {}", customerId);		
				
		Customer customer = fastPassWebFeignClient.getFastPassById(customerId);
		
		logger.info("Customer detail for customer Id:{} is: {}",customerId, customer);
		
		m.addAttribute("customer", customer);
		
		return "fast-pass-console";
	}	
	
	@GetMapping(path="/customer-details", params= {"phoneNumber"})
	public String getFastPassDetailsByPhoneNumber(@RequestParam String phoneNumber, Model m) {
		
		logger.info("Getting the fast pass customer details on the console for customer with phoneNumber: {}", phoneNumber);		
				
		Customer customer = fastPassWebFeignClient.getFastPassByPhoneNumber(phoneNumber);
		
		logger.info("Customer detail for customer with phoneNumber: {} is: {}",phoneNumber, customer);
		
		m.addAttribute("customer", customer);
		
		return "fast-pass-console";
	}	
}