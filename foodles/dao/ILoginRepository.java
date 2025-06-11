package com.cg.foodles.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.foodles.entity.LoginBean;

@Repository
public interface ILoginRepository extends JpaRepository<LoginBean, Integer>{
	
	@Query("select u from LoginBean u where u.userName = :userName")
	public List<LoginBean> getUserByUname(@Param("userName") String userName);

}
