package com.modestack.ahmed.models;

public class LoginDto {
	
	private String userName;
	private String password;
	
	public LoginDto() {
		System.out.println(this.getClass().getName()+" Constructor Called...");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
