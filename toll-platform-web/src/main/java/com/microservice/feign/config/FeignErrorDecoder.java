package com.microservice.feign.config;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		if (response.status() >= 400 && response.status() <= 499) {
			return new ClientException(response.status(), response.reason());
		}
		if (response.status() >= 500 && response.status() <= 599) {
			return new ServerException(response.status(), response.reason());
		}
		return errorStatus(methodKey, response);
	}

	private Exception errorStatus(String methodKey, Response response) {
		return new RuntimeException(response.reason());
	}

}
