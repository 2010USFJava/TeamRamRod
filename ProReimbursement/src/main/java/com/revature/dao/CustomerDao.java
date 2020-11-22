package com.revature.dao;

import java.sql.SQLException;

import com.revature.beans.Customer;

public interface CustomerDao {
	public void createCustomer(Customer c) throws SQLException;
	
	public Customer findCustomerById(int employeeID) throws SQLException;

}
