package com.cg.foodles.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.foodles.dao.ICartRepository;
import com.cg.foodles.dao.IItemRepository;
import com.cg.foodles.entity.FoodCartBean;
import com.cg.foodles.entity.ItemBean;
import com.cg.foodles.exceptions.CartException;
import com.cg.foodles.exceptions.ItemException;

@Service
public class ICartServiceImpl implements ICartService {

	@Autowired
	ICartRepository cartDao;

	@Autowired
	IItemRepository itemDao;

	@Override
	public FoodCartBean addItemToCart(FoodCartBean cart, ItemBean item) throws CartException, ItemException {
		if((cart!=null) &&(item!=null)) {
			if(cartDao.existsById(cart.getCartId())) {
				if(itemDao.existsById(item.getItemId())) {
					Map<ItemBean,Integer> item_cartQty = cart.getItem_cartQty();
					if(item_cartQty.containsKey(item)) {
						cart = increaseQuantity(cart,item,1);
						return cart;
					}
					cart.addItem(item);
					return cartDao.save(cart);
				  }
			}
			throw new CartException("Cart with id "+cart.getCartId()+" not found");
		}
		throw new CartException("Item/Cart not found");
	}

	@Override
	public FoodCartBean increaseQuantity(FoodCartBean cart, ItemBean item, Integer quantity) throws ItemException {
		if((cart!=null) &&(item!=null)&&(quantity>0)){
			if(cartDao.existsById(cart.getCartId())) {	
				Map<ItemBean,Integer> item_cartQty = cart.getItem_cartQty();
				if(item_cartQty.containsKey(item)) {
					Integer currQty = item_cartQty.get(item);
					Integer availQty = item.getQuantity();
					if(currQty<availQty) {
						Integer increasedQty = (quantity+currQty);
						if((availQty-increasedQty)>=0) {
							System.out.println("was i here");
							cart.getItem_cartQty().put(item,increasedQty);
							return cartDao.save(cart);
						}
						throw new ItemException(item.getItemName()+" quantity exceeds available quantity");
					}
				}
				throw new ItemException(item.getItemName()+" quantity exceeds available quantity");
			}
		}
		throw new ItemException("Cart with id "+cart.getCartId()+" not found");
	}

	@Override
	public FoodCartBean reduceQuantity(FoodCartBean cart, ItemBean item, int quantity) throws CartException, ItemException {
		if(cart!=null && item!=null && quantity>0) {
			if(cartDao.existsById(cart.getCartId())) {
				Map<ItemBean,Integer> item_cartQty = cart.getItem_cartQty();
				if(item_cartQty.containsKey(item)) {
					Integer currQty = item_cartQty.get(item);
					if(quantity>=currQty) {
						cart.removeItem(item);
						return cartDao.save(cart);
					}
					Integer reducedQty =(currQty-quantity);
					cart.getItem_cartQty().put(item, reducedQty);
					return cartDao.save(cart);
					}		
				}
				throw new ItemException(item.getItemName()+" not found in cart");
			}
		throw new CartException("Cart empty/notfound"); 
	}

	@Override
	public FoodCartBean removeItem(FoodCartBean cart, ItemBean item) throws ItemException, CartException {
		if(cart!=null && item!=null) {
			if(cartDao.existsById(cart.getCartId())) {
				if(itemDao.existsById(item.getItemId())) {
					System.out.println("Hello");
					Map<ItemBean,Integer> item_cartQty = cart.getItem_cartQty();
					if(item_cartQty.containsKey(item)) {
						cart.removeItem(item);
						cart = cartDao.save(cart);
						return  cart;
					}
					else{
						throw new ItemException(item.getItemName()+" not found in cart");
					}
				}
			}
		}
		throw new CartException("Cart empty/notfound");
	}

	@Override
	public FoodCartBean clearCart(FoodCartBean cart) throws CartException {
		if(cart!=null) {
			if(cartDao.existsById(cart.getCartId())) {
				cart.getItemList().clear();
				cart.getItem_cartQty().clear();
				if(cart.getItemList().size()>0)
					throw new CartException("Cart not empty");
				else
					return cartDao.save(cart);
			}
		}
		throw new CartException("Cart empty/notfound");
	}
	
	
	
	public FoodCartBean viewCart(FoodCartBean cart) {
		
		return cartDao.findById(cart.getCartId()).get();
	}
	
	public FoodCartBean isCart(String cartId) {
		if(cartDao.existsById(cartId)) {
			FoodCartBean foodCart = cartDao.findById(cartId).get();
			if(foodCart!=null) {
				return foodCart;
			}
		}
		return null;
	}
	
	
	
}
