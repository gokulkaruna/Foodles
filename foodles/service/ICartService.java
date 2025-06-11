package com.cg.foodles.service;

import com.cg.foodles.entity.FoodCartBean;
import com.cg.foodles.entity.ItemBean;
import com.cg.foodles.exceptions.CartException;
import com.cg.foodles.exceptions.ItemException;

public interface ICartService {

	public FoodCartBean addItemToCart(FoodCartBean cart, ItemBean item) throws CartException, ItemException;

	public FoodCartBean reduceQuantity(FoodCartBean cart, ItemBean item, int quantity) throws CartException, ItemException;

	public FoodCartBean removeItem(FoodCartBean cart, ItemBean item) throws ItemException, CartException;

	public FoodCartBean clearCart(FoodCartBean cart) throws CartException;

	FoodCartBean increaseQuantity(FoodCartBean cart, ItemBean item, Integer quantity) throws  ItemException;

}
