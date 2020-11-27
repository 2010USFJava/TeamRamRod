package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.beans.Customer;
import com.revature.service.CustomerService;

public class CusLoginController {
	static CustomerService cServ = new CustomerService();
	
	public static String login(HttpServletRequest req) {
		if(!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		System.out.println("EMAIL: " + email);
		System.out.println("PASSWORD: " + password);
		Customer cus = cServ.logGetCustomer(email, password);
		System.out.println("CUS: " + cus);
		if(cus == null) {
			return "invalid.change";
		} else {
			req.getSession().setAttribute("currentcus", cus);
			return "home.customer";
		}
	}
}
