package com.cg.foodles.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.foodles.entity.BillBean;

@Repository
public interface IBillRepository extends JpaRepository<BillBean, Integer>{


}
