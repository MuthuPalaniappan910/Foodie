package com.healthy.foodie.dto;

import java.util.List;

public class AdminRequestDto {
	
	private String vendorName;
	private String vendorImage;
	public String getVendorImage() {
		return vendorImage;
	}
	public void setVendorImage(String vendorImage) {
		this.vendorImage = vendorImage;
	}
	private List<MenuDetail> menuDetails;
	
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public List<MenuDetail> getMenuDetails() {
		return menuDetails;
	}
	public void setMenuDetails(List<MenuDetail> menuDetails) {
		this.menuDetails = menuDetails;
	}

}
