package com.cg.foodles.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cg.foodles.entity.OrderDetailsBean;

@Repository
public interface IOrderRepository extends JpaRepository<OrderDetailsBean,Integer>{
//	@Query("select o from OrderDetaisBean o where o.cart.cartId = (select c.cartId from FoodCartBean c where c.customer.customerId = :custId)")
//	public List<OrderDetailsBean> getOrders(@Param("custId") String custId);
}
