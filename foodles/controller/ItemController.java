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
import com.cg.foodles.entity.ItemBean;
import com.cg.foodles.entity.RestaurantBean;
import com.cg.foodles.exceptions.ItemException;
import com.cg.foodles.exceptions.RestaurantException;
import com.cg.foodles.service.ICategoryServiceImpl;
import com.cg.foodles.service.IItemServiceImpl;
import com.cg.foodles.service.IRestaurantServiceImpl;

@CrossOrigin("*")
@RequestMapping("/item")
@RestController
public class ItemController {

	@Autowired
	IItemServiceImpl itemService;
	@Autowired
	IRestaurantServiceImpl restaurantService;
	@Autowired
	ICategoryServiceImpl categoryService;
	ItemBean itm;
	
	
	@PostMapping("/add")
	public ItemBean addItem(@RequestBody ItemBean item) throws ItemException {
		itm = itemService.addItem(item);
		return itm;
	}

	@GetMapping("/view/{itmid}")
	public ItemBean viewItem(@PathVariable("itmid") Integer itmId) throws ItemException {
		itm = itemService.isItem(itmId);
		itm = itemService.viewItem(itm);
		return itm;	
	}

	@PutMapping("/update")
	public ItemBean updateItem(@RequestBody ItemBean item) throws ItemException {
		itm = itemService.updateItem(item);
		return itm;
		
	}

	@DeleteMapping("/remove/{itmid}/{restId}")
	public ItemBean removeItem(@PathVariable("itmid") Integer itmId,@PathVariable("restId") String restId) throws ItemException, RestaurantException {
		itm = itemService.isItem(itmId);
		RestaurantBean rest =  restaurantService.isRestaurantBean(restId);
		if(itm.getRestaurants().size()==1) {
			if(rest.getItemList().contains(itm)){
				rest.getItemList().remove(itm);
				restaurantService.updateRestaurant(rest);
			}
		}
		itm = itemService.removeItem(itm);
		return itm;
	}

	@GetMapping("/viewall/rest/{restid}")
	public List<ItemBean> viewAllItems(@PathVariable("restid") String restId) throws RestaurantException {
		RestaurantBean re = restaurantService.isRestaurantBean(restId);
		return itemService.viewAllItems(re);
		
	}

	@GetMapping("/viewall/category/{category}")
	//category obj with name
	public List<ItemBean> viewAllItems1(@PathVariable("category") String catName) throws ItemException {
		CategoryBean cat = categoryService.isCategoryByName(catName);
		return itemService.viewAllItems(cat);
	}

	@GetMapping("/viewall/name/{itmname}")
	public List<ItemBean> viewAllItemsByName(@PathVariable("itmname") String itmName) throws ItemException {
		List<ItemBean> itm = itemService.viewAllItemsByName(itmName);
		return itm;
	}
	
	//get all items
	@GetMapping("/getall")
	public List<ItemBean> getAllItems() throws ItemException {
		return itemService.viewAllItems();		
	}
	
	
	
}