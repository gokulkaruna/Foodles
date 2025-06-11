package com.cg.foodles.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.foodles.dao.IBillRepository;
import com.cg.foodles.entity.BillBean;
import com.cg.foodles.entity.ItemBean;
import com.cg.foodles.exceptions.BillException;


@Service
public class IBillServiceImpl implements IBillService{
	@Autowired
	IBillRepository billDao;
	
	BillBean billRef;

	@Override
	public BillBean addBill(BillBean bill) throws BillException {
		if(bill!=null) {
			if(isBill(bill.getBillId())==null){
				if(bill.getOrder().getCart().getItem_cartQty().size()>0) {
					Integer totalItemCount = bill.getOrder().getCart().getItem_cartQty().size();
					bill.getOrder().setOrderStatus("ORDER CONFIRMED");
					billRef = billDao.save(bill);
					LocalDateTime now = LocalDateTime.now();  
					billRef.setBillDate(now);
					billRef.setTotalItem(totalItemCount);
					billRef.setTotalCost(calculateTotalCost(billRef));
					return billDao.save(billRef);	
				}
			}
			throw new BillException("Bill already exists with id "+bill.getBillId());
		}throw new BillException("Check yout bill id");
	}

	@Override
	public BillBean updateBill(BillBean bill) throws BillException {
		if(bill!=null) {
			if (billDao.existsById(bill.getBillId())) {
				return billDao.save(bill);
			}
		}
		throw new BillException("Bill Does Not Exists");
	}

	@Override
	public BillBean removeBill(BillBean bill) throws BillException {
		if (bill != null) {
			if (billDao.existsById(bill.getBillId())) {
				billRef = billDao.findById(bill.getBillId()).get();
				billDao.deleteById(bill.getBillId());
				return billRef;
			}throw new BillException("Bill Does Not exists with id "+bill.getBillId());
		}
		throw new BillException("Bill Does Not Exists");	
	}

	@Override
	public BillBean viewBill(BillBean bill) throws BillException {
		if(bill != null) {
			if(billDao.existsById(bill.getBillId())) {
				billRef =  billDao.findById(bill.getBillId()).get();
				return billRef;
			}throw new BillException("Bill Does Not exists with id "+bill.getBillId());
		}
		throw new BillException("Bill Does Not Exists");
	}

	@Override
	public List<BillBean> viewBills(LocalDate startDate, LocalDate endDate) throws BillException{
		LocalDateTime startDateB = startDate.atStartOfDay();  //date T00:00:00
		LocalDateTime endDateB = endDate.atStartOfDay();
		List<BillBean> billListSent = new ArrayList<BillBean>();
		List<BillBean> billList = billDao.findAll();
		for (BillBean bl: billList) {
			if (bl.getBillDate().isAfter(startDateB) && bl.getBillDate().isBefore(endDateB)) {
				billListSent.add(bl);
			}
		}
		if (billListSent.isEmpty()) {
			throw new BillException("No Bills present between "+startDate+" - "+endDate);
		}
		return billListSent;
	}

	@Override
	public List<BillBean> viewBills(String custId) throws BillException {
		List<BillBean> billList = billDao.findAll();
		List<BillBean> custBillList = new ArrayList<BillBean>();
		for (BillBean b: billList) {
			if(b.getOrder().getCart().getCustomer().getCustomerId().equalsIgnoreCase(custId)) {
				custBillList.add(b);
			}
		}
		if(custBillList.size()>0)
			return billList;
		throw new BillException("No bill customer "+custId);
	}

	@Override
	public double calculateTotalCost(BillBean bill) throws BillException {
		if (bill != null) {
			if (billDao.existsById(bill.getBillId())) {
				billRef = billDao.findById(bill.getBillId()).get();
				Map<ItemBean,Integer> cartItems = billRef.getOrder().getCart().getItem_cartQty();
				Iterator<ItemBean> itr = cartItems.keySet().iterator();
				Double totalCost = 0.0;
				while(itr.hasNext()) {
					ItemBean key = itr.next();
					Integer qty = cartItems.get(key);
					totalCost+=((key.getCost())*qty);
				}
				return totalCost;
			}
			throw new BillException("Bill Does Not exists with id "+bill.getBillId());
		}
		throw new BillException("Bill Does Not Exists");
	}
	
	public BillBean isBill(Integer billId) throws BillException{
		if(billId!=null){
			if (billDao.existsById(billId)) {
				billRef = billDao.findById(billId).get();
				return billRef;
			}   
		}
		return null;
	}
}