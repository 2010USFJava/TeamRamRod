package com.revature.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Customer;
import com.revature.controller.CusLoginController;
import com.revature.dao.CustomerDao;
import com.revature.dao.FormDao;
import com.revature.daoimpl.CustomerDaoImpl;
import com.revature.daoimpl.FormDaoImpl;
import com.revature.util.Calculate;

public class CustomerService {
	
	CustomerDao cusdao = new CustomerDaoImpl();
	FormDao fdao = new FormDaoImpl();
	static Calculate calc = new Calculate();
	
	public CustomerService() {}
	
	public boolean loginVerify(String email, String password) {
		List<Customer> cList = new ArrayList<Customer>();
		try {
			cList = cusdao.getAllCustomers();
			for(Customer c: cList) {
				System.out.println(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean verify = false;
		for(Customer cus: cList) {
			if(cus.getEmail().equals(email) && cus.getPassword().equals(password)) {
				verify = true;
			}
		}
		return verify;
	}
	
	public Customer logGetCustomer(String email, String password) {
		System.out.println("Inside logGetCustomer");
		if(loginVerify(email,password)) {
			try {
				System.out.println(cusdao.getCustomerByEmail(email));
				return cusdao.getCustomerByEmail(email);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void insertNewCustomer(Customer cus) {
		try {
			cusdao.createCustomer(cus);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public double getTuition(Customer cus) {
		try {
			return cusdao.getCustomerById(cus.getEmployeeID()).getTuition();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0.0;
	}
	
	public void insertCustomerIdLookUp(Customer c) {
		try {
			cusdao.insertIdCustomerLookUp(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getCurrentForm(Customer c) {
		try {
			int formNum = fdao.findFormIDByCustomerIDLookUp(c.getEmployeeID());
			CusLoginController.currentForm = fdao.findFormByID(formNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTuition(int employeeID) {
		int formID = CusLoginController.currentForm.getFormID(); //1010
		double cost = CusLoginController.currentForm.getCost(); //222
		int eventNum = CusLoginController.currentForm.getEventNum(); //4
		double finalReimbursement = calc.setModifyTuition(calculateReimbursementByEventNum(cost, eventNum));
		CusLoginController.currentCustomer.setTuition(finalReimbursement);
		try {
			cusdao.updateTuition(CusLoginController.currentCustomer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public double calculateReimbursementByEventNum(double cost, int eventNum) {
		double reimbursement = 0;
		switch(eventNum) {
			case 1:
				System.out.println("University course: 80%");
				reimbursement = cost*.8;
				break;
			case 2:
				System.out.println("Seminar: 60%");
				reimbursement = cost*.6;
				break;
			case 3:
				System.out.println("Certification Prep class: 75%");
				reimbursement = cost*.75;
				break;
			case 4:
				System.out.println("Certification: 100%");
				reimbursement = cost;
				break;
			case 5: 
				System.out.println("Technical Training: 90%");
				reimbursement = cost*.9;
				break;
			case 6: 
				System.out.println("Other: 30%");
				reimbursement = cost*.3;
				break;
			default: 
				System.out.println("in default case, incorrect event num");
				break;
		}
		return reimbursement;
	}

}

