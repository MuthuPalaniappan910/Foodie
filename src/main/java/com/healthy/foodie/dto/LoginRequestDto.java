package com.healthy.foodie.dto;

import javax.validation.constraints.NotBlank;

import com.healthy.foodie.constants.ApplicationConstants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
	@NotBlank(message=ApplicationConstants.BLANK_MESSAGE)
	private String mobileNumber;
	@NotBlank(message=ApplicationConstants.BLANK_MESSAGE)
	private String password;
}
