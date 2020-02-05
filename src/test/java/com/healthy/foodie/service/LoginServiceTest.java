package com.healthy.foodie.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.healthy.foodie.dto.LoginRequestDto;
import com.healthy.foodie.entity.User;
import com.healthy.foodie.exception.UserNotFoundException;
import com.healthy.foodie.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginServiceTest {
	LoginRequestDto loginRequestDto = null;
	LoginRequestDto loginRequestDto1 = null;
	User user = null;

	@InjectMocks
	LoginServiceImpl loginServiceImpl;

	@Mock
	UserRepository userRepository;

	@Before
	public void before() {
		loginRequestDto = new LoginRequestDto();
		loginRequestDto.setMobileNumber("9876543210");
		loginRequestDto.setPassword("muthu123");

		loginRequestDto1 = new LoginRequestDto();
		loginRequestDto1.setMobileNumber("9876543210");
		loginRequestDto1.setPassword("muthu1234");

		user = new User();
		user.setMobileNumber(9876543210L);
		user.setPassword("muthu123");
		user.setUserName("Muthu");
	}

	@Test
	public void testCheckLoginPositive() throws UserNotFoundException {
		Long mobileNumber = Long.parseLong(loginRequestDto.getMobileNumber());
		Mockito.when(userRepository.findBymobileNumberAndPassword(mobileNumber, loginRequestDto.getPassword()))
				.thenReturn(user);
		User response = loginServiceImpl.checkLogin(loginRequestDto);
		assertEquals(user.getUserName(), response.getUserName());
	}

	@Test(expected = UserNotFoundException.class)
	public void testCheckLoginException() throws UserNotFoundException {
		Long mobileNumber = Long.parseLong(loginRequestDto.getMobileNumber());
		Mockito.when(userRepository.findBymobileNumberAndPassword(mobileNumber, loginRequestDto.getPassword()))
				.thenReturn(user);
		loginServiceImpl.checkLogin(loginRequestDto1);
	}
}
