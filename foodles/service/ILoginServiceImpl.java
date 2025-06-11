package com.cg.foodles.service;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.foodles.dao.ICustomerRepository;
import com.cg.foodles.dao.ILoginRepository;
import com.cg.foodles.dao.IRestaurantRepository;
import com.cg.foodles.entity.LoginBean;
import com.cg.foodles.exceptions.UserException;

@Service
public class ILoginServiceImpl implements ILoginService{
	@Autowired
	ILoginRepository loginDao;
	
	LoginBean log;
	
	//register
	public LoginBean registerUser(LoginBean login) {
		if(login!=null) {
			List<LoginBean> logList = loginDao.getUserByUname(login.getUserName());
			if(logList.isEmpty()) {
				log = loginDao.save(login);
				return log;
			}
		}
		return null;
	}
	
	//login
	public LoginBean loginUser(LoginBean login) {
		if(login!=null) {
			List<LoginBean> logList = loginDao.getUserByUname(login.getUserName());
			if(logList.isEmpty()) {
				return null;
			}
			if(logList.get(0).getPassword().equals(login.getPassword())) {
				return logList.get(0);
			}
		}
		return null;
	}
	
	
	public boolean removeUser(Integer id) {
		if(loginDao.existsById(id)) {
			loginDao.deleteById(id);
			return true;
		}
		return false;
	}
	
}