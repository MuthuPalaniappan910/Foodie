package com.healthy.foodie.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuResponseDto {
	private List<MenuList> menuList;
	private String message;
	private Integer statusCode;
}
