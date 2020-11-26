package com.revature.driver;

import java.sql.SQLException;

import com.revature.beans.Manager;
import com.revature.dao.CustomerDao;
import com.revature.dao.ManagerDao;
import com.revature.daoimpl.CustomerDaoImpl;
import com.revature.daoimpl.ManagerDaoImpl;

public class Driver {

	public static void main(String[] args) {
		CustomerDao c = new CustomerDaoImpl();
		ManagerDao m = new ManagerDaoImpl();
		try {
			//c.createCustomer(new Customer(1, "Sukanya", "Raj", "email", "password"));
			//System.out.println(c.findCustomerById(1));
			//m.createManager(new Manager(1, "John", "Doe", "email", "password", " direct supervisor"));
			System.out.println(m.findManagerById(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
