package com.cg.foodles.service;

import java.util.List;

import com.cg.foodles.entity.CategoryBean;
import com.cg.foodles.exceptions.CategoryException;

public interface ICategoryService {
	public CategoryBean addCategory(CategoryBean cat) throws CategoryException;
	public CategoryBean updateCategory(CategoryBean cat) throws CategoryException;
	public CategoryBean removeCategory(CategoryBean cat) throws CategoryException;
	public CategoryBean viewCategory(CategoryBean cat) throws CategoryException;
	public List<CategoryBean> viewAllCategory() throws CategoryException;  

}
