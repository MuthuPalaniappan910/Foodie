package com.healthy.foodie.service;

import org.springframework.stereotype.Service;

import com.healthy.foodie.constants.ApplicationConstants;

@Service("PhonePe")
public class PhonePe implements PaymentService {

	@Override
	public String payment(String paymentType) {
		return ApplicationConstants.PHONEPE_PAYMENT;

	}

}
