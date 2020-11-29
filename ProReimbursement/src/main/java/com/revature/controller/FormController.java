package com.revature.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import com.revature.beans.Customer;
import com.revature.beans.Form;
import com.revature.service.CustomerService;
import com.revature.service.FormService;

public class FormController {
	static CustomerService cServ = new CustomerService();
	static FormService fServ = new FormService();
	
	public static String fillOutForm(HttpServletRequest req) {
		int employeeNum = Integer.parseInt(req.getParameter("employeeID"));
		double tuition = 1000.0;
		Customer cus = new Customer(employeeNum, req.getParameter("firstname"), 
				req.getParameter("lastname"), req.getParameter("email"), req.getParameter("password"), tuition);
		
		cServ.insertNewCustomer(cus);
		System.out.println(cus);
		return "resources/html/form.html";		
	}
	
	public static String enterNewForm(HttpServletRequest req) {
		int eventNum = Integer.parseInt(req.getParameter("event"));
		double eventCost = Double.parseDouble(req.getParameter("eventcost"));
		Date eventDate = Date.valueOf(req.getParameter("eventdate"));
		boolean hasFiles = false;
		if(req.getParameter("box").equals("yes")) {
			hasFiles = true;
		}
		Form form = new Form(1, eventDate, req.getParameter("eventtime"), req.getParameter("eventlocation"),
				req.getParameter("description"), eventCost, req.getParameter("gradingformat"), 
				eventNum, req.getParameter("justification"), "", hasFiles);
		
		fServ.insertNewForm(form);
		return "resources/html/applicationReceived.html";
	}
	
	public static String customerForm(HttpServletRequest req) {
		return "resources/html/customerInfo.html";		
	}
}