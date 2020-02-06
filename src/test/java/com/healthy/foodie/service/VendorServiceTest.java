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

import com.healthy.foodie.dto.MenuList;
import com.healthy.foodie.entity.Food;
import com.healthy.foodie.entity.Menu;
import com.healthy.foodie.exception.MenuNotFoundException;
import com.healthy.foodie.exception.VendorNotFoundException;
import com.healthy.foodie.repository.FoodRepository;
import com.healthy.foodie.repository.MenuRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class VendorServiceTest {
	List<Menu> menuList = null;
	List<Menu> menuList1 = null;
	Menu menu = null;
	Food food = null;
	Menu menuList3 = null;

	@InjectMocks
	VendorServiceImpl vendorServiceImpl;

	@Mock
	MenuRepository menuRepository;

	@Mock
	FoodRepository foodRepository;

	@Before
	public void before() {
		menuList = new ArrayList<>();
		menu = new Menu();
		food = new Food();
		food.setFoodId(1L);
		food.setFoodName("Dosa");
		menu.setMenuId(1L);
		menu.setFoodId(1L);
		menu.setPrice(200.00);
		menu.setVendorId(1L);
		menuList.add(menu);

		menuList1 = new ArrayList<>();

		menuList3 = new Menu();
		menuList3.setMenuId(1L);
		menuList3.setFoodId(1L);
		menuList3.setVendorId(1L);
	}

	@Test
	public void testGetVendorDetailsPositive() throws VendorNotFoundException {
		Long vendorId = 100L;
		Mockito.when(menuRepository.findAllByVendorId(vendorId)).thenReturn(menuList);
		Mockito.when(foodRepository.findByFoodId(1L)).thenReturn(food);
		List<MenuList> response = vendorServiceImpl.getVendorDetails(vendorId);
		assertEquals(menuList.size(), response.size());
	}

	@Test(expected = VendorNotFoundException.class)
	public void testGetVendorDetailsVendorException() throws VendorNotFoundException {
		Long vendorId = 100L;
		Mockito.when(menuRepository.findAllByVendorId(vendorId)).thenReturn(menuList1);
		vendorServiceImpl.getVendorDetails(vendorId);
	}

	@Test
	public void testGetMenuDetailsPositive() throws MenuNotFoundException {
		Long menuId = 100L;
		Mockito.when(menuRepository.findById(menuId)).thenReturn(Optional.of(menuList3));
		Mockito.when(foodRepository.findByFoodId(1L)).thenReturn(food);
		MenuList response = vendorServiceImpl.getMenuDetails(menuId);
		assertEquals(menuList3.getMenuId(), response.getMenuId());
	}

	@Test(expected = MenuNotFoundException.class)
	public void testGetMenuDetailsNegative() throws MenuNotFoundException {
		Long menuId = 100L;
		Mockito.when(menuRepository.findById(menuId)).thenReturn(Optional.ofNullable(null));
		vendorServiceImpl.getMenuDetails(menuId);
	}

}
