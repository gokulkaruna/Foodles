package com.cg.foodles.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.foodles.dao.ICategoryRepository;
import com.cg.foodles.entity.CategoryBean;
import com.cg.foodles.exceptions.CategoryException;

@Service
public class ICategoryServiceImpl implements ICategoryService{
	@Autowired
	ICategoryRepository categoryDao;
	CategoryBean category;

	@Override
	public CategoryBean addCategory(CategoryBean cat) throws CategoryException {
		if(categoryDao.existsById(cat.getCatId())) {
			throw new CategoryException("Category Already exists");
		}
		return categoryDao.save(cat);
	}

	@Override
	public CategoryBean updateCategory(CategoryBean cat) throws CategoryException {
		if(categoryDao.existsById(cat.getCatId())) {
			return categoryDao.save(cat);
		}
		throw new CategoryException("Category does not exist");
	}

	@Override
	public CategoryBean removeCategory(CategoryBean cat) throws CategoryException {
		if(cat!=null) {
			if(categoryDao.existsById(cat.getCatId())) {
				category =  categoryDao.findById(cat.getCatId()).get();
				categoryDao.deleteById(cat.getCatId());
				return category;
			}
		}
		throw new CategoryException("Category does not exist");
	}

	@Override
	public CategoryBean viewCategory(CategoryBean cat) throws CategoryException {
		if(cat!=null) {
			if(categoryDao.existsById(cat.getCatId())) {
				category =  categoryDao.findById(cat.getCatId()).get();
				return category;
			}
		}
		throw new CategoryException("Category does not exist");
	}

	@Override
	public List<CategoryBean> viewAllCategory() throws CategoryException {
		List<CategoryBean> catList = categoryDao.findAll();
		if(catList.size()>0)
			return catList;
		throw new CategoryException("Category not found");
	}
	
	public CategoryBean isCategory(Integer catId) {
		if(categoryDao.existsById(catId)) {
			category = categoryDao.findById(catId).get();
			if(category!=null) {
				return category;
			}
		}
		return null;
	}
	
	public CategoryBean isCategoryByName(String catName) {
		List<CategoryBean> catList = categoryDao.findAll();
		for(CategoryBean cat: catList) {
			if(cat.getCategoryName().equalsIgnoreCase(catName)) {
				return cat;
			}
		}
		return null;
	}
}
