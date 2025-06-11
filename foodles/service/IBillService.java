package com.cg.foodles.service;

import java.time.LocalDate;
import java.util.List;
import com.cg.foodles.entity.BillBean;
import com.cg.foodles.exceptions.BillException;

public interface IBillService {
	public BillBean addBill(BillBean bill) throws BillException;
	public BillBean updateBill(BillBean bill) throws BillException;
	public BillBean removeBill(BillBean bill) throws BillException;
	public BillBean viewBill(BillBean bill) throws BillException;
	public List<BillBean> viewBills(LocalDate startDate, LocalDate endDate) throws BillException;
	public List<BillBean> viewBills(String custId) throws BillException;
	public double calculateTotalCost(BillBean bill) throws BillException;

}