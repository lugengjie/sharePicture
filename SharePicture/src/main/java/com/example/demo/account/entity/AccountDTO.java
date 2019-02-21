package com.example.demo.account.entity;

public class AccountDTO {
	private String email;
	private String password;
	private String name;
	private String activationCode;
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	
	public String getActivationCode() {
		return activationCode;
	}
	public void setEmail(String emial) {
		this.email = emial;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	
}
