package com.microservice.feign.config;

public class ClientException extends Exception {

	private static final long serialVersionUID = -4017489217663767041L;

	public int status;
	public String reason;

	public ClientException(int status, String reason) {
		super();
		this.status = status;
		this.reason = reason;
	}
}
