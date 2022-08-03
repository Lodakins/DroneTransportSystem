package com.musala.dto;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class ResponseMessage {

	@Expose
	private String message;
	@Expose
	private boolean status;
	
	
	
	
	public ResponseMessage(String message) {
		super();
		this.message = message;
	}
	public ResponseMessage(String message, boolean status) {
		super();
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
