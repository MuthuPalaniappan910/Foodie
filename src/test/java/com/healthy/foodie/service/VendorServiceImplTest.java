package com.healthy.foodie.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;

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

@RunWith(MockitoJUnitRunner.Silent.class)
public class VendorServiceImplTest {
	
	@InjectMocks
	VendorServiceImpl vendorServiceImpl;
	
	@Mock
	VendorRepository vendorRepository;
	@Mock
	MenuRepository menuRepository;
	@Mock
	OrderDetaiRepository orderDetaiRepository;
	@Mock
	PaymentRegistery paymentRegistery;
	
	@Test
	public void getAllVendorTest() {
		List<Vendor> list = new ArrayList<Vendor>();
		Vendor vendor = new Vendor();
		vendor.setVendorId(1L);
		vendor.setVendorName("snackit");
		list.add(vendor);
		Mockito.when(vendorRepository.findAll()).thenReturn(list);
		List<Vendor> venderdb = vendorServiceImpl.getAllVendor();
		assertNotNull(venderdb);
		assertTrue(venderdb.equals(list));
	}
	@Test(expected = NoVendorAvailableException.class)
	public void getAllVendorTestNegative() {
		List<Vendor> list = new ArrayList<Vendor>();
		Mockito.when(vendorRepository.findAll()).thenReturn(list);
		List<Vendor> venderdb = vendorServiceImpl.getAllVendor();
	}
	@Test
	public void orderAndPayment() {
		OrderResponseDto responseDto = new OrderResponseDto();
		OrderRequestDto request = new OrderRequestDto();
		OrderDetail orderDetail = new OrderDetail();
		OrderedItem items = new OrderedItem();
		OrderedMenu orderMenu = new OrderedMenu();
		Menu menu = new Menu();
		String paymentType = "PayTm";
		orderDetail.setOrderPlacedDate(LocalDate.now());
		orderDetail.setOrderId(1L);
		orderDetail.setCustomerId(1L);
		orderDetail.setOrderStatus("Ordered");
		orderDetail.setTotalPrice(items.getMenuPrice());
		menu.setMenuId(1L);
		menu.setFoodId(1L);
		menu.setPrice(45.0);
		menu.setVendorId(1L);
		responseDto.setOrderId(1L);
		responseDto.setMessage("Ordered");
		responseDto.setStatusCode(HttpStatus.ACCEPTED.value());
		request.setVendorId(1l);
		orderMenu.setMenuId(1L);
		orderMenu.setQuantity(2);
		items.setMenuPrice(menu.getPrice()*orderMenu.getQuantity());
		OngoingStubbing<Optional<Menu>> menu1 = Mockito.when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));
		assertNotNull(menu1);
		
	}


}
