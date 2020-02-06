package com.healthy.foodie.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.healthy.foodie.constants.ApplicationConstants;
import com.healthy.foodie.dto.MenuList;
import com.healthy.foodie.dto.OrderRequestDto;
import com.healthy.foodie.dto.OrderResponseDto;
import com.healthy.foodie.dto.OrderedMenu;
import com.healthy.foodie.entity.Food;
import com.healthy.foodie.entity.Menu;
import com.healthy.foodie.entity.OrderDetail;
import com.healthy.foodie.entity.OrderedItem;
import com.healthy.foodie.entity.Vendor;
import com.healthy.foodie.exception.MenuNotFoundException;
import com.healthy.foodie.exception.NoVendorAvailableException;
import com.healthy.foodie.exception.VendorNotFoundException;
import com.healthy.foodie.repository.FoodRepository;
import com.healthy.foodie.repository.MenuRepository;
import com.healthy.foodie.repository.OrderDetaiRepository;
import com.healthy.foodie.repository.VendorRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VendorServiceImpl implements VendorService {

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	FoodRepository foodRepository;

	@Autowired
	OrderDetaiRepository orderDetaiRepository;

	@Autowired
	VendorRepository vendorRepository;

	@Autowired
	PaymentRegistery paymentRegistery;

	/**
	 * @author Vinod B N
	 * @return list of vendors
	 * @exception NoVendorAvailableException
	 */

	@Override
	public List<Vendor> getAllVendor() {
		log.info("Inside VendorServiceImpl of method getAllVendor");
		List<Vendor> list = vendorRepository.findAll();
		if (list.isEmpty()) {
			log.error("No vendor available right now");
			throw new NoVendorAvailableException(ApplicationConstants.NO_VENDOR_AVAILABLE);
		}
		log.info(ApplicationConstants.VENDOR_AVAILABLE);
		return list;
	}

	/**
	 * @author Vinod B N
	 * @param orderRequest, paymentType,customerId
	 * @return orderId
	 */
	public OrderResponseDto orderAndPayment(OrderRequestDto orderRequest, String paymentType, Long customerId) {
		log.info("Inside VendorServiceImpl of orderAndPayment");
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
		orderDetail.setOrderStatus(ApplicationConstants.ORDERED);
		String response = paymentRegistery.getServiceBean(paymentType).payment(paymentType);
		OrderDetail savedOrderDetail = orderDetaiRepository.save(orderDetail);
		OrderResponseDto orderResponseDto = new OrderResponseDto();
		orderResponseDto.setOrderId(savedOrderDetail.getOrderId());
		orderResponseDto.setMessage(response);
		orderResponseDto.setStatusCode(HttpStatus.ACCEPTED.value());
		return orderResponseDto;
	}
	

	/**
	 * @author Muthu
	 * 
	 *         Method is used to get the list of menu details available on a
	 *         particular stall
	 * 
	 * @param vendorId
	 * @return
	 * @throws VendorNotFoundException
	 */

	@Override
	public List<MenuList> getVendorDetails(Long vendorId) throws VendorNotFoundException {
		List<MenuList> vendorMenuList = new ArrayList<>();
		List<Menu> menuList = menuRepository.findAllByVendorId(vendorId);
		if (menuList.isEmpty()) {
			log.error(ApplicationConstants.VENDOR_NOTFOUND_MESSAGE);
			throw new VendorNotFoundException(ApplicationConstants.VENDOR_NOTFOUND_MESSAGE);
		}
		menuList.forEach(menu -> {
			Food food = foodRepository.findByFoodId(menu.getFoodId());
			MenuList menuDetails = new MenuList();
			menuDetails.setMenuId(menu.getMenuId());
			menuDetails.setMenuName(food.getFoodName());
			menuDetails.setMenuPrice(menu.getPrice());
			vendorMenuList.add(menuDetails);
		});
		return vendorMenuList;
	}

	/**
	 * @author Muthu
	 * 
	 *         Method is used to get the details of a particular item
	 * 
	 * @param menuId
	 * @return
	 * @throws MenuNotFoundException
	 */
	@Override
	public MenuList getMenuDetails(Long menuId) throws MenuNotFoundException {
		MenuList menuList = new MenuList();
		Optional<Menu> menu = menuRepository.findById(menuId);
		if (!(menu.isPresent())) {
			log.error(ApplicationConstants.MENU_NOTFOUND_MESSAGE);
			throw new MenuNotFoundException(ApplicationConstants.MENU_NOTFOUND_MESSAGE);
		}
		Long menuIdDetails = menu.get().getFoodId();
		Food food = foodRepository.findByFoodId(menuIdDetails);
		menuList.setMenuId(menu.get().getMenuId());
		menuList.setMenuName(food.getFoodName());
		menuList.setMenuPrice(menu.get().getPrice());
		return menuList;

	}
}
