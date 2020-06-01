package com.modestack.ahmed.models;

public class AuthenticationResponse {
	private final String accessToken;
	private int statusCode;
	private String message;

	public AuthenticationResponse(String accessToken, int statusCode, String message) {
		this.accessToken = accessToken;
		this.statusCode = statusCode;
		this.message = message;
	}
	

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAccessToken() {
		return accessToken;
	}

}
