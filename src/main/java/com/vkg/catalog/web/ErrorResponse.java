package com.vkg.catalog.web;

public class ErrorResponse {
	private String message;
	
	
	public ErrorResponse(String message) {
		this.message = message;
	}

	public boolean isError() {
		return true;
	}
	
	public String getMessage() {
		return message;
	}
}
