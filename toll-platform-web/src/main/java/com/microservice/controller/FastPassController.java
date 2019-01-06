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
import org.springframework.web.client.RestTemplate;

import com.microservice.model.Customer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/*You can force a bean to refresh its configuration - to pull updated values
from the Config Server.Needs spring-boot-actuator as dependency
hit the /actuator/refresh(POST) endpoint on this client to get the refresh on config changes*/
@Controller
@RefreshScope
@RequestMapping("/toll-web")
public class FastPassController {

	private static final Logger logger = LoggerFactory.getLogger(FastPassController.class);

	@Autowired
	private RestTemplate restTemplate;	
	
	@HystrixCommand(fallbackMethod="getFastPassCustomerDetailsFallback") // This fall back method is called when the fast-pass-service is down
	@GetMapping(path="/customer-details", params= {"customerId"})
	public String getFastPassCustomerDetails(@RequestParam String customerId, Model m) {
		
		logger.info("Getting the fast pass customer details on the console for customer with Id: {}", customerId);		
				
		Customer customer = restTemplate.getForObject("http://fast-pass-service/fast-pass?customerId=" + customerId, Customer.class);
		
		logger.info("Customer detail for customer Id:{} is: {}",customerId, customer);
		
		m.addAttribute("customer", customer);
		
		return "fast-pass-console";
	}	
	
	public String getFastPassCustomerDetailsFallback(@RequestParam String customerId, Model m) {
		
		logger.warn("Initiating a fallback method exceution using circuit breaker for DashboardController::getFastPassCustomerDetails");
		
		Customer cust = new Customer();
		cust.setCustomerId(customerId);
		
		m.addAttribute("customer", cust);
		
		return "fast-pass-console";
	}
	
}