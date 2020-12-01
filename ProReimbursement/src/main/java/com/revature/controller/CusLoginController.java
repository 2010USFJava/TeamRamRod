package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.beans.Customer;
import com.revature.beans.Form;
import com.revature.service.CustomerService;

public class CusLoginController {
	static CustomerService cServ = new CustomerService();
	public static Customer currentCustomer;
	public static Form currentForm;
	
	public static String login(HttpServletRequest req) {
		if(!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
;
		Customer cus = cServ.logGetCustomer(email, password);
		if(cus == null) {
			return "invalid.customer";
		} else {
			currentCustomer = cus;
			req.getSession().setAttribute("currentcus", cus);
			return "home.customer";
		}
	}
}
