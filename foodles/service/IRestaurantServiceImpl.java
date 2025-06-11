package com.cg.foodles.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.foodles.dao.IRestaurantRepository;
import com.cg.foodles.entity.ItemBean;
import com.cg.foodles.entity.RestaurantBean;
import com.cg.foodles.exceptions.RestaurantException;

@Service
public class IRestaurantServiceImpl implements IRestaurantService{
	@Autowired
	IRestaurantRepository restaurantDao;
	
	RestaurantBean restaurant;

	@Override
	public RestaurantBean addRestaurant(RestaurantBean res) throws RestaurantException {
		if(restaurantDao.existsById(res.getRestaurantId())) {
			return null;
		}
		return restaurantDao.save(res);
	}
	

	@Override
	public RestaurantBean updateRestaurant(RestaurantBean res) throws RestaurantException {
		if(restaurantDao.existsById(res.getRestaurantId())) {
			return restaurantDao.save(res);
		}
		return null;
	}

	@Override
	public RestaurantBean viewRestaurant(RestaurantBean res) throws RestaurantException {
		if(res!=null) {
			return restaurantDao.findById(res.getRestaurantId()).get();
		}
		return null;
	}

	
	@Override
	public RestaurantBean removeRestaurant(RestaurantBean res) throws RestaurantException {
		if(res!=null){
			if(restaurantDao.existsById(res.getRestaurantId())) {
				restaurant = restaurantDao.findById(res.getRestaurantId()).get();
				restaurantDao.deleteById(res.getRestaurantId());
				return restaurant;
			}
		}
		throw new RestaurantException("Restaurant with that id does not exist");
	}

	
	@Override
	public List<RestaurantBean> viewNearByRestaurant(String location) throws RestaurantException {
		List<RestaurantBean> restList = restaurantDao.findAll();
		List<RestaurantBean> filterdList = new ArrayList<RestaurantBean>();
		for(RestaurantBean res : restList) {
				if(res.getAddress().getCity().toLowerCase().equals(location.toLowerCase())) {
					filterdList.add(res);
				}
		}
		if(filterdList.size()>0)
			return filterdList;
		else
			throw new RestaurantException("No Restaurants at "+location);	 
	}

	@Override
	public List<RestaurantBean> viewRestaurantByItemName(String name) throws RestaurantException {
		List<RestaurantBean> restList = restaurantDao.findAll();
		List<RestaurantBean> filterdList = new ArrayList<RestaurantBean>();
		for(RestaurantBean res : restList) {
			for(ItemBean itm :res.getItemList()) {
				if(itm.getItemName().equalsIgnoreCase(name)) {
					filterdList.add(res);
					break;
				}
			}
		}
		if(filterdList.size()>0)
			return filterdList;
		else
			throw new RestaurantException(name+" not found in any Restaurant");	 
	}
	
	public RestaurantBean isRestaurantBean(String restId){
		if(restaurantDao.existsById(restId)) {	
			restaurant = restaurantDao.findById(restId).get();
			if(restaurant!=null)
				return restaurant;
		}
		return null;
	   }

}
