package com.healthy.foodie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthy.foodie.entity.OrderDetail;

@Repository
public interface OrderDetaiRepository extends JpaRepository<OrderDetail, Long> {

}
