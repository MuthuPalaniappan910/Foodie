package com.healthy.foodie.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthy.foodie.constants.ApplicationConstants;
import com.healthy.foodie.dto.AdminRequestDto;
import com.healthy.foodie.dto.MenuDetail;
import com.healthy.foodie.dto.ResponseDto;
import com.healthy.foodie.entity.Food;
import com.healthy.foodie.entity.Menu;
import com.healthy.foodie.entity.Vendor;
import com.healthy.foodie.repository.FoodRepository;
import com.healthy.foodie.repository.MenuRepository;
import com.healthy.foodie.repository.VendorRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private VendorRepository vendorRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private FoodRepository foodRepository;
	
	@Override
	public ResponseDto addMenu(AdminRequestDto adminRequestDto) {
		Optional<Vendor> vendor=vendorRepository.findByVendorName(adminRequestDto.getVendorName());
		Vendor vendordetail= new Vendor();
		if(!(vendor.isPresent())){
			vendordetail = new Vendor();
			vendordetail.setVendorName(adminRequestDto.getVendorName());
			vendordetail.setImage(adminRequestDto.getVendorImage());
			vendordetail=vendorRepository.save(vendordetail);
		}
		for (MenuDetail menudetail : adminRequestDto.getMenuDetails()) {
			Menu menu= new Menu();
			Food food= searchFood(menudetail.getMenuName());
			menu.setVendorId(vendordetail.getVendorId());
			menu.setFoodId(food.getFoodId());
			Double menuPrice=Double.parseDouble(menudetail.getMenuPrice());
			menu.setPrice(menuPrice);
			menuRepository.save(menu);
		}
		ResponseDto responseDto=new ResponseDto();
		responseDto.setMessage(ApplicationConstants.ADMIN_SUCCESSMESSAGE);
		responseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
		return responseDto ;
	}
	
	public Food searchFood(String foodName) {
		Optional<Food> food=foodRepository.findByFoodName(foodName);
		if(!(food.isPresent())) {
			Food newfood= new Food();
			newfood.setFoodName(foodName);
			return foodRepository.save(newfood);
		}
		return food.get();
	}

}
