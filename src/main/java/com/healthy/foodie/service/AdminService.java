package com.healthy.foodie.service;


import com.healthy.foodie.dto.AdminRequestDto;
import com.healthy.foodie.dto.ResponseDto;

public interface AdminService {

	ResponseDto addMenu(AdminRequestDto adminRequestDto);

}
