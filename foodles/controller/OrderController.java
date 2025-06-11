package com.cg.foodles.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.foodles.entity.CustomerBean;
import com.cg.foodles.entity.FoodCartBean;
import com.cg.foodles.entity.OrderDetailsBean;
import com.cg.foodles.entity.RestaurantBean;
import com.cg.foodles.exceptions.CartException;
import com.cg.foodles.exceptions.CustomerException;
import com.cg.foodles.exceptions.OrderException;
import com.cg.foodles.service.ICartServiceImpl;
import com.cg.foodles.service.ICustomerServiceImpl;
import com.cg.foodles.service.IOrderServiceImpl;
import com.cg.foodles.service.IRestaurantServiceImpl;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {
	@Autowired
	IOrderServiceImpl orderService;
	@Autowired
	IRestaurantServiceImpl restaurantService;
	@Autowired
	ICustomerServiceImpl customerService;
	@Autowired
	ICartServiceImpl cartService;

	OrderDetailsBean OrderDetails;
	

	@PostMapping("/add/{cartid}")
	public OrderDetailsBean addOrder(@PathVariable("cartid") String cartId) throws OrderException, CartException {
		FoodCartBean cart = cartService.isCart(cartId);
		OrderDetailsBean newOrder = new OrderDetailsBean();
		if(cart!=null) {
			newOrder.setCart(cart); //set cart
			newOrder.setOrderStatus("ORDER PENDING");  //set order status
		    java.util.Date utilDate = new java.util.Date();
		    LocalDate date = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    newOrder.setOrderDate(date);  //set todays date as order date 
		}
		return orderService.addOrder(newOrder);
	}  
	
	@PutMapping("/update")
	public OrderDetailsBean updateOrder(@RequestBody OrderDetailsBean order) throws OrderException{
		return orderService.updateOrder(order);
	}
	
	@DeleteMapping("/remove/{orderid}")
	public OrderDetailsBean removeOrder(@PathVariable("orderid") Integer ordId) throws OrderException{
		OrderDetails = orderService.isOrder(ordId);
		return orderService.removeOrder(OrderDetails);
	}
	
	
	@GetMapping("/view/{orderid}")
	public OrderDetailsBean viewOrder(@PathVariable("orderid") Integer ordId) throws OrderException {
		OrderDetails = orderService.isOrder(ordId);
		return orderService.viewOrder(OrderDetails);
	}
	
	@GetMapping("/viewallorders/restaurant/{restid}")
	public List<OrderDetailsBean> viewAllOrders(@PathVariable("restid") String restId) throws OrderException{
		RestaurantBean rest = restaurantService.isRestaurantBean(restId);
		return orderService.viewAllOrders(rest);
	
	}
	@GetMapping("/viewallorders/customer/{custid}")
	public List<OrderDetailsBean> viewAllOrders1(@PathVariable("custid") String custId) throws OrderException, CustomerException{
		CustomerBean cust = customerService.isCustomer(custId);
		return orderService.viewAllOrders(cust);
	
	}
} 
