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
		
		double tuition = 1000.0;
		Customer cus = new Customer(employeeNum, req.getParameter("firstname"), 
				req.getParameter("lastname"), req.getParameter("email"), req.getParameter("password"), tuition);
		
		CusLoginController.currentCustomer = cus;
		System.out.println(cus);
		
		return "resources/html/form.html";		
	}
	
	public static String fixDate(String date) {
		String newDate = "";
		
		char[] ch = new char[date.length()];
		for(int i=0; i < ch.length; i++) {
			ch[i] = date.charAt(i);
		}
		
		for(int i=6; i < ch.length; i++) {
			newDate = newDate + ch[i];  
		}
		newDate += '-';
		System.out.println(newDate);
		for(int i=0; i < 5; i++) {
			if (ch[i] == '/') {
				newDate = newDate + '-';
			}
			else {
				newDate = newDate + ch[i];
			}
		}
		return newDate;
}
	
	public static String enterNewForm(HttpServletRequest req) {
		int eventNum = Integer.parseInt(req.getParameter("event"));
		double eventCost = Double.parseDouble(req.getParameter("eventcost"));		
		String mydate = req.getParameter("eventdate");
		System.out.println(mydate);
		LocalDate eventDate = LocalDate.parse(fixDate(mydate));
		String dept = req.getParameter("department");
		
		boolean hasFiles = false;
		if(req.getParameter("box").equals("yes")) {
			hasFiles = true;
		}
		Form form = new Form(1, eventDate, req.getParameter("eventtime"), req.getParameter("eventlocation"),
				req.getParameter("description"), eventCost, req.getParameter("gradingformat"), 
				eventNum, req.getParameter("justification"), "", hasFiles);
		
		CusLoginController.currentForm = form;
		fServ.insertNewForm(form);
		fServ.insertFormIdLookUp(form);
		cServ.insertNewCustomer(CusLoginController.currentCustomer);
		cServ.insertCustomerIdLookUp(CusLoginController.currentCustomer);
		adServ.addDepartment(CusLoginController.currentCustomer.getEmployeeID(), dept);
		return "resources/html/filesAndGrades.html";
	}
	
	public static String customerForm(HttpServletRequest req) {
		return "resources/html/customerInfo.html";		
	}
	
	public static String fillOptional(HttpServletRequest req) {
		String text = req.getParameter("optional");
		String email = req.getParameter("email");
		boolean hasEmail = false;
		if (email.equals("yes"))
			hasEmail=true;
		fServ.updateOptional(text);
		fServ.updateEmail(hasEmail);
		System.out.println("Inside has email- untested");
		return "resources/html/applicationReceived.html";
	}
}
