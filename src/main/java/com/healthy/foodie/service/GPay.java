package com.healthy.foodie.service;

import org.springframework.stereotype.Service;

import com.healthy.foodie.constants.ApplicationConstants;

@Service("GPay")
public class GPay implements PaymentService  {

	@Override
	public String payment(String paymentType) {
		return ApplicationConstants.GPAY_PAYMENT;
	}
	

}
