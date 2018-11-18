package com.microservice.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/*This is a custom health check endpoint which sends the health status to eureka status about
its availability.So when the error code is between 2 and 4, the service will be down
otherwise up.*/
@Component
public class CustomHealthCheck implements HealthIndicator {

	int errorCode = 0;

	@Override
	public Health health() {

		System.out.println("Health Check performed, error code is: " + errorCode);

		Health health;

		if (errorCode > 2 && errorCode < 6) {
			health = Health.down().withDetail("Custome Error Code", errorCode).build();
		} else {
			health = Health.up().build();
		}

		errorCode++;

		return health;
	}

}
