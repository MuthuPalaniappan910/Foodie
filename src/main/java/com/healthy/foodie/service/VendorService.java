package com.healthy.foodie.service;

import java.util.List;

import com.healthy.foodie.dto.OrderRequestDto;
import com.healthy.foodie.dto.OrderResponseDto;
import com.healthy.foodie.entity.Vendor;

public interface VendorService {
	
	List<Vendor> getAllVendor();
	
	public OrderResponseDto orderAndPayment(OrderRequestDto orderRequest, String paymenttype, Long customerId);
	
}
