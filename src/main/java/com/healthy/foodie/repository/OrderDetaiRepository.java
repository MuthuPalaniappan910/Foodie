package com.healthy.foodie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthy.foodie.entity.OrderDetail;

@Repository
public interface OrderDetaiRepository extends JpaRepository<OrderDetail, Long> {
	List<OrderDetail> findByCustomerId(Long customerId);
}
