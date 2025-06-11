package com.cg.foodles.service;


import com.cg.foodles.entity.LoginBean;
import com.cg.foodles.exceptions.UserException;

public interface ILoginService {
	public LoginBean registerUser(LoginBean login);
	public LoginBean loginUser(LoginBean login);
}
