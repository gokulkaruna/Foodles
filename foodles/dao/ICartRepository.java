package com.cg.foodles.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.foodles.entity.FoodCartBean;

@Repository
public interface ICartRepository extends JpaRepository<FoodCartBean, String> {

}
