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

import com.microservice.feign.client.TollRateWebFeignClient;
import com.microservice.model.TollRate;

/*You can force a bean to refresh its configuration - to pull updated values
from the Config Server.Needs spring-boot-actuator as dependency
hit the /actuator/refresh(POST) endpoint on this client to get the refresh on config changes*/
@Controller
@RefreshScope
@RequestMapping("/toll-web")
public class TollRateWebController {

	private static final Logger logger = LoggerFactory.getLogger(TollRateWebController.class);

	@Autowired
	private TollRateWebFeignClient tollRateFeignClient;	
	
	@GetMapping(value="/charges", params= {"stationId"})	
	public String getTollCharges(@RequestParam int stationId, Model model) {
		
		logger.info("Getting the toll rate on dashboard for: {}", stationId);		
		
		TollRate tollRate = tollRateFeignClient.getTollCharges(stationId);
		
		logger.info("Current Charges for Station :{} is: {}",stationId, tollRate.getCurrentRate());
		
		model.addAttribute("tollRate", tollRate.getCurrentRate());
		
		return "toll-rate-view";
	}	
}