package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.beans.Manager;
import com.revature.service.AdminService;
import com.revature.util.LogThis;

public class AdminLoginController {
	static AdminService aServ = new AdminService();
	public static Manager currentAdmin;
	
	public static String login(HttpServletRequest req) {
		if(!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Manager ad = aServ.logGetAdmin(email, password);
		if(ad == null) {
			LogThis.LogIt("info", ad.getEmail() + ", admin has failed to logged in");
			return "invalid.admin";
		} else {
			currentAdmin = ad;
			req.getSession().setAttribute("currentad", ad);
			LogThis.LogIt("info", ad.getEmail() + ", admin has logged in");
			return "home.admin";
		}
	}


}
