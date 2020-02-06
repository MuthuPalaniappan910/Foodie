package com.healthy.foodie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthy.foodie.constants.ApplicationConstants;
import com.healthy.foodie.dto.OrderDetails;
import com.healthy.foodie.entity.OrderDetail;
import com.healthy.foodie.exception.OrderHistoryNotFoundException;
import com.healthy.foodie.repository.OrderDetaiRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private OrderDetaiRepository orderDetailRepository;

	@Override
	public List<OrderDetails> getOrderHistory(Long customerId) throws OrderHistoryNotFoundException {
		List<OrderDetail> orderDetail = orderDetailRepository.findByCustomerId(customerId);
		List<OrderDetails> listoforders = new ArrayList<>();

		if (orderDetail.isEmpty()) {
			throw new OrderHistoryNotFoundException(ApplicationConstants.ORDER_HISTORY_NOT_FOUND_MESSAGE);
		}
		orderDetail.forEach(order -> {
			OrderDetails orderDetails = new OrderDetails();
			BeanUtils.copyProperties(order, orderDetails);
			listoforders.add(orderDetails);
		});
		return listoforders;
	}

}
