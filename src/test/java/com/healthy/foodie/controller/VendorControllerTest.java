package com.healthy.foodie.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.healthy.foodie.dto.OrderRequestDto;
import com.healthy.foodie.dto.OrderResponseDto;
import com.healthy.foodie.dto.VendorResponseDto;
import com.healthy.foodie.entity.Vendor;
import com.healthy.foodie.service.VendorServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class VendorControllerTest {
	
	@InjectMocks
	VendorController vendorController;
	
	@Mock
	VendorServiceImpl vendorServiceImpl;
	
	@Test
	public void getAllVendorTest() {
		List listofvendors = new ArrayList<Vendor>();
		Vendor vendor = new Vendor();
		vendor.setVendorId(1L);
		vendor.setVendorName("snackit");
		listofvendors.add(vendor);
		List<Vendor> listofvendors1 = vendorServiceImpl.getAllVendor();
		assertNotNull(listofvendors1);
	}
	@Test
	public void getAllVendorTestNegative() {
		List listofvendors = null;
		VendorResponseDto vendor = new VendorResponseDto();
		vendor.setStatusCode(HttpStatus.OK.value());
		Mockito.when(vendorServiceImpl.getAllVendor()).thenReturn(listofvendors);
		VendorResponseDto response = vendorController.getAllVendor();
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}
	@Test
	public void orderAndPayment() {
		OrderRequestDto request = new OrderRequestDto();
		OrderResponseDto response = new OrderResponseDto();
		request.setVendorId(1L);
		String paymenttype = "GPay";
		Long customerId = 1L;
		Mockito.when(vendorServiceImpl.orderAndPayment(request, paymenttype, customerId)).thenReturn(response);
		response = vendorController.orderAndPayment(request, paymenttype, customerId);
		assertNotNull(response);
	}
	@Test
	public void orderAndPaymentNegative() {
		OrderRequestDto request = new OrderRequestDto();
		OrderResponseDto response = new OrderResponseDto();
		String paymenttype = null;
		Long customerId = null;
		Mockito.when(vendorServiceImpl.orderAndPayment(request, paymenttype, customerId)).thenReturn(response);
		response = vendorController.orderAndPayment(request, paymenttype, customerId);
	}

}
