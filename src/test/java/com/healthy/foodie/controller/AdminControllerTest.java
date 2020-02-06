package com.healthy.foodie.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.healthy.foodie.dto.AdminRequestDto;
import com.healthy.foodie.dto.MenuDetail;
import com.healthy.foodie.dto.MenuList;
import com.healthy.foodie.dto.MenuResponseDto;
import com.healthy.foodie.dto.ResponseDto;
import com.healthy.foodie.exception.MenuNotFoundException;
import com.healthy.foodie.exception.VendorNotFoundException;
import com.healthy.foodie.service.AdminService;
import com.healthy.foodie.service.VendorService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AdminControllerTest {
	
	AdminRequestDto adminRequestDto= null;
	ResponseDto responseDto = null;
	List<MenuDetail> menuDetails= null;
	MenuDetail menuDetail1=null;
	MenuDetail menuDetail2=null;

	@InjectMocks
	AdminController adminController;

	@Mock
	AdminService adminService;

	@Before
	public void before() {
		adminRequestDto = new AdminRequestDto();
		adminRequestDto.setVendorName("Give me 5");
		adminRequestDto.setVendorImage("jsdhsjkd");
		menuDetails = new ArrayList<>();
		menuDetail1 = new MenuDetail();
		menuDetail2 = new MenuDetail();
		menuDetail1.setMenuName("Poori");
		menuDetail1.setMenuPrice("40");
		menuDetail2.setMenuName("idly");
		menuDetail2.setMenuPrice("30");
		menuDetails.add(menuDetail1);
		menuDetails.add(menuDetail2);
		adminRequestDto.setMenuDetails(menuDetails);
		responseDto = new ResponseDto();
		responseDto.setMessage("Menu added successfully");
		responseDto.setStatusCode(200);
	}

	@Test
	public void testAddMenuPositive() throws VendorNotFoundException {
		Mockito.when(adminService.addMenu(adminRequestDto)).thenReturn(responseDto);
		ResponseEntity<ResponseDto> response = adminController.addMenu(adminRequestDto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
