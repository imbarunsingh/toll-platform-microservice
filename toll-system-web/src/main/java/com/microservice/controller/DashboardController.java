package com.microservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.microservice.model.Customer;
import com.microservice.model.TollRate;

/*You can force a bean to refresh its configuration - to pull updated values
from the Config Server.Needs spring-boot-actuator as dependency
hit the /actuator/refresh(POST) endpoint on this client to get the refresh on config changes*/
@Controller
@RefreshScope
@RequestMapping("/dashboard")
public class DashboardController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private RestTemplate restTemplate;
	

	@GetMapping(value="/toll-rate", params= {"stationId"})	
	public String getTollRate(@RequestParam int stationId, Model model) {
		
		logger.info("Getting the toll rate on dashboard for: {}", stationId);	
		
		TollRate tollRate = restTemplate.getForObject("http://toll-rate-service/toll-charges/"+stationId, TollRate.class);
		
		logger.info("Current Charges for Station :{} is: {}",stationId, tollRate.getCurrentRate());
		
		model.addAttribute("tollRate", tollRate.getCurrentRate());
		
		return "dashboard";
	}
	
	@GetMapping(path="/customer-details", params= {"customerId"})
	public String getFastPassCustomerDetails(@RequestParam String customerId, Model m) {
		
		logger.info("Getting the fast pass customer details on the console for customer with Id: {}", customerId);		
				
		Customer customer = restTemplate.getForObject("http://fast-pass-service/fast-pass?customerId=" + customerId, Customer.class);
		
		logger.info("Customer detail for customer Id:{} is: {}",customerId, customer);
		
		m.addAttribute("customer", customer);
		
		return "console";
	}
	
}