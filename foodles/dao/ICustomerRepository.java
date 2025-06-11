package com.cg.foodles.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.foodles.entity.CustomerBean;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerBean, String>{
	
}
