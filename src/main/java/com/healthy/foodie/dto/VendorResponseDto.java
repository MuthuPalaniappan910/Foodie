package com.healthy.foodie.dto;

import java.util.List;

import com.healthy.foodie.entity.Vendor;

public class VendorResponseDto {
	
	private int statusCode;
	private String message;
	private List<Vendor> vendors;
	private String image;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public List<Vendor> getListofvendors() {
		return vendors;
	}
	public void setListofvendors(List<Vendor> listofvendors) {
		this.vendors = listofvendors;
	}
	

}
