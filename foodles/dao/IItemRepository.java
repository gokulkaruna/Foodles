package com.cg.foodles.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.foodles.entity.ItemBean;

@Repository
public interface IItemRepository extends JpaRepository<ItemBean, Integer> {
	

}
