package com.healthy.foodie.dto;

import java.time.LocalDate;

public class OrderDetails {

	private Long orderId;
	private LocalDate orderPlacedDate;
	private Double totalPrice;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	
}
