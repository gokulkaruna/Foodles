package com.cg.foodles.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.foodles.entity.LoginBean;

import com.cg.foodles.service.ILoginServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class LoginController {
	
	@Autowired
	ILoginServiceImpl loginService;
	
	LoginBean log;
	

	
	@PostMapping("/login")
	public ResponseEntity<LoginBean> loginUser(@RequestBody LoginBean login){
		log = loginService.loginUser(login);
		if(log!=null) 
			return new ResponseEntity<LoginBean>(log,HttpStatus.OK);
		return new ResponseEntity<LoginBean>(log,HttpStatus.NOT_FOUND);
	}

	
	@PostMapping("/register")
	public ResponseEntity<LoginBean> registerUser(@RequestBody LoginBean login){
		log = loginService.registerUser(login);
		if(log!=null) 
			return new ResponseEntity<LoginBean>(log,HttpStatus.OK);
		return new ResponseEntity<LoginBean>(log,HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/remove/{userId}")
	public String removeUser(@PathVariable("userId") Integer userId){
		boolean flag = loginService.removeUser(userId);
		if(flag!=false) 
			return "User Delete Successfully";
		return "User Deletion Failed";
	}
	

}