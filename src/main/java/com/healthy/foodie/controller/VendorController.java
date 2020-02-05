package com.healthy.foodie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthy.foodie.constants.ApplicationConstants;
import com.healthy.foodie.dto.OrderRequestDto;
import com.healthy.foodie.dto.OrderResponseDto;
import com.healthy.foodie.dto.VendorResponseDto;
import com.healthy.foodie.entity.Vendor;
import com.healthy.foodie.service.VendorServiceImpl;

/*
 * Method is used to view the list of Vendor available in the application 
 */
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
@RequestMapping("/vendors")
public class VendorController {

	@Autowired
	VendorServiceImpl vendorServiceImpl;

	/**
	 * @author Vinod B N
	 * 
	 * 
	 * @param Vendor of vendor
	 * @return List of vendors
	 * @return response with User message and status code
	 * 
	 */
	@GetMapping()
	public VendorResponseDto getAllVendor() {
		List<Vendor> listofvendors = vendorServiceImpl.getAllVendor();
		VendorResponseDto vendorResponseDto = new VendorResponseDto();
		vendorResponseDto.setListofvendors(listofvendors);
		vendorResponseDto.setMessage(ApplicationConstants.VENDOR_AVAILABLE);
		vendorResponseDto.setStatusCode(HttpStatus.OK.value());
		return vendorResponseDto;
	}

	/**
	 * @author Vinod B N
	 * 
	 * 
	 * @param orderRequest , paymenttype ,customerId
	 * @return ordrerId
	 * @return response with User message and status code
	 * 
	 */
	@PostMapping("/customers/{customerId}/orders")
	public OrderResponseDto orderAndPayment(OrderRequestDto orderRequest, String paymenttype, Long customerId) {
		return vendorServiceImpl.orderAndPayment(orderRequest, paymenttype, customerId);

	}

}
