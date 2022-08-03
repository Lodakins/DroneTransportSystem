package com.musala.dto;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class Link {
	@Expose
	String url;
	
	@Expose
	String self;
	
	
	public Link(String url, String self) {
		super();
		this.url = url;
		this.self = self;
	}
	public String getUrl() {
		return url;
	}
	public String getSelf() {
		return self;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	
	public String toString() {
		return new Gson().toJson(this);
	}
}
