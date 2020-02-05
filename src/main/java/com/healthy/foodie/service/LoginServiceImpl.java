package com.healthy.foodie.service;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthy.foodie.constants.ApplicationConstants;
import com.healthy.foodie.dto.LoginRequestDto;
import com.healthy.foodie.entity.User;
import com.healthy.foodie.exception.UserNotFoundException;
import com.healthy.foodie.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


/*
 *  Method is used for user login
 */

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
	@Autowired
	UserRepository userRepository;

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
	@Override
	public User checkLogin(@Valid LoginRequestDto loginRequestDto) throws UserNotFoundException {
		Long mobileNumber = Long.parseLong(loginRequestDto.getMobileNumber());
		User customer = userRepository.findBymobileNumberAndPassword(mobileNumber, loginRequestDto.getPassword());
		if (Objects.isNull(customer)) {
			log.error(ApplicationConstants.USER_NOTFOUND_MESSAGE);
			throw new UserNotFoundException(ApplicationConstants.USER_NOTFOUND_MESSAGE);
		}
		return customer;
	}
}
