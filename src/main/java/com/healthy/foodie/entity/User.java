package com.healthy.foodie.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.healthy.foodie.util.FoodieEnum.Role;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String userName;
	private String password;
	private Long mobileNumber;
	@Enumerated(EnumType.STRING)
	private Role role;
}
