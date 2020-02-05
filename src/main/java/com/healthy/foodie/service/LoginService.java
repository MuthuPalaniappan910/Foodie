package com.healthy.foodie.service;

import javax.validation.Valid;

import com.healthy.foodie.dto.LoginRequestDto;
import com.healthy.foodie.entity.User;
import com.healthy.foodie.exception.UserNotFoundException;

public interface LoginService {

	User checkLogin(@Valid LoginRequestDto loginRequestDto) throws UserNotFoundException;

}
