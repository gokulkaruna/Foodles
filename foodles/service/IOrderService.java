package com.cg.foodles.service;

import java.util.List;

import com.cg.foodles.entity.CustomerBean;
import com.cg.foodles.entity.OrderDetailsBean;
import com.cg.foodles.entity.RestaurantBean;
import com.cg.foodles.exceptions.CartException;
import com.cg.foodles.exceptions.CustomerException;
import com.cg.foodles.exceptions.OrderException;

public interface IOrderService {
	public OrderDetailsBean addOrder(OrderDetailsBean order) throws OrderException, CartException;
	public OrderDetailsBean updateOrder(OrderDetailsBean order) throws OrderException;
	public OrderDetailsBean removeOrder(OrderDetailsBean order) throws OrderException;
	public OrderDetailsBean viewOrder(OrderDetailsBean order) throws OrderException;
	public List<OrderDetailsBean> viewAllOrders(RestaurantBean res) throws OrderException;
	public List<OrderDetailsBean> viewAllOrders(CustomerBean customer) throws OrderException, CustomerException;

}
