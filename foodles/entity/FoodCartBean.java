package com.cg.foodles.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="fcart_tbl")
public class FoodCartBean {
	@Id
	@Column(name="crtid",length=20)
	private String cartId;
	//	Mapping
	@OneToOne
	private CustomerBean customer;
	
	@OneToMany(mappedBy = "foodCart")
	@JsonIgnore
	private List<ItemBean> itemList = new ArrayList<ItemBean>();
	
	@ElementCollection
	@MapKeyColumn(name = "itemId")
	@JoinColumn(name="item_cart_qty")
	Map<ItemBean,Integer> item_cartQty = new HashMap<ItemBean,Integer>();
	
	
	
	public FoodCartBean() {
		super();
	}

	public FoodCartBean(String cartId, CustomerBean customer, List<ItemBean> itemList) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.itemList = itemList;
	}

	public void addItem(ItemBean item) {
//		item.setFoodCart(this);
//		itemList.add(item);
		item_cartQty.put(item, 1);
	}
	
	
	
	public void removeItem(ItemBean item) {
		if(itemList.remove(item));
			System.out.println("hi");
			if(item_cartQty.containsKey(item)) {
				System.out.println("hi1");
				item_cartQty.remove(item);
			}
	}
	


	public List<ItemBean> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemBean> itemList) {
		this.itemList = itemList;
	}

	public Map<ItemBean, Integer> getItem_cartQty() {
		return item_cartQty;
	}

	public void setItem_cartQty(Map<ItemBean, Integer> item_cartQty) {
		this.item_cartQty = item_cartQty;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}


	@Override
	public String toString() {
		return "FoodCartBean [cartId=" + cartId + ", Customer=" + customer + ", itemList_qty=" + item_cartQty + "]";
	}

	
}
