package com.musala.dto;

import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class MedicationDTO {


	@Expose
	int medId;
	
	@Expose
	String name;
	
	@Expose
	String weight;
	
	@Expose
	String code;

	@Expose
	int droneId;
	
	@Expose
	String image;
	
	@Expose
	Date createdAt;
	
	@Expose
	List<Link> links;
	
	
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public boolean isRequiredFields() {
		return this.name != null && !this.name.trim().equals("") && this.weight != null && this.isValidNUmber(this.weight, Double.class) &&
		this.code != null && !this.code.trim().equals("") && this.image != null && !this.image.trim().equals("");
	}

	public int getMedId() {
		return medId;
	}





	public String getName() {
		return name;
	}





	public String getWeight() {
		return weight;
	}





	public String getCode() {
		return code;
	}





	public int getDroneId() {
		return droneId;
	}





	public String getImage() {
		return image;
	}





	public Date getCreatedAt() {
		return createdAt;
	}





	public void setMedId(int medId) {
		this.medId = medId;
	}





	public void setName(String name) {
		this.name = name;
	}





	public void setWeight(String weight) {
		this.weight = weight;
	}





	public void setCode(String code) {
		this.code = code;
	}





	public void setDroneId(int droneId) {
		this.droneId = droneId;
	}





	public void setImage(String image) {
		this.image = image;
	}





	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	 private boolean isValidNUmber(String number, Class<?> _class) {
	        if (number == null || number.trim().equals(""))
	            return false;

	        try {
	            number = number.trim();
	            if (_class == Integer.class) {
	                Integer.parseInt(number);
	            }
	            if (_class == Long.class) {
	                Long.parseLong(number);
	            }
	            if (_class == Float.class) {
	                Float.parseFloat(number);
	            }
	            if (_class == Double.class) {
	                Double.parseDouble(number);
	            }
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            return false;
	        }

	        return true;
	    }


	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
