package com.healthy.foodie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthy.foodie.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
	Optional<Food> findByFoodName(String foodName);
	Food findByFoodId(Long foodId);
}
