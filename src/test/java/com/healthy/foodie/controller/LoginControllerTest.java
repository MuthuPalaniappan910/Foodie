package com.healthy.foodie.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.healthy.foodie.dto.LoginRequestDto;
import com.healthy.foodie.dto.LoginResponseDto;
import com.healthy.foodie.entity.User;
import com.healthy.foodie.exception.UserNotFoundException;
import com.healthy.foodie.service.LoginService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginControllerTest {
	LoginRequestDto loginRequestDto = null;
	User user = null;

	@InjectMocks
	LoginController loginController;

	@Mock
	LoginService loginService;

	@Before
	public void before() {
		loginRequestDto = new LoginRequestDto();
		loginRequestDto.setMobileNumber("9876543210");
		loginRequestDto.setPassword("muthu123");

		user = new User();
		user.setMobileNumber(9876543210L);
		user.setPassword("muthu123");
	}

	@Test
	public void testCheckLoginPositive() throws UserNotFoundException {
		Mockito.when(loginService.checkLogin(loginRequestDto)).thenReturn(user);
		ResponseEntity<LoginResponseDto> response = loginController.checkLogin(loginRequestDto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
