package com.cg.foodles.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="bill_tbl")
public class BillBean {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="bill_generator",sequenceName = "bill_seq",allocationSize = 1 )
	@Column(name="billid",nullable = false,updatable = false)
	private Integer billId;
	
	@Column(name="billdate")
	private LocalDateTime billDate;
	
	@Column(name="totitem")
	private Integer totalItem;
	
	@Column(name="totcost",precision = 2)
	private Double totalCost;
	
	//Mapping
	@OneToOne
	private OrderDetailsBean order;  

	public BillBean() {
		super();
	}

	
	public BillBean(Integer billId, LocalDateTime billDate, Integer totalItem, Double totalCost,
			OrderDetailsBean order) {
		super();
		this.billId = billId;
		this.billDate = billDate;
		this.totalItem = totalItem;
		this.totalCost = totalCost;
		this.order = order;
	}


	public Integer getBillId() {
		return billId;
	}


	public void setBillId(Integer billId) {
		this.billId = billId;
	}


	public LocalDateTime getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDateTime billDate) {
		this.billDate = billDate;
	}

	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public OrderDetailsBean getOrder() {
		return order;
	}

	public void setOrder(OrderDetailsBean order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "BillBean [billId=" + billId + ", billDate=" + billDate + ", totalItem=" + totalItem + ", totalCost="
				+ totalCost + "]";
	}
	
	
	
}