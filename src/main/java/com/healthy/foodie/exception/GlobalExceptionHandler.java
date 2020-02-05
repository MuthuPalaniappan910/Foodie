package com.healthy.foodie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.healthy.foodie.constants.ApplicationConstants;
import com.healthy.foodie.dto.ResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseDto> userNotFoundException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(ApplicationConstants.USER_NOTFOUND_MESSAGE);
		responseDto.setStatusCode(ApplicationConstants.NOTFOUND_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}

	@ExceptionHandler(VendorNotFoundException.class)
	public ResponseEntity<ResponseDto> vendorNotFoundException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(ApplicationConstants.VENDOR_NOTFOUND_MESSAGE);
		responseDto.setStatusCode(ApplicationConstants.NOTFOUND_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}

	@ExceptionHandler(MenuNotFoundException.class)
	public ResponseEntity<ResponseDto> menuNotFoundException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(ApplicationConstants.MENU_NOTFOUND_MESSAGE);
		responseDto.setStatusCode(ApplicationConstants.NOTFOUND_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}

}
