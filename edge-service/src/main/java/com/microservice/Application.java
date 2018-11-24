package com.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.microservice.filters.ErrorFilter;
import com.microservice.filters.LogPostFilter;
import com.microservice.filters.LogPreFilter;
import com.microservice.filters.PostFilter;
import com.microservice.filters.PreFilter;
import com.microservice.filters.RouteFilter;

/**
 *
 * @author Barun Singh
 * This service routes the request to all the servers
 * registred in Eureka based on service name. e.g:
 * http://localhost:<zuulproxyPort>/<global prefix>/toll-rate
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
	//Registering the filters beans
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
	
	@Bean
	public LogPreFilter logPreFilter() {
		return new LogPreFilter();
	}

	@Bean
	public LogPostFilter logPostFilter() {
		return new LogPostFilter();
	}
}
