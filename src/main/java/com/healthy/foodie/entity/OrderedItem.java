package com.healthy.foodie.entity;

import javax.persistence.Embeddable;

@Embeddable
public class OrderedItem {

	private Long menuId;
	private Integer quantity;
	private Double menuPrice;

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(Double menuPrice) {
		this.menuPrice = menuPrice;
	}

}
