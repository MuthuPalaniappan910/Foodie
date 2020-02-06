package com.healthy.foodie.dto;

import java.util.List;

public class OrderRequestDto {
	
	private Long vendorId;
	private List<OrderedMenu> orderedMenus;
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	public List<OrderedMenu> getOrderedMenus() {
		return orderedMenus;
	}
	public void setOrderedMenus(List<OrderedMenu> orderedMenus) {
		this.orderedMenus = orderedMenus;
	}


}
