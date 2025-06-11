package com.cg.foodles.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.foodles.dao.ICartRepository;
import com.cg.foodles.dao.IOrderRepository;
import com.cg.foodles.dao.IRestaurantRepository;
import com.cg.foodles.entity.CustomerBean;
import com.cg.foodles.entity.FoodCartBean;
import com.cg.foodles.entity.ItemBean;
import com.cg.foodles.entity.OrderDetailsBean;
import com.cg.foodles.entity.RestaurantBean;
import com.cg.foodles.exceptions.CartException;
import com.cg.foodles.exceptions.CustomerException;
import com.cg.foodles.exceptions.OrderException;

@Service
public class IOrderServiceImpl implements IOrderService {
	@Autowired
	IOrderRepository orderDao;
	
	@Autowired
	IRestaurantRepository restaurantDao;

	@Autowired
	ICartRepository cartDao;
	

	OrderDetailsBean ord;

	@Override
	public OrderDetailsBean addOrder(OrderDetailsBean order) throws OrderException, CartException {
		if(order!=null) {
			if(isOrder(order.getOrderId())==null){
				if(order.getCart()!=null) {	
					return orderDao.save(order);	
				}
			}throw new CartException("order already exists with id "+order.getOrderId());
		}
		throw new CartException("Check your cart Id");
	

	}

	@Override
	public OrderDetailsBean updateOrder(OrderDetailsBean order) throws OrderException {
		if(order!=null) {
			if (orderDao.existsById(order.getOrderId())) {
				return orderDao.save(order);
			}
			throw new OrderException("Order with id "+order.getOrderId()+" does not exist");
		}
		throw new OrderException("Order does not exist");
	}

	@Override
	public OrderDetailsBean removeOrder(OrderDetailsBean order) throws OrderException {
		if (order != null) {
			if (orderDao.existsById(order.getOrderId())) {
				ord = orderDao.findById(order.getOrderId()).get();
				orderDao.deleteById(ord.getOrderId());
				return ord;
			}
			throw new OrderException("Order with id "+order.getOrderId()+" does not exist");
		}
		throw new OrderException("Order does not exist");
		
	}

	@Override
	public OrderDetailsBean viewOrder(OrderDetailsBean order) throws OrderException {
		if (order != null) {
			if (orderDao.existsById(order.getOrderId())) {
				return orderDao.findById(order.getOrderId()).get();
			}
			throw new OrderException("Order with id "+order.getOrderId()+" does not exist");
		}
		throw new OrderException("Order does not exist");
	}

	@Override
	public List<OrderDetailsBean> viewAllOrders(RestaurantBean rest) throws OrderException {
		if(rest!=null) {
			List<OrderDetailsBean> allOrders = orderDao.findAll();
			List<OrderDetailsBean> orderRest = new ArrayList<OrderDetailsBean>();
			for(OrderDetailsBean order:allOrders) {
				Map<ItemBean,Integer> cart = order.getCart().getItem_cartQty();
				Iterator<ItemBean> itr = cart.keySet().iterator();
				if(itr.hasNext()) {
					ItemBean cartItem = itr.next();
					if(cartItem.getRestaurants().get(0).getRestaurantId().equalsIgnoreCase(rest.getRestaurantId())){
						orderRest.add(order);
					}
				}	
			}
			
			if(orderRest.size()>0) {
				return orderRest;
			}
		}
		
		throw new OrderException("Order with  does not exist");
	}

	@Override
	public List<OrderDetailsBean> viewAllOrders(CustomerBean customer) throws OrderException, CustomerException {
//		if(customer!=null) {
//			List<OrderDetailsBean> finalOrdList = orderDao.getOrders(customer.getCustomerId());
//			if(finalOrdList.size()>0) {
//				return finalOrdList;
//			}
//			throw new CustomerException("Customer with Id "+customer.getCustomerId()+" not found");
//		}
//		throw new OrderException("Order does not exist for this customer");
		return null;

	}

	public OrderDetailsBean isOrder(Integer ordId) {
		if(ordId!=null)	{
			if(orderDao.existsById(ordId)) {
				ord = orderDao.findById(ordId).get();
				if (ord != null) {
					return ord;
				}
			}
		}
		return null;
	}

}
