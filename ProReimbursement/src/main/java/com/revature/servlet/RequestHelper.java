package com.revature.servlet;

import javax.servlet.http.HttpServletRequest;

import com.revature.controller.HomeController;
import com.revature.controller.CusLoginController;

public class RequestHelper {
	
	public static String process(HttpServletRequest req) {
		System.out.println(req.getRequestURI()); //URI: what resource am I going to get from this domain
		System.out.println(req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/ProReimbursement/login.customer":
			System.out.println("in login rhelper");
			return CusLoginController.login(req);
		
		case "/ProReimbursement/home.customer":
			System.out.println("in home rhelper");
			return HomeController.home(req); //home controller takes us to home.html (view back to us)
			
		default:
			System.out.println("in default case");
			return "resources/html/unsuccessfullogin.html";
		}
	}
}
