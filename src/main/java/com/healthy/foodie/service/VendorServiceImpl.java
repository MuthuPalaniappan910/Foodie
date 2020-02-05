package com.healthy.foodie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthy.foodie.constants.ApplicationConstants;
import com.healthy.foodie.dto.MenuList;
import com.healthy.foodie.entity.Food;
import com.healthy.foodie.entity.Menu;
import com.healthy.foodie.exception.MenuNotFoundException;
import com.healthy.foodie.exception.VendorNotFoundException;
import com.healthy.foodie.repository.FoodRepository;
import com.healthy.foodie.repository.MenuRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VendorServiceImpl implements VendorService {
	@Autowired
	MenuRepository menuRepository;

	@Autowired
	FoodRepository foodRepository;

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
