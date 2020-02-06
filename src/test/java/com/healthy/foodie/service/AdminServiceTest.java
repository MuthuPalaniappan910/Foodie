package com.healthy.foodie.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.healthy.foodie.dto.AdminRequestDto;
import com.healthy.foodie.dto.MenuDetail;
import com.healthy.foodie.dto.MenuList;
import com.healthy.foodie.dto.ResponseDto;
import com.healthy.foodie.entity.Food;
import com.healthy.foodie.entity.Menu;
import com.healthy.foodie.entity.Vendor;
import com.healthy.foodie.exception.MenuNotFoundException;
import com.healthy.foodie.exception.VendorNotFoundException;
import com.healthy.foodie.repository.FoodRepository;
import com.healthy.foodie.repository.MenuRepository;
import com.healthy.foodie.repository.VendorRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AdminServiceTest {

	AdminRequestDto adminRequestDto= null;
	ResponseDto responseDto = null;
	List<MenuDetail> menuDetails= null;
	MenuDetail menuDetail1=null;
	MenuDetail menuDetail2=null;
	Vendor vendor=null;
	Food food = null;
	
	@InjectMocks
	AdminServiceImpl adminServiceImpl;
	
	@Mock
	VendorRepository vendorRepository;

	@Mock
	MenuRepository menuRepository;

	@Mock
	FoodRepository foodRepository;

	@Before
	public void before() {
		adminRequestDto = new AdminRequestDto();
		adminRequestDto.setVendorName("Give me 5");
		adminRequestDto.setVendorImage("jsdhsjkd");
		menuDetails = new ArrayList<>();
		menuDetail1 = new MenuDetail();
		menuDetail2 = new MenuDetail();
		menuDetail1.setMenuName("Poori");
		menuDetail1.setMenuPrice("40");
		menuDetail2.setMenuName("idly");
		menuDetail2.setMenuPrice("30");
		menuDetails.add(menuDetail1);
		//menuDetails.add(menuDetail2);
		adminRequestDto.setMenuDetails(menuDetails);
		responseDto = new ResponseDto();
		vendor = new Vendor();
		food = new Food();
		vendor.setImage("jsdhsjkd");
		vendor.setVendorId(1L);
		vendor.setVendorName("Give me 5");
		food.setFoodId(1L);
		food.setFoodName("Poori");
		responseDto.setMessage("Menu added successfully");
		responseDto.setStatusCode(200);
	}

	@Test
	public void testGetVendorDetailsPositive() throws VendorNotFoundException {
		Mockito.when(vendorRepository.findByVendorName(adminRequestDto.getVendorName())).thenReturn(Optional.of(vendor));
		for (MenuDetail menuDetail : adminRequestDto.getMenuDetails()) {
			Mockito.when(foodRepository.findByFoodName(menuDetail.getMenuName())).thenReturn(Optional.of(food));
		}
		
		ResponseDto response = adminServiceImpl.addMenu(adminRequestDto);
	}

}
