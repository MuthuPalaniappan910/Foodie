package com.healthy.foodie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthy.foodie.entity.Menu;
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{

}
