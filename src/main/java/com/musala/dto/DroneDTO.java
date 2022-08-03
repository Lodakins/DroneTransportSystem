package com.musala.dto;



import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;


public class DroneDTO{
	
	@Expose
	private int droneId;

	@Expose
	private int weight;
	
	@Expose
	private String serial;
	
	@Expose
	private String model;
	
	@Expose
	int battery;
	
	@Expose
	private String state;
	
	@Expose
	private List<Link> links;
	
	
	public List<Link> getLinks() {
		return links;
	}



	public void setLinks(List<Link> links) {
		this.links = links;
	}



	public boolean isRequiredFields() {
		return this.serial != null && !this.serial.trim().equals("") && this.model != null && !this.model.equals("");
	}
	
	
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String getSerial() {
		return serial;
	}
	
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public int getDroneId() {
		return droneId;
	}

	public void setDroneId(int droneId) {
		this.droneId = droneId;
	}

	public int getBattery() {
		return battery;
	}



	public void setBattery(int battery) {
		this.battery = battery;
	}



	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}