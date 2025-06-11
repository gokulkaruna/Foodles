package com.cg.foodles.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.foodles.entity.BillBean;
import com.cg.foodles.entity.OrderDetailsBean;
import com.cg.foodles.exceptions.BillException;
import com.cg.foodles.service.IBillServiceImpl;
import com.cg.foodles.service.IOrderServiceImpl;

@RequestMapping("/bill")
@RestController
public class BillController {
	@Autowired
	IBillServiceImpl billService;
	
	@Autowired
	IOrderServiceImpl orderService;
	BillBean billRef;
	
	@PostMapping("/add/{orderid}")
	public BillBean addBill(@PathVariable ("orderid") Integer orderId) throws BillException {
		OrderDetailsBean order =orderService.isOrder(orderId);
		BillBean newBill = new BillBean();
		if(order!=null) {
			newBill.setOrder(order);
		}
		return billService.addBill(newBill);
	}
	
	@PutMapping("/update")
	public BillBean updateBill(@RequestBody BillBean bill) throws BillException{
			billRef = billService.updateBill(bill);
			return billRef;
	}
	
	@DeleteMapping("/remove/{billId}")
	public BillBean removeBill(@PathVariable ("billId") Integer billId) throws BillException{
			billRef = billService.isBill(billId);
			billRef = billService.removeBill(billRef);
			return billRef;
	}
	

	@GetMapping("/view/{billId}")
	public BillBean viewBill(@PathVariable("billId") Integer billId) throws BillException {
		billRef = billService.isBill(billId);
		return billService.viewBill(billRef);
	
	}
	
	
	@GetMapping("/viewwithdate")
	public List<BillBean> viewBills(@RequestParam("startdate")  String start_Date, @RequestParam("enddate") String  end_Date) throws BillException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate startDate = LocalDate.parse(start_Date, formatter);
		LocalDate endDate = LocalDate.parse(end_Date, formatter);
		return billService.viewBills(startDate, endDate);
		
	}
	
	
	@GetMapping("/viewall/{custId}")
	public List<BillBean> viewBills(@PathVariable("custId") String custId) throws BillException {
		List<BillBean> billList = billService.viewBills(custId);
		return billList;
	}

	@GetMapping("/totalcost/{billid}")
	public double calculateTotalCost(@PathVariable("billid") Integer billId) throws BillException {
		billRef = billService.isBill(billId);
		return billService.calculateTotalCost(billRef);
	}

}