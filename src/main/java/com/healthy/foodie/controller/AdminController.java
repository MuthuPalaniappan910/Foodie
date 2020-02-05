package com.healthy.foodie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthy.foodie.dto.AdminRequestDto;
import com.healthy.foodie.dto.ResponseDto;
import com.healthy.foodie.service.AdminService;

@RestController
@RequestMapping("/admins")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/menu")
	public ResponseEntity<ResponseDto> addMenu(@RequestBody AdminRequestDto adminRequestDto){
		ResponseDto responseDto= adminService.addMenu(adminRequestDto);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

}
