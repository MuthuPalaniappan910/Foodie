package com.healthy.foodie.service;

import java.util.List;


import com.healthy.foodie.dto.OrderRequestDto;
import com.healthy.foodie.dto.OrderResponseDto;
import com.healthy.foodie.entity.Vendor;
import com.healthy.foodie.dto.MenuList;
import com.healthy.foodie.exception.MenuNotFoundException;
import com.healthy.foodie.exception.VendorNotFoundException;
public interface VendorService {

	List<MenuList> getVendorDetails(Long vendorId) throws VendorNotFoundException;
List<Vendor> getAllVendor();
	
	public OrderResponseDto orderAndPayment(OrderRequestDto orderRequest, String paymenttype, Long customerId);
	MenuList getMenuDetails(Long menuId) throws MenuNotFoundException;
}
