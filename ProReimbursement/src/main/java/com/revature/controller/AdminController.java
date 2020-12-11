package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import com.revature.service.AdminService;

public class AdminController {
	static AdminService aServ = new AdminService();
	
	public static String getAdminForms(HttpServletRequest req) {
		return "resources/html/admin/adminHome.html";
	}
}
