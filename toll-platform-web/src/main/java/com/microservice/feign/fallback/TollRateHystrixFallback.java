package com.microservice.feign.fallback;

import org.springframework.stereotype.Component;

import com.microservice.feign.client.TollRateFeignClient;
import com.microservice.model.TollRate;

@Component
public class TollRateHystrixFallback implements TollRateFeignClient {

	@Override
	public TollRate getTollCharges(int stationId) {
		TollRate rate = new TollRate();
		rate.setCurrentRate(1);
		rate.setStationId(1);
		return rate;
	}

}
