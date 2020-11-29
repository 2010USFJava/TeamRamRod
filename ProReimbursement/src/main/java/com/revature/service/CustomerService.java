package com.revature.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Customer;
import com.revature.dao.CustomerDao;
import com.revature.daoimpl.CustomerDaoImpl;

public class CustomerService {
	
	CustomerDao cusdao = new CustomerDaoImpl();
	
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

}

