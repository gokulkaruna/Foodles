package com.cg.foodles.service;

import java.util.List;
import com.cg.foodles.entity.CustomerBean;
import com.cg.foodles.exceptions.CustomerException;

public interface ICustomerService {
	public CustomerBean addCustomer(CustomerBean customer) throws CustomerException;
	public CustomerBean updateCustomer(CustomerBean customer) throws CustomerException;
	public CustomerBean removeCustomer(CustomerBean customer) throws CustomerException;
	public CustomerBean viewCustomer(CustomerBean customer) throws CustomerException;
	public List<CustomerBean> viewAllCustomers() throws CustomerException;
	
}
