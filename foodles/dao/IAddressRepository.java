package com.cg.foodles.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.foodles.entity.AddressBean;
@Repository
public interface IAddressRepository extends JpaRepository<AddressBean,String>{

}
