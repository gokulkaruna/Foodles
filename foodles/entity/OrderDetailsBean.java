package com.cg.foodles.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ord_tbl")
public class OrderDetailsBean {
	//make auto generated
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="order_generator",sequenceName = "order_seq",allocationSize = 1 )
	@Column(name="ordid",nullable = false,updatable = false)
	private Integer orderId;
	
	@Column(name="ordstatus",length=20)
	private String orderStatus;
	
	@Column(name="orddate")
	private  LocalDate orderDate;
	
//	//Mymapping
//	@OneToOne
//	@JsonIgnore
//	private BillBean bill;
	
	//Mapping
	@OneToOne
	private FoodCartBean cart;
	
	public OrderDetailsBean() {
		super();
	}
	

	public OrderDetailsBean(Integer orderId, String orderstatus, LocalDate orderDate, FoodCartBean cart) {
        super();
        this.orderId = orderId;
        this.orderStatus = orderstatus;
        this.orderDate = orderDate;
        this.cart = cart;
    }
	
	
    public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

//	public BillBean getBill() {
//		return bill;
//	}
//
//	public void setBill(BillBean bill) {
//		this.bill = bill;
//	}

	public Integer getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Integer orderId) {
    	this.orderId = orderId;
    }
    
   
    public LocalDate getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(LocalDate orderDate) {
    	this.orderDate = orderDate;
    }
    
    public FoodCartBean getCart() {
        return cart;
    }
    
    public void setCart(FoodCartBean cart) {
        this.cart = cart;
    }
    
    @Override
    public String toString() {
        return "OrderDetailsBean [OrderId=" + orderId + ", Orderstatus=" + orderStatus + ", OrderDate=" + orderDate
                + ", cart=" + cart + "]";
    }

}
