package com.microservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.microservice.feign.config.FeignConfiguration;
import com.microservice.feign.fallback.TollRateWebHystrixFallback;
import com.microservice.model.TollRate;

import feign.Param;
import feign.RequestLine;

@FeignClient(name = "toll-rate-service",
			 configuration = FeignConfiguration.class,
			 fallback = TollRateWebHystrixFallback.class)
public interface TollRateWebFeignClient {
	
	@RequestLine("GET /toll-charges/{stationId}")
	public TollRate getTollCharges(@Param("stationId") int stationId);
}
