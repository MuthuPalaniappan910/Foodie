package com.healthy.foodie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.healthy.foodie.constants.ApplicationConstants;
import com.healthy.foodie.dto.ResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ResponseDto> customerNotFoundException() {
		ResponseDto responseDto = new ResponseDto();
		//responseDto.setMessage(ApplicationConstants.CUSTOMER_NOTFOUND_MESSAGE);
		//responseDto.setStatusCode(ApplicationConstants.NOTFOUND_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}
	
	@ExceptionHandler(OrderHistoryNotFoundException.class)
	public ResponseEntity<ResponseDto> OrderHistoryNotFoundException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(ApplicationConstants.ORDER_HISTORY_NOT_FOUND_MESSAGE);
		responseDto.setStatusCode(ApplicationConstants.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}

}
