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

import com.microservice.model.TollRate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/*You can force a bean to refresh its configuration - to pull updated values
from the Config Server.Needs spring-boot-actuator as dependency
hit the /actuator/refresh(POST) endpoint on this client to get the refresh on config changes*/
@Controller
@RefreshScope
@RequestMapping("/toll-rate")
public class TollRateController {

	private static final Logger logger = LoggerFactory.getLogger(TollRateController.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="getTollChargesFallback") // This fall back method is called when the toll-rate-service is down
	@GetMapping(value="/charges", params= {"stationId"})	
	public String getTollCharges(@RequestParam int stationId, Model model) {
		
		logger.info("Getting the toll rate on dashboard for: {}", stationId);	
		
		TollRate tollRate = restTemplate.getForObject("http://toll-rate-service/toll-charges/"+stationId, TollRate.class);
		
		logger.info("Current Charges for Station :{} is: {}",stationId, tollRate.getCurrentRate());
		
		model.addAttribute("tollRate", tollRate.getCurrentRate());
		
		return "toll-rate-view";
	}	
	
	public String getTollChargesFallback(@RequestParam int stationId, Model model) {
		
		logger.warn("Initiating a fallback method exceution using circuit breaker for DashboardController::getTollRate");
		
		model.addAttribute("tollRate", "1.00");
		
		return "toll-rate-view";
	}
}