package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.beans.Manager;
import com.revature.service.AdminService;

public class AdminLoginController {
	static AdminService aServ = new AdminService();
	
	public static String login(HttpServletRequest req) {
		if(!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Manager ad = aServ.logGetAdmin(email, password);
		if(ad == null) {
			return "invalid.admin";
		} else {
			req.getSession().setAttribute("currentad", ad);
			return "home.admin";
		}
	}

}
