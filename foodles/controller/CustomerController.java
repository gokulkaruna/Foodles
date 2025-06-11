package com.cg.foodles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.foodles.entity.CustomerBean;
import com.cg.foodles.entity.LoginBean;
import com.cg.foodles.exceptions.CustomerException;
import com.cg.foodles.service.ICustomerServiceImpl;


@CrossOrigin("*")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	ICustomerServiceImpl customerService;
	CustomerBean cust;
	
	@PostMapping("/add")
	public ResponseEntity<CustomerBean> addCustomer(@RequestBody CustomerBean customer) {
		cust = customerService.addCustomer(customer);
		if(cust!=null) 
			return new ResponseEntity<CustomerBean>(cust,HttpStatus.OK);
		return new ResponseEntity<CustomerBean>(cust,HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/update")
	public ResponseEntity<CustomerBean> updateCustomer(@RequestBody CustomerBean customer) throws CustomerException{
		cust = customerService.updateCustomer(customer);
		if(cust!=null) 
			return new ResponseEntity<CustomerBean>(cust,HttpStatus.OK);
		return new ResponseEntity<CustomerBean>(cust,HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/view/{cid}")
	public ResponseEntity<CustomerBean> viewCustomer(@PathVariable("cid") String cId) throws CustomerException{
		cust = customerService.isCustomer(cId);
		customerService.viewCustomer(cust);
		if(cust!=null) 
			return new ResponseEntity<CustomerBean>(cust,HttpStatus.OK);
		return new ResponseEntity<CustomerBean>(cust,HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/remove/{cid}")
	public CustomerBean removeCustomer(@PathVariable("cid") String cId) throws CustomerException {
		cust = customerService.isCustomer(cId);
		cust =  customerService.removeCustomer(cust);
		return cust;
	}
	
	@GetMapping("/viewall")
	public List<CustomerBean> viewAllCustomers() throws CustomerException{
		List<CustomerBean> custList = customerService.viewAllCustomers();
		return custList;
		
	}
	
}
