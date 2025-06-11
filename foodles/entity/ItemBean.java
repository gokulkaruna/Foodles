package com.cg.foodles.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.engine.internal.CascadePoint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="item_tbl")
@SequenceGenerator(name = "item_seq",sequenceName = "item_seq",allocationSize = 1)
public class ItemBean {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "item_seq")
	@Column(name="itm_id")
	private Integer itemId;
	
	@Column(name="itmname",length=20)
	private String itemName;
	
	@Column(name="itmqty")
	private Integer quantity;
	
	@Column(name="itmcost",precision = 2)
	private Double cost;
	
	//image
	
	
	//Mapping
	@ManyToMany(mappedBy = "itmList")
	@JsonIgnoreProperties("itemList")
	private List<RestaurantBean> restaurants = new ArrayList<RestaurantBean>();

	@ManyToOne(cascade = CascadeType.ALL)
	private CategoryBean category;
	
	@ManyToOne(optional = true)
	@JsonIgnore
    private FoodCartBean foodCart;

//	public FoodCartBean getFoodCart() {
//		return foodCart;
//	}

	public void setFoodCart(FoodCartBean foodCart) {
		this.foodCart = foodCart;
	}

	public ItemBean() {
		super();
	}

	public ItemBean(Integer itemId, String itemName, Integer quantity, Double cost,
			CategoryBean category) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.cost = cost;
		this.category = category;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public List<RestaurantBean> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<RestaurantBean> restaurants) {
		this.restaurants = restaurants;
	}
	
	public CategoryBean getCategory() {
		return category;
	}

	public void setCategory(CategoryBean category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ItemBean [itemId=" + itemId + ", itemName=" + itemName + ", quantity=" + quantity + ", cost=" + cost
				+ "]";
	}

	
	
}
