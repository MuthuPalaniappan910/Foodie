package com.healthy.foodie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthy.foodie.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

	Food findByFoodId(Long foodId);

}
