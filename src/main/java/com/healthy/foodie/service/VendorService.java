package com.healthy.foodie.service;

import java.util.List;

import com.healthy.foodie.dto.MenuList;
import com.healthy.foodie.exception.MenuNotFoundException;
import com.healthy.foodie.exception.VendorNotFoundException;

public interface VendorService {

	List<MenuList> getVendorDetails(Long vendorId) throws VendorNotFoundException;

	MenuList getMenuDetails(Long menuId) throws MenuNotFoundException;

}
