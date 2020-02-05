package com.healthy.foodie.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.healthy.foodie.constants.ApplicationConstants;
import com.healthy.foodie.dto.OrderRequestDto;
import com.healthy.foodie.dto.OrderResponseDto;
import com.healthy.foodie.dto.OrderedMenu;
import com.healthy.foodie.entity.Menu;
import com.healthy.foodie.entity.OrderDetail;
import com.healthy.foodie.entity.OrderedItem;
import com.healthy.foodie.entity.Vendor;
import com.healthy.foodie.exception.NoVendorAvailableException;
import com.healthy.foodie.repository.MenuRepository;
import com.healthy.foodie.repository.OrderDetaiRepository;
import com.healthy.foodie.repository.VendorRepository;

/*
 * Method is used to view the list of Vendors available in the Application and Order menu for a particular vendor
*/
@Service
public class VendorServiceImpl implements VendorService {
	private static final Logger logger = LoggerFactory.getLogger(VendorServiceImpl.class);
	@Autowired
	VendorRepository vendorRepository;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	OrderDetaiRepository orderDetaiRepository;

	/**
	 * @author Vinod B N
	 * @return list of vendors
	 * @exception NoVendorAvailableException
	 */

	@Override
	public List<Vendor> getAllVendor() {
		logger.info("Inside VendorServiceImpl of method getAllVendor");
		List<Vendor> list = vendorRepository.findAll();
		if (list.isEmpty()) {
			logger.error("No vendor available right now");
			throw new NoVendorAvailableException(ApplicationConstants.NO_VENDOR_AVAILABLE);
		}
		logger.info(ApplicationConstants.VENDOR_AVAILABLE);
		System.out.println(list);
		return list;
	}

	public OrderResponseDto orderAndPayment(OrderRequestDto orderRequest, String paymenttype, Long customerId) {
		logger.info("Inside VendorServiceImpl of orderAndPayment");
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCustomerId(customerId);
		orderDetail.setOrderPlacedDate(LocalDate.now());
		List<OrderedItem> items = new ArrayList<>();
		OrderedItem item = new OrderedItem();
		List<OrderedMenu> menulist = orderRequest.getOrderedMenus();
		menulist.forEach(retrivedItem -> {
			Optional<Menu> menu = menuRepository.findById(retrivedItem.getMenuId());
			item.setMenuId(menu.get().getMenuId());
			item.setQuantity(retrivedItem.getQuantity());
			item.setMenuPrice(retrivedItem.getQuantity() * menu.get().getPrice());
			Double totprice = 0.0;
			totprice = totprice + item.getMenuPrice();
			orderDetail.setTotalPrice(totprice);
		});
		orderDetail.setItems(items);
		if (pay(paymenttype).equalsIgnoreCase(ApplicationConstants.PAYMENT_SUCCESSFULL)) {
			logger.info("Payment success and Confirmed Ordered");
			orderDetail.setOrderStatus(ApplicationConstants.ORDERED);
		}
		OrderDetail savedOrderDetail = orderDetaiRepository.save(orderDetail);
		OrderResponseDto orderResponseDto = new OrderResponseDto();
		orderResponseDto.setOrderId(savedOrderDetail.getOrderId());
		orderResponseDto.setMessage(ApplicationConstants.ORDERED_SUCCESSFULL);
		orderResponseDto.setStatusCode(HttpStatus.ACCEPTED.value());
		return orderResponseDto;

	}

	public String pay(String paymentType) {
		return ApplicationConstants.PAYMENT_SUCCESSFULL;
	}
}
