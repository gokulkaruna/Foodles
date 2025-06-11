package com.cg.foodles.service;

import java.util.List;

import com.cg.foodles.entity.RestaurantBean;
import com.cg.foodles.exceptions.RestaurantException;

public interface IRestaurantService {
	public RestaurantBean addRestaurant(RestaurantBean res) throws RestaurantException;
	public RestaurantBean updateRestaurant(RestaurantBean res) throws RestaurantException;
	public RestaurantBean removeRestaurant(RestaurantBean res) throws RestaurantException;
	public RestaurantBean viewRestaurant(RestaurantBean res) throws RestaurantException;
	public List<RestaurantBean> viewNearByRestaurant(String location) throws RestaurantException;
	public List<RestaurantBean> viewRestaurantByItemName(String name)throws RestaurantException;

}
