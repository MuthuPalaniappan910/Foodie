package com.healthy.foodie.service;


import java.util.List;

import com.healthy.foodie.dto.OrderDetails;
import com.healthy.foodie.exception.OrderHistoryNotFoundException;

public interface CustomerService {

	List<OrderDetails> getOrderHistory(Long customerId) throws OrderHistoryNotFoundException;

}
