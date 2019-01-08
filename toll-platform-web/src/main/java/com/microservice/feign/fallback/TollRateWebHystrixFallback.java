package com.microservice.feign.fallback;

import org.springframework.stereotype.Component;

import com.microservice.feign.client.TollRateWebFeignClient;
import com.microservice.model.TollRate;

@Component
public class TollRateWebHystrixFallback implements TollRateWebFeignClient {

	@Override
	public TollRate getTollCharges(int stationId) {
		TollRate rate = new TollRate();
		rate.setCurrentRate(1);
		rate.setStationId(stationId);
		return rate;
	}

}
