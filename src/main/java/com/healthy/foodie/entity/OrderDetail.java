package com.healthy.foodie.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	private Long customerId;
	private String orderStatus;
	private LocalDate orderPlacedDate;
	private Double totalPrice;

	@ElementCollection
	private List<OrderedItem> items = new ArrayList<>();


	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDate getOrderPlacedDate() {
		return orderPlacedDate;
	}

	public void setOrderPlacedDate(LocalDate orderPlacedDate) {
		this.orderPlacedDate = orderPlacedDate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderedItem> getItems() {
		return items;
	}

	public void setItems(List<OrderedItem> items) {
		this.items = items;
	}
}
