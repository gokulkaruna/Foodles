package com.cg.foodles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.cg.foodles.entity.RestaurantBean;
import com.cg.foodles.exceptions.RestaurantException;
import com.cg.foodles.service.IRestaurantServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/restaurant")
public class RestaurantController {
	@Autowired
	IRestaurantServiceImpl restaurantService;
	
	RestaurantBean restaurant;
	
	@PostMapping("/add")
	public ResponseEntity<RestaurantBean> addRestaurant(@RequestBody RestaurantBean res) throws RestaurantException{
		restaurant = restaurantService.addRestaurant(res);
		if(restaurant!=null) 
			return new ResponseEntity<RestaurantBean>(restaurant,HttpStatus.OK);
		return new ResponseEntity<RestaurantBean>(restaurant,HttpStatus.NOT_FOUND);	
	}
	
	@PutMapping("/update")
	public ResponseEntity<RestaurantBean> updateRestaurant(@RequestBody RestaurantBean res) throws RestaurantException{
		restaurant =restaurantService.updateRestaurant(res);
		if(restaurant!=null) 
			return new ResponseEntity<RestaurantBean>(restaurant,HttpStatus.OK);
		return new ResponseEntity<RestaurantBean>(restaurant,HttpStatus.NOT_FOUND);	
	}
	
	@GetMapping("/view/{restaurantId}")
	public ResponseEntity<RestaurantBean> viewRestaurant(@PathVariable("restaurantId") String restaurantId) throws RestaurantException{
		restaurant = restaurantService.isRestaurantBean(restaurantId);
		restaurant = restaurantService.viewRestaurant(restaurant);
		if(restaurant!=null) 
			return new ResponseEntity<RestaurantBean>(restaurant,HttpStatus.OK);
		return new ResponseEntity<RestaurantBean>(restaurant,HttpStatus.NOT_FOUND);	
	}
	
	@DeleteMapping("/remove/{restaurantId}")
	public RestaurantBean removeRestaurant(@PathVariable ("restaurantId") String restaurantId) throws RestaurantException{
		restaurant = restaurantService.isRestaurantBean(restaurantId);
		restaurant = restaurantService.removeRestaurant(restaurant);
		return restaurant;
	}
	

	
	@GetMapping("/viewbyitem/{itemname}")
	public List<RestaurantBean> viewRestaurantByItemName(@PathVariable("itemname") String itemName) throws RestaurantException{
		List<RestaurantBean> filterdList = restaurantService.viewRestaurantByItemName(itemName);
		return filterdList;
	}
	
	@GetMapping("/viewbylocation/{location}")
	public List<RestaurantBean> viewRestaurantByLocation(@PathVariable("location") String location) throws RestaurantException{
		List<RestaurantBean> filterdList = restaurantService.viewNearByRestaurant(location);
		return filterdList;
	}
	
}


