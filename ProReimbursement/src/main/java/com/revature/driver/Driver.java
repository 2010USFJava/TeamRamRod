package com.revature.driver;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import com.revature.beans.Form;
import com.revature.dao.FormDao;
import com.revature.daoimpl.FormDaoImpl;

public class Driver {

	public static void main(String[] args) {
//		CustomerDao c = new CustomerDaoImpl();
//		ManagerDao m = new ManagerDaoImpl();
		FormDao f = new FormDaoImpl();
//		
		Date sqlDate = Date.valueOf("2020-11-30");
		System.out.println(sqlDate);
//		try {
			try {
				f.createForm(new Form(1,sqlDate,"5pm","Los Angeles","Network+ Cert", 
						200.0,"1000 point, 750 pass", 5,"Need it","", true));
				System.out.println();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
//			System.out.println(CustomerService.logGetCustomer("email1", "password1"));
//			//c.createCustomer(new Customer(2, "Mike", "Garcia", "email1", "password1"));
//			//System.out.println(c.getCustomerByEmail("email1"));
//			//m.createManager(new Manager(1, "John", "Doe", "email", "password", " direct supervisor"));
//			//System.out.println(m.findManagerById(1));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

	}


