package com.microservice.model;

public class Customer {

	private String customerId;
	private String fullName;
	private String phoneNumber;
	private int currentBalance;

	public Customer() {
	};

	public Customer(String customerId, String fullName, String phoneNumber, int currentBalance) {
		this.customerId = customerId;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.currentBalance = currentBalance;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(int currentBalance) {
		this.currentBalance = currentBalance;
	}
}
