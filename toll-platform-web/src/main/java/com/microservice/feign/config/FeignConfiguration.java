package com.microservice.feign.config;

import org.springframework.context.annotation.Bean;

import feign.Contract;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.ErrorDecoder;

public class FeignConfiguration {

	public static final int FIVE_SECONDS = 5000;

	@Bean
	public Contract feignContract() {
		return new feign.Contract.Default();
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	public Request.Options options() {
		return new Request.Options(FIVE_SECONDS, FIVE_SECONDS);
	}

	@Bean
	public Retryer retryer() {
		return new FeignRetryer();
	}

	@Bean
	public ErrorDecoder errorDecoder() {
		return new FeignErrorDecoder();
	}
}
