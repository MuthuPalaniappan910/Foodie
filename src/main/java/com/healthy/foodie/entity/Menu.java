package com.healthy.foodie.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menuId;
	private Long vendorId;
	private Long foodId;
	private Double price;
}
