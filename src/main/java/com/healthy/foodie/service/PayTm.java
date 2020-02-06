package com.healthy.foodie.service;

import org.springframework.stereotype.Service;

import com.healthy.foodie.constants.ApplicationConstants;

@Service("PayTm")
public class PayTm implements PaymentService {

	@Override
	public String payment(String paymentType) {
		return ApplicationConstants.PAYTM_PAYMENT;
	}

}