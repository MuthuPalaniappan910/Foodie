package com.healthy.foodie.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
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

import com.healthy.foodie.dto.OrderDetails;
import com.healthy.foodie.dto.OrderHistoryDto;
import com.healthy.foodie.exception.OrderHistoryNotFoundException;
import com.healthy.foodie.service.CustomerService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerControllerTest {
	List<OrderDetails> orderDetailList = null;
	List<OrderDetails> orderDetailList1 = null;
	OrderDetails orderDetail = null;

	@InjectMocks
	CustomerController customerController;

	@Mock
	CustomerService customerService;

	@Before
	public void before() {
		orderDetailList = new ArrayList<>();
		orderDetail = new OrderDetails();
		orderDetail.setOrderId(1L);
		orderDetail.setOrderPlacedDate(LocalDate.now());
		orderDetail.setTotalPrice(100.00);
		orderDetailList.add(orderDetail);
	}

	@Test
	public void testGetOrderHistoryPositive() throws OrderHistoryNotFoundException {
		Long customerId = 1L;
		Mockito.when(customerService.getOrderHistory(customerId)).thenReturn(orderDetailList);
		ResponseEntity<OrderHistoryDto> response = customerController.getOrderHistory(customerId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testGetOrderHistoryNegative() throws OrderHistoryNotFoundException {
		Long customerId = 1L;
		Mockito.when(customerService.getOrderHistory(customerId)).thenReturn(orderDetailList1);
		ResponseEntity<OrderHistoryDto> response = customerController.getOrderHistory(1L);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

}
