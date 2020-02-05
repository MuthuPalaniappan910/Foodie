package com.healthy.foodie.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthy.foodie.constants.ApplicationConstants;
import com.healthy.foodie.dto.LoginRequestDto;
import com.healthy.foodie.dto.LoginResponseDto;
import com.healthy.foodie.entity.User;
import com.healthy.foodie.exception.UserNotFoundException;
import com.healthy.foodie.service.LoginService;

import lombok.extern.slf4j.Slf4j;


/*
 *  Method is used for user login
 */

@RequestMapping("/login")
@RestController
@Slf4j
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class LoginController {
	@Autowired
	LoginService loginService;

	/**
	 * @author Muthu
	 * 
	 *         Method is used to classify a user based on the credentials as a admin
	 *         or customer
	 * 
	 * @param loginRequestDto which gets user mobile number and password
	 * @return LoginResponseDto contains message,statusCode,role,customerId and
	 *         his/her name
	 * @throws UserNotFoundException
	 */
	@PostMapping
	public ResponseEntity<LoginResponseDto> checkLogin(@Valid @RequestBody LoginRequestDto loginRequestDto)
			throws UserNotFoundException {
		log.info("For checking whether the person is a manager or not");
		User userResponse = loginService.checkLogin(loginRequestDto);
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		log.info(ApplicationConstants.LOGIN_SUCCESSMESSAGE);
		loginResponseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
		loginResponseDto.setMessage(ApplicationConstants.LOGIN_SUCCESSMESSAGE);
		BeanUtils.copyProperties(userResponse, loginResponseDto);
		return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
	}
}
