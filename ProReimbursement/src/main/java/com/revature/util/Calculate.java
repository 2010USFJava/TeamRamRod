package com.revature.util;

import java.sql.SQLException;

import com.revature.controller.CusLoginController;
import com.revature.dao.CustomerDao;
import com.revature.daoimpl.CustomerDaoImpl;
import com.revature.service.CustomerService;

public class Calculate {
	CustomerService cServ = new CustomerService();
	CustomerDao cdao = new CustomerDaoImpl();
	//AvailableReimbursement = TotalReimbursement ($1000) – PendingReimbursements – AwardedReimbursements
	//If the projected reimbursement for an event exceeds the available reimbursement amount, it is adjusted to the amount available
	
	//after the third approval, the tuition is subtracted by calculated reimbursement
	public double calculateTuitionRemaining(double reimbursementAmount) {
		System.out.println("reimbursementAmount: " + reimbursementAmount);
		double newTuition = cServ.getTuition(CusLoginController.currentCustomer) - reimbursementAmount;
		System.out.println("new tuition: " + newTuition);
		return newTuition;
	}
	
	//just checks if new reimbursement amount is greater than available tuition amount
	public boolean exceedsTuition(double reimbursementAmount) {
		return (calculateTuitionRemaining(reimbursementAmount) < 0);		
	}
	
	public double setModifyTuition(double reimbursementAmount) {
		if(exceedsTuition(reimbursementAmount)) {
			double temp = cServ.getTuition(CusLoginController.currentCustomer);
			CusLoginController.currentCustomer.setTuition(0);
			try {
				cdao.updateTuition(CusLoginController.currentCustomer);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return temp;
		} else {
			double newTuition = calculateTuitionRemaining(reimbursementAmount);
			CusLoginController.currentCustomer.setTuition(newTuition);
			try {
				cdao.updateTuition(CusLoginController.currentCustomer);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return newTuition;
		}
	}

}
