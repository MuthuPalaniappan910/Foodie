package com.healthy.foodie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthy.foodie.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findBymobileNumberAndPassword(Long mobileNumber, String password);
}
