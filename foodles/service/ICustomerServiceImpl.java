package com.cg.foodles.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.foodles.dao.IAddressRepository;
import com.cg.foodles.dao.ICartRepository;
import com.cg.foodles.dao.ICustomerRepository;
import com.cg.foodles.entity.CustomerBean;
import com.cg.foodles.entity.FoodCartBean;
import com.cg.foodles.entity.ItemBean;
import com.cg.foodles.exceptions.CustomerException;

@Service
public class ICustomerServiceImpl implements ICustomerService{
	@Autowired
	ICustomerRepository customerDao;
	@Autowired
	ICartRepository cartDao;
	@Autowired 
	IAddressRepository addressDao;
	
	CustomerBean cust;
	

	
	@Override
	public CustomerBean addCustomer(CustomerBean customer) {
		if(customerDao.existsById(customer.getCustomerId())) {
			return null;
		}
		List<ItemBean> itemList = new ArrayList<ItemBean>();
		FoodCartBean cart = new FoodCartBean(customer.getCustomerId(),customer,itemList);
		customerDao.save(customer);
		cart.setCustomer(customer);
		cartDao.save(cart);
		return customer;
	}

	@Override
	public CustomerBean updateCustomer(CustomerBean customer) throws CustomerException {
		if(customerDao.existsById(customer.getCustomerId())) {
			return customerDao.save(customer);
		}
		return null;
	}

	@Override
	public CustomerBean removeCustomer(CustomerBean customer) throws CustomerException {
		if(customer!=null) {
			if(customerDao.existsById(customer.getCustomerId())) {
				cust = customerDao.findById(customer.getCustomerId()).get();
				customerDao.deleteById(customer.getCustomerId());
				return cust;
			}
		}
		throw new CustomerException("Customer does not exist with that Id");
	}


	@Override
	public CustomerBean viewCustomer(CustomerBean customer) throws CustomerException {
		if(customer!=null) {
			if(customerDao.existsById(customer.getCustomerId())) {
				cust =  customerDao.findById(customer.getCustomerId()).get();
				return cust;
			}
		}
		return null;
	}

	@Override
	public List<CustomerBean> viewAllCustomers() throws CustomerException {
		List<CustomerBean> custList = customerDao.findAll();
		if(custList.size()>0)
			return custList;
		throw new CustomerException("No Customers Found");
	}
	
	public CustomerBean isCustomer(String cid) {
		if(customerDao.existsById(cid)) {
			cust = customerDao.findById(cid).get();
			if(cust!=null) {
				return cust;
			}
		}
		return null;
	}

}
