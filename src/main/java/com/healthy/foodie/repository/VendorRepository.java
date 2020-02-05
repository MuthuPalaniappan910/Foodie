package com.healthy.foodie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthy.foodie.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>{
	
	Optional<Vendor> findByVendorName(String vendorName);

}
