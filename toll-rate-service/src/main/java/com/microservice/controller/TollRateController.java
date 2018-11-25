package com.microservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.model.TollRate;
import com.microservice.service.TollRateService;

/*You can force a bean to refresh its configuration - to pull updated values
from the Config Server.Needs spring-boot-actuator as dependency
hit the /actuator/refresh(POST) endpoint on this client to get the refresh on config changes*/
@RestController
@RefreshScope
public class TollRateController {

	private static final Logger logger = LoggerFactory.getLogger(TollRateController.class);

	@Autowired
	private TollRateService tollRateService;

	@GetMapping("/toll-charges/{stationId}")
	public TollRate getTollCharges(@PathVariable int stationId) {
		logger.info("Getting the Toll Charges for the stationId: {}", stationId);
		return tollRateService.getTollRate(stationId);
	}
}