package com.revature.servlet;

import javax.servlet.http.HttpServletRequest;

import com.revature.controller.AdminLoginController;
import com.revature.controller.CusLoginController;
import com.revature.controller.HomeController;

public class RequestHelper {
	
	public static String process(HttpServletRequest req) {
		System.out.println(req.getRequestURI()); //URI: what resource am I going to get from this domain
		
		switch(req.getRequestURI()) {
		case "/ProReimbursement/login.customer":
			System.out.println("in customer login rhelper");
			return CusLoginController.login(req);
		
		case "/ProReimbursement/home.customer":
			System.out.println("in customer home rhelper");
			return HomeController.customerHome(req); //home controller takes us to home.html (view back to us)
		
		case "/ProReimbursement/login.admin":
			System.out.println("in admin login rhelper");
			return AdminLoginController.login(req);
			
		case "/ProReimbursement/home.admin":
			System.out.println("in admin home rhelper");
			return HomeController.adminHome(req); //home controller takes us to home.html (view back to us)
			
		default:
			System.out.println("in default case");
			return "resources/html/unsuccessfullogin.html";
		}
	}
}
