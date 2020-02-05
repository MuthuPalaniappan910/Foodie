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

import com.healthy.foodie.dto.MenuList;
import com.healthy.foodie.dto.MenuResponseDto;
import com.healthy.foodie.exception.VendorNotFoundException;
import com.healthy.foodie.service.VendorService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class VendorControllerTest {

	MenuResponseDto menuResponseDto = null;
	List<MenuList> menuList = null;
	MenuList menu = null;
	List<MenuList> menuList1 = null;

	@InjectMocks
	VendorController vendorController;

	@Mock
	VendorService vendorService;

	@Before
	public void before() {
		menuResponseDto = new MenuResponseDto();
		menuList = new ArrayList<>();
		menu = new MenuList();
		menu.setMenuId(1L);
		menu.setMenuName("Dosa");
		menu.setMenuPrice(200.00);
		menuList.add(menu);

		menuList1 = new ArrayList<>();
	}

	@Test
	public void testGetMenuDetailsPositive() throws VendorNotFoundException {
		Long vendorId = 100L;
		Mockito.when(vendorService.getVendorDetails(vendorId)).thenReturn(menuList);
		ResponseEntity<MenuResponseDto> response = vendorController.getVendorDetails(vendorId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testGetMenuDetailsNegative() throws VendorNotFoundException {
		Long vendorId = 100L;
		Mockito.when(vendorService.getVendorDetails(vendorId)).thenReturn(menuList1);
		ResponseEntity<MenuResponseDto> response = vendorController.getVendorDetails(vendorId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

}
