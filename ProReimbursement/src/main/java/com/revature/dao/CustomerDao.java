package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Customer;

public interface CustomerDao {
	public void createCustomer(Customer c) throws SQLException;
	
	public Customer getCustomerById(int employeeID) throws SQLException;
	
	public Customer getCustomerByEmail(String email) throws SQLException;
	
	public List<Customer> getAllCustomers() throws SQLException;
	
	public void updateTuition(Customer c) throws SQLException;
	
	public void insertIdCustomerLookUp(Customer c) throws SQLException;
}
