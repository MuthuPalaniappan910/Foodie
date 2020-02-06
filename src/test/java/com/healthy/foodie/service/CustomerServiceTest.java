package com.healthy.foodie.service;

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

import com.healthy.foodie.dto.OrderDetails;
import com.healthy.foodie.entity.OrderDetail;
import com.healthy.foodie.exception.OrderHistoryNotFoundException;
import com.healthy.foodie.repository.OrderDetaiRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerServiceTest {
	List<OrderDetail> orderDetailList = null;
	OrderDetail orderDetail = null;

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@Mock
	OrderDetaiRepository orderDetailRepository;

	@Before
	public void before() {
		orderDetailList = new ArrayList<>();
		orderDetail = new OrderDetail();
		orderDetail.setOrderId(1L);
		orderDetail.setCustomerId(1L);
		orderDetail.setOrderPlacedDate(LocalDate.now());
		orderDetail.setTotalPrice(100.00);
		orderDetailList.add(orderDetail);
	}

	@Test
	public void testGetOrderHistoryPositive() throws OrderHistoryNotFoundException {
		Long customerId = 1L;
		Mockito.when(orderDetailRepository.findByCustomerId(customerId)).thenReturn(orderDetailList);
		List<OrderDetails> response = customerServiceImpl.getOrderHistory(customerId);
		assertEquals(orderDetailList.size(), response.size());
	}

	@Test(expected = OrderHistoryNotFoundException.class)
	public void testGetOrderHistoryNegative() throws OrderHistoryNotFoundException {
		Long customerId = 1L;
		Mockito.when(orderDetailRepository.findByCustomerId(customerId)).thenReturn(orderDetailList);
		customerServiceImpl.getOrderHistory(2L);
	}

}
