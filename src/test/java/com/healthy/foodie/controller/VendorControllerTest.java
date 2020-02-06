package com.healthy.foodie.controller;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.healthy.foodie.dto.OrderRequestDto;
import com.healthy.foodie.dto.OrderResponseDto;
import com.healthy.foodie.dto.VendorResponseDto;
import com.healthy.foodie.entity.Vendor;
import com.healthy.foodie.exception.MenuNotFoundException;
import com.healthy.foodie.exception.VendorNotFoundException;
import com.healthy.foodie.service.VendorService;
import com.healthy.foodie.service.VendorServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class VendorControllerTest {

	MenuResponseDto menuResponseDto = null;
	List<MenuList> menuList = null;
	MenuList menu = null;
	List<MenuList> menuList1 = null;
	MenuList menuList2 = null;
	MenuList menuList4 = null;

	@InjectMocks
	VendorController vendorController;

	@Mock
	VendorService vendorService;
	
	@Mock
	VendorServiceImpl vendorServiceImpl;

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

		menuList2 = new MenuList();
		menuList2.setMenuId(1L);
		menuList2.setMenuName("Dosa");
		menuList2.setMenuPrice(200.00);

	}
	
	@Test
	public void getAllVendorTest() {
		List listofvendors = new ArrayList<Vendor>();
		Vendor vendor = new Vendor();
		vendor.setVendorId(1L);
		vendor.setVendorName("snackit");
		listofvendors.add(vendor);
		List<Vendor> listofvendors1 = vendorServiceImpl.getAllVendor();
		assertNotNull(listofvendors1);
	}
	@Test
	public void getAllVendorTestNegative() {
		List listofvendors = null;
		VendorResponseDto vendor = new VendorResponseDto();
		vendor.setStatusCode(HttpStatus.OK.value());
		Mockito.when(vendorServiceImpl.getAllVendor()).thenReturn(listofvendors);
		VendorResponseDto response = vendorController.getAllVendor();
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}
	@Test
	public void orderAndPayment() {
		OrderRequestDto request = new OrderRequestDto();
		OrderResponseDto response = new OrderResponseDto();
		request.setVendorId(1L);
		String paymenttype = "GPay";
		Long customerId = 1L;
		Mockito.when(vendorServiceImpl.orderAndPayment(request, paymenttype, customerId)).thenReturn(response);
		response = vendorController.orderAndPayment(request, paymenttype, customerId);
		assertNotNull(response);
	}
	@Test
	public void orderAndPaymentNegative() {
		OrderRequestDto request = new OrderRequestDto();
		OrderResponseDto response = new OrderResponseDto();
		String paymenttype = null;
		Long customerId = null;
		Mockito.when(vendorServiceImpl.orderAndPayment(request, paymenttype, customerId)).thenReturn(response);
		response = vendorController.orderAndPayment(request, paymenttype, customerId);
	}


	@Test
	public void testGetVendorDetailsPositive() throws VendorNotFoundException {
		Long vendorId = 100L;
		Mockito.when(vendorService.getVendorDetails(vendorId)).thenReturn(menuList);
		ResponseEntity<MenuResponseDto> response = vendorController.getVendorDetails(vendorId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testGetVendorDetailsNegative() throws VendorNotFoundException {
		Long vendorId = 100L;
		Mockito.when(vendorService.getVendorDetails(vendorId)).thenReturn(menuList1);
		ResponseEntity<MenuResponseDto> response = vendorController.getVendorDetails(vendorId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void testGetMenuDetailsPositive() throws MenuNotFoundException {
		Long menuId = 10L;
		Mockito.when(vendorService.getMenuDetails(10L)).thenReturn(menuList2);
		ResponseEntity<MenuList> response = vendorController.getMenuDetails(menuId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testGetMenuDetailsNegative() throws MenuNotFoundException {
		Long menuId = 10L;
		Mockito.when(vendorService.getMenuDetails(menuId)).thenReturn(menuList4);
		ResponseEntity<MenuList> response = vendorController.getMenuDetails(10L);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

}
