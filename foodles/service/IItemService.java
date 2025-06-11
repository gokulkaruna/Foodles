package com.cg.foodles.service;

import java.util.List;

import com.cg.foodles.entity.CategoryBean;
import com.cg.foodles.entity.ItemBean;
import com.cg.foodles.entity.RestaurantBean;
import com.cg.foodles.exceptions.ItemException;
import com.cg.foodles.exceptions.RestaurantException;

public interface IItemService {

	public ItemBean addItem(ItemBean item) throws ItemException;

	public ItemBean updateItem(ItemBean item) throws ItemException;

	public ItemBean removeItem(ItemBean item) throws ItemException;

	public ItemBean viewItem(ItemBean item) throws ItemException;

	public List<ItemBean> viewAllItems(RestaurantBean rest) throws RestaurantException;

	public List<ItemBean> viewAllItems(CategoryBean rest) throws ItemException;

	public List<ItemBean> viewAllItemsByName(String itmName) throws ItemException;
}
