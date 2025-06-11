package com.cg.foodles.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.foodles.dao.IItemRepository;
import com.cg.foodles.entity.CategoryBean;
import com.cg.foodles.entity.ItemBean;
import com.cg.foodles.entity.RestaurantBean;
import com.cg.foodles.exceptions.ItemException;
import com.cg.foodles.exceptions.RestaurantException;

@Service
public class IItemServiceImpl implements IItemService {

	@Autowired
	IItemRepository itemDao;
	
	@Override
	public ItemBean addItem(ItemBean item) throws ItemException {
		if(item!=null) {
			return itemDao.save(item);
		}
		throw new ItemException("Please Check Your Data");
	}

	@Override
	public ItemBean updateItem(ItemBean item) throws ItemException {
		if(itemDao.existsById(item.getItemId())) {
			return itemDao.save(item);
		}
		throw new ItemException("Item Not Updated");
	}

	@Override
	public ItemBean removeItem(ItemBean item) throws ItemException {
		if(item!=null) {
			if(itemDao.existsById(item.getItemId())) {
				ItemBean itm = itemDao.findById(item.getItemId()).get();
				itemDao.deleteById(item.getItemId());
				return itm;
			}
		}
		throw new ItemException("Item not found");
	}

	@Override
	public ItemBean viewItem(ItemBean item) throws ItemException {
		if(item!=null) {
			if(itemDao.existsById(item.getItemId())) {
				return itemDao.findById(item.getItemId()).get();
			}
		}
		throw new ItemException("Item not found");
	}

	@Override
	public List<ItemBean> viewAllItems(RestaurantBean rest) throws RestaurantException {
		if(rest!=null)
			return rest.getItemList();
		throw new RestaurantException("Restaurant not found");
	}

	

	@Override
	public List<ItemBean> viewAllItems(CategoryBean cat) throws ItemException {
		if(cat!=null) {
			List<ItemBean> itmList = itemDao.findAll();
			List<ItemBean> filItemList = new ArrayList<ItemBean>();
			for(ItemBean itm : itmList) {
				if(itm.getCategory().getCategoryName().equalsIgnoreCase(cat.getCategoryName())) {
					filItemList.add(itm);
				}
			}
			if(filItemList.size()>0) {
				return filItemList;
			}
		}
		throw new ItemException("Items not found");		
	}

	@Override
	public List<ItemBean> viewAllItemsByName(String itmName) throws ItemException {
		
		List<ItemBean> itmList = itemDao.findAll();
		List<ItemBean> filItemList = new ArrayList<ItemBean>();
		for(ItemBean itm : itmList) {
			if(itm.getItemName().equalsIgnoreCase(itmName)) {
				filItemList.add(itm);
			}
		}
		if(filItemList.size()>0) {
			return filItemList;
		}
		throw new ItemException("Items not found");
	}
	
	
	public ItemBean isItem(Integer itmId) {
		if(itemDao.existsById(itmId)) {
			ItemBean item = itemDao.findById(itmId).get();
			if(item!=null) {
				return item;
			}
		}
		return null;
	}
	
	
	public List<ItemBean> viewAllItems(){
		return itemDao.findAll();
	}
	
	
}
