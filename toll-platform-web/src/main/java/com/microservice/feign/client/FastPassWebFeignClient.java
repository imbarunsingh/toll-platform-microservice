package com.microservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.microservice.feign.config.FeignConfiguration;
import com.microservice.feign.fallback.FastPassWebHystrixFallback;
import com.microservice.model.Customer;

import feign.Param;
import feign.RequestLine;

@FeignClient(name = "fast-pass-service",
			 configuration = FeignConfiguration.class,
			 fallback = FastPassWebHystrixFallback.class)
public interface FastPassWebFeignClient {
	
	@RequestLine("GET /fast-pass?customerId={customerId}")
	public Customer getFastPassById(@Param("customerId") String customerId);
	
	@RequestLine("GET /fast-pass?phoneNumber={phoneNumber}")
	public Customer getFastPassByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
