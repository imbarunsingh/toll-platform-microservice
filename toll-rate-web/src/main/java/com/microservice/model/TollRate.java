package com.microservice.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TollRate {

	private int stationId;
	private int currentRate;
	private LocalDateTime timeStamp;

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public int getCurrentRate() {
		return currentRate;
	}

	public void setCurrentRate(int currentRate) {
		this.currentRate = currentRate;
	}

	public LocalDateTime getTimestamp() {
		return timeStamp;
	}

	public void setTimestamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

}
