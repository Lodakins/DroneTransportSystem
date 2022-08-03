package com.musala.dto;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class DroneBatterLog {
	
	@Expose
	private int droneID;
	
	@Expose 
	private String name;
	
	@Expose
	private int battery;
	
	@Expose
	private String date;
	
	@Expose
	private String time;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDroneID() {
		return droneID;
	}

	public int getBattery() {
		return battery;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public void setDroneID(int droneID) {
		this.droneID = droneID;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public String toString() {
		return new Gson().toJson(this);
	}

}
