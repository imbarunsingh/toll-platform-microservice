package com.microservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*You can force a bean to refresh its configuration - to pull updated values
from the Config Server.Needs spring-boot-actuator as dependency
hit the /actuator/refresh(POST) endpoint on this client to get the refresh on config changes*/
@Controller
@RefreshScope 
public class RateController {

	@Value("${rate}")
	private String rate;

	@Value("${lanecount}")
	private String laneCount;

	@Value("${tollstart}")
	private String tollStart;
	
	@Value("${connstring}")
	private String connString;

	@RequestMapping(value = "/rate", method=RequestMethod.GET)
	public String getRates(Model m) {

		System.out.println("The Retrieved values ares :" + rate + " " + laneCount + " " + tollStart);
		m.addAttribute("rateAmount", rate);
		m.addAttribute("laneCount", laneCount);
		m.addAttribute("tollStart", tollStart);
		m.addAttribute("connString", connString);

		// Returning the view name
		return "rateView";
	}
}
