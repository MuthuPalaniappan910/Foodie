package com.healthy.foodie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthy.foodie.constants.ApplicationConstants;
import com.healthy.foodie.dto.OrderDetails;
import com.healthy.foodie.dto.OrderHistoryDto;
import com.healthy.foodie.exception.OrderHistoryNotFoundException;
import com.healthy.foodie.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customer")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@Slf4j
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * @author Karthika
	 * 
	 *         Method is used to view the list of orders made by the particular
	 *         customer
	 * 
	 * @param customerId
	 * @return OrderHistoryDto Displays the order details along with a status code
	 *         and message
	 * @throws OrderHistoryNotFoundException
	 */
	@GetMapping("/{customerId}/orders")
	public ResponseEntity<OrderHistoryDto> getOrderHistory(@PathVariable Long customerId)
			throws OrderHistoryNotFoundException {
		List<OrderDetails> orderDetails = customerService.getOrderHistory(customerId);
		if (orderDetails != null) {
			OrderHistoryDto orderHistoryDto = new OrderHistoryDto();
			orderHistoryDto.setStatuscode(ApplicationConstants.SUCCESS_CODE);
			orderHistoryDto.setMessage(ApplicationConstants.ORDER_HISTORY_SUCCESSMESSAGE);
			orderHistoryDto.setOrderDetails(orderDetails);
			return new ResponseEntity<>(orderHistoryDto, HttpStatus.OK);
		}
		OrderHistoryDto orderHistoryDto = new OrderHistoryDto();
		return new ResponseEntity<>(orderHistoryDto, HttpStatus.NOT_FOUND);
	}

}
