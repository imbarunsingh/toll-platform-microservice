package com.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.microservice.ribbonconfig.RibbonConfiguration;
import com.microservice.security.FeignClientInterceptor;

import feign.RequestInterceptor;

@EnableFeignClients
@EnableEurekaClient
@EnableCircuitBreaker
@RibbonClients(defaultConfiguration = RibbonConfiguration.class) // apply your Ribbon config to whole ribbon clients in your server
@SpringBootApplication
public class Application {		

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
    public RequestInterceptor getFeignClientInterceptor() {
        return new FeignClientInterceptor();
    }
}
