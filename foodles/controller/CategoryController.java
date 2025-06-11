package com.cg.foodles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.foodles.entity.CategoryBean;
import com.cg.foodles.exceptions.CategoryException;
import com.cg.foodles.service.ICategoryServiceImpl;



@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	ICategoryServiceImpl categoryService;
	CategoryBean category;
	
	@PostMapping("/add")
	public CategoryBean addCategory(@RequestBody CategoryBean cat) throws CategoryException{
		category = categoryService.addCategory(cat);
		return category;
	}
	
	
	@PutMapping("/update")
	public CategoryBean updateCategory(@RequestBody CategoryBean cat) throws CategoryException{
		category = categoryService.updateCategory(cat);
		return category;
	}
	
	
	@DeleteMapping("/remove/{catId}")
	public CategoryBean removeCategory(@PathVariable ("catId") Integer catId) throws CategoryException{
		category = categoryService.isCategory(catId);
		category =  categoryService.removeCategory(category);
		return category;
	}
	
	
	@GetMapping("/view/{catId}")
	public CategoryBean viewCategory(@PathVariable("catId") Integer catId) throws CategoryException{
		category = categoryService.isCategory(catId);
		categoryService.viewCategory(category);
		return category;	
	}
	
	@GetMapping("/viewall")
	public List<CategoryBean> viewAllCategory() throws CategoryException
	{
		List<CategoryBean> catList = categoryService.viewAllCategory();
		return catList;	
	}
	
	

}
