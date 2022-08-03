package com.musala.dto;

import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity(name="medication")
@Table(name="medication")
public class MedicationDTO {

	@Id
	@Column(name="med_id")
	int medId;
	
	@Column(name="name")
	String name;
	
	@Column(name="weight")
	double weight;
	
	@Column(name="code")
	String code;
	
	@Column(name="drone_id")
	@ManyToOne
	int droneId;
	
	@Column(name="image")
	Blob image;
	
	@Column(name="createdAt")
	Date createdAt;
	
	
	public int getMedId() {
		return medId;
	}
	public void setMedId(int medId) {
		this.medId = medId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getDrone_id() {
		return droneId;
	}
	public void setDrone_id(int drone_id) {
		this.droneId = drone_id;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
