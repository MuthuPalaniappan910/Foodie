package com.healthy.foodie.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
	private String mobileNumber;
	private String password;
}
