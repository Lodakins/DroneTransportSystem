package com.musala.dto;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

@Entity(name="drone")
@Table(name="drone")
public class DroneDTO{
	
	@Id
	@Column(name="drone_id")
	int droneId;
	
	@Column(name="weight")
	private Double weight;
	
	@Expose
	@Column(name="serial")
	private String serial;
	
	@Expose
	@Column(name="model")
	private String model;
	
	@Column(name="battery")
	long battery;
	
	@Expose
	@Column(name="state")
	private String state;
	
	
	private boolean isRequiredFields() {
		return this.serial != null && this.serial.trim().equals("") && this.model != null && this.model.equals("");
	}
	
	
	
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
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



	public long getBattery() {
		return battery;
	}



	public void setBattery(long battery) {
		this.battery = battery;
	}



	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}