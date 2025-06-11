package com.cg.foodles.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.foodles.entity.CategoryBean;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryBean, Integer>{

}
