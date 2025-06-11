package com.cg.foodles.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.foodles.entity.RestaurantBean;

@Repository
public interface IRestaurantRepository extends JpaRepository<RestaurantBean, String>{
	
}
