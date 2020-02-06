package com.healthy.foodie.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthy.foodie.constants.ApplicationConstants;
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

import lombok.extern.slf4j.Slf4j;

/*
 * Method is used to view the list of Vendor available in the application 
 */
@RequestMapping("/vendors")
@RestController
@Slf4j
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class VendorController {
	@Autowired
	VendorService vendorService;

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
	@GetMapping("/{vendorId}")
	public ResponseEntity<MenuResponseDto> getVendorDetails(@PathVariable(name = "vendorId") Long vendorId)
			throws VendorNotFoundException {
		log.info("For getting the list of menu items on a particular stall");
		List<MenuList> menuList = vendorService.getVendorDetails(vendorId);
		MenuResponseDto menuResponseDto = new MenuResponseDto();
		if (!(menuList.isEmpty())) {
			menuResponseDto.setMenuList(menuList);
			log.info(ApplicationConstants.MENU_DISPLAY_SUCCESS_MESSSAGE);
			menuResponseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
			menuResponseDto.setMessage(ApplicationConstants.MENU_DISPLAY_SUCCESS_MESSSAGE);
			return new ResponseEntity<>(menuResponseDto, HttpStatus.OK);
		}
		log.info(ApplicationConstants.MENU_DISPLAY_FAILURE_MESSSAGE);
		menuResponseDto.setStatusCode(ApplicationConstants.NOTFOUND_CODE);
		menuResponseDto.setMessage(ApplicationConstants.MENU_DISPLAY_FAILURE_MESSSAGE);
		return new ResponseEntity<>(menuResponseDto, HttpStatus.NOT_FOUND);
	}

	@Autowired
	VendorServiceImpl vendorServiceImpl;

	/**
	 * @author Vinod B N
	 * 
	 * 
	 * @param Vendor of vendor
	 * @return List of vendors
	 * @return response with User message and status code
	 * 
	 */
	@GetMapping()
	public VendorResponseDto getAllVendor() {
		List<Vendor> listofvendors = vendorServiceImpl.getAllVendor();
		VendorResponseDto vendorResponseDto = new VendorResponseDto();
		vendorResponseDto.setListofvendors(listofvendors);
		vendorResponseDto.setMessage(ApplicationConstants.VENDOR_AVAILABLE);
		vendorResponseDto.setStatusCode(HttpStatus.OK.value());
		return vendorResponseDto;
	}

	/**
	 * @author Vinod B N
	 * 
	 * 
	 * @param orderRequest , paymentType ,customerId
	 * @return ordrerId
	 * @return response with User message and status code
	 * 
	 */
	@PostMapping("/customers/{customerId}/orders")
	public OrderResponseDto orderAndPayment(OrderRequestDto orderRequest, String paymentType, Long customerId) {
		return vendorServiceImpl.orderAndPayment(orderRequest, paymentType, customerId);
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
	@GetMapping("/menu/{menuId}")
	public ResponseEntity<MenuList> getMenuDetails(@PathVariable(name = "menuId") Long menuId)
			throws MenuNotFoundException {
		log.info("For the info of a particular item");
		MenuList menuList = vendorService.getMenuDetails(menuId);
		if (!(Objects.isNull(menuList))) {
			log.info("Particular info about a item is displayed");
			return new ResponseEntity<>(menuList, HttpStatus.OK);
		}
		log.info("No information found about that item");
		return new ResponseEntity<>(menuList, HttpStatus.NOT_FOUND);
	}

}
