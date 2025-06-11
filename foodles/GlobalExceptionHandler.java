package com.cg.foodles;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cg.foodles.exceptions.BillException;
import com.cg.foodles.exceptions.CartException;
import com.cg.foodles.exceptions.CategoryException;
import com.cg.foodles.exceptions.CustomerException;
import com.cg.foodles.exceptions.ErrorInfo;
import com.cg.foodles.exceptions.ItemException;
import com.cg.foodles.exceptions.OrderException;
import com.cg.foodles.exceptions.RestaurantException;
import com.cg.foodles.exceptions.UserException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerException.class)
	public @ResponseBody ErrorInfo customerExceptionInfo(CustomerException e,HttpServletRequest req) {
		 ErrorInfo erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;	
	}
	
	@ExceptionHandler(UserException.class)
	public @ResponseBody ErrorInfo userExceptionInfo(UserException e,HttpServletRequest req) {
		 ErrorInfo erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;	
	}
	
	@ExceptionHandler(RestaurantException.class)
	public @ResponseBody ErrorInfo restaurantExceptionInfo(RestaurantException e,HttpServletRequest req) {
		 ErrorInfo erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;	
	}
	
	@ExceptionHandler(CartException.class)
	public @ResponseBody ErrorInfo cartExceptionInfo(CartException e,HttpServletRequest req) {
		 ErrorInfo erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;	
	
	}
	
	@ExceptionHandler(CategoryException.class)
	public @ResponseBody ErrorInfo categoryExceptionInfo(CategoryException e,HttpServletRequest req) {
		 ErrorInfo erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;	
	
	}

	
	@ExceptionHandler(ItemException.class)
	public @ResponseBody ErrorInfo itemExceptionInfo(ItemException e,HttpServletRequest req) {
		 ErrorInfo erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;	
	
	}
	
	@ExceptionHandler(OrderException.class)
	public @ResponseBody ErrorInfo orderExceptionInfo(OrderException e,HttpServletRequest req) {
		 ErrorInfo erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;	
	
	}
	
	@ExceptionHandler(BillException.class)
	public @ResponseBody ErrorInfo billExceptionInfo(BillException e,HttpServletRequest req) {
		 ErrorInfo erinfo = new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		 return erinfo;	
	
	}
	
}