package com.microservice.serviceImpl;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.microservice.model.TollRate;
import com.microservice.service.TollRateService;

@Service
public class TollRateServiceImpl implements TollRateService {

	@Override
	public TollRate getTollRate(int stationId) {
		TollRate tollRate = new TollRate();
		tollRate.setStationId(stationId);
		tollRate.setCurrentRate(stationId*10);
		tollRate.setTimestamp(LocalDateTime.now());
		return tollRate;
	}

}
