package com.healthy.foodie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthy.foodie.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>{

}