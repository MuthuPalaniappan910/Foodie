package com.healthy.foodie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthy.foodie.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
