package com.cg.foodles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.foodles.entity.FoodCartBean;
import com.cg.foodles.entity.ItemBean;
import com.cg.foodles.exceptions.CartException;
import com.cg.foodles.exceptions.ItemException;
import com.cg.foodles.service.ICartServiceImpl;
import com.cg.foodles.service.IItemServiceImpl;




@RequestMapping("/cart")
@RestController
@CrossOrigin("*")
public class CartController {
	
	
	@Autowired
	ICartServiceImpl cartService;
	
	@Autowired
	IItemServiceImpl itemService;
	
	FoodCartBean fCart;

	@PostMapping("/additem/{cartid}")
	public FoodCartBean addItemToCart(@RequestBody ItemBean item,@PathVariable("cartid") String cartId) throws CartException, ItemException {	
		FoodCartBean cart = cartService.isCart(cartId);
		ItemBean itm = itemService.isItem(item.getItemId());
		return cartService.addItemToCart(cart, itm);
	}
	
	
	//done
	@GetMapping("/viewcart/{cartid}")
	public FoodCartBean viewCart(@PathVariable("cartid") String cartId) throws CartException {
		FoodCartBean cart = cartService.isCart(cartId);
		return cartService.viewCart(cart);
	}

	@PostMapping("/increasequantity/{cartid}/{itemid}/{quantity}")
	public FoodCartBean increaseQuantity(@PathVariable("cartid") String cartId,@PathVariable("itemid") Integer itemId,@PathVariable("quantity") Integer quantity) throws  ItemException {
		FoodCartBean cart = cartService.isCart(cartId);
		ItemBean item = itemService.isItem(itemId);
		fCart = cartService.increaseQuantity(cart, item, quantity);
		return fCart;
	}

	@PostMapping("/reducequantity/{cartid}/{itemid}/{quantity}")
	public FoodCartBean reduceQuantity(@PathVariable("cartid") String cartId,@PathVariable("itemid") Integer itemId,@PathVariable("quantity") Integer quantity) throws CartException, ItemException {
		FoodCartBean cart = cartService.isCart(cartId);
		ItemBean item = itemService.isItem(itemId);
		fCart =cartService.reduceQuantity(cart, item, quantity);
		return fCart;	
	}

	@PostMapping("/removeitem/{cartid}/{itemid}")
	public FoodCartBean removeItem(@PathVariable("cartid") String cartId,@PathVariable("itemid") Integer itemId) throws ItemException, CartException {
		FoodCartBean cart = cartService.isCart(cartId);
		ItemBean item = itemService.isItem(itemId);
		return cartService.removeItem(cart, item);
	}

	@PostMapping("/clear/{cartid}")
	public FoodCartBean clearCart(@PathVariable("cartid") String cartId) throws CartException{
		FoodCartBean cart = cartService.isCart(cartId);
		fCart = cartService.clearCart(cart);
		return fCart;
	}
	
	
	

}
