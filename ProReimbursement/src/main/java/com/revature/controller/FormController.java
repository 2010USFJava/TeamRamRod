package com.revature.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import com.revature.beans.Customer;
import com.revature.beans.Form;
import com.revature.service.AdminService;
import com.revature.service.ApprovalDatesService;
import com.revature.service.CustomerService;
import com.revature.service.FormService;

public class FormController {
	static CustomerService cServ = new CustomerService();
	static FormService fServ = new FormService();
	static ApprovalDatesService aServ = new ApprovalDatesService();
	static AdminService adServ = new AdminService();
	
	public static String fillOutForm(HttpServletRequest req) {
		int employeeNum = Integer.parseInt(req.getParameter("employeeID"));
		String dept = req.getParameter("department");
		
		double tuition = 1000.0;
		Customer cus = new Customer(employeeNum, req.getParameter("firstname"), 
				req.getParameter("lastname"), req.getParameter("email"), req.getParameter("password"), tuition);
		
		CusLoginController.currentCustomer = cus;
		System.out.println(cus);
		adServ.addDepartment(employeeNum, dept);
		return "resources/html/form.html";		
	}
	
	public static String enterNewForm(HttpServletRequest req) {
		int eventNum = Integer.parseInt(req.getParameter("event"));
		double eventCost = Double.parseDouble(req.getParameter("eventcost"));		
		LocalDate eventDate = LocalDate.parse(req.getParameter("eventdate"));
		boolean hasFiles = false;
		if(req.getParameter("box").equals("yes")) {
			hasFiles = true;
		}
		Form form = new Form(1, eventDate, req.getParameter("eventtime"), req.getParameter("eventlocation"),
				req.getParameter("description"), eventCost, req.getParameter("gradingformat"), 
				eventNum, req.getParameter("justification"), "", hasFiles);
	
		fServ.insertNewForm(form);
		fServ.insertFormIdLookUp(form);
		cServ.insertNewCustomer(CusLoginController.currentCustomer);
		cServ.insertCustomerIdLookUp(CusLoginController.currentCustomer);
		return "resources/html/applicationReceived.html";
	}
	
	public static String customerForm(HttpServletRequest req) {
		return "resources/html/customerInfo.html";		
	}
	
	public static String fillOptional(HttpServletRequest req) {
		return "resources/html/filesAndGrades.html";
	}
}
