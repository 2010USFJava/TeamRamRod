package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.service.AdminService;
import com.revature.service.FormService;

public class AdminController {
	static AdminService aServ = new AdminService();
	static FormService fServ = new FormService();
	
	public static String getAdminForms(HttpServletRequest req) {
		return "resources/html/admin/adminHome.html";
	}

	public static String getOptionForm(HttpServletRequest req) {
		System.out.println("formNum: line 17= " + req.getParameter("formNum"));
		int printForm = Integer.parseInt(req.getParameter("formNum"));
		CusLoginController.currentForm = fServ.getForm(printForm);
		List<Integer> approvedIDs = aServ.getInitialIDs(); //isApproved is true
		for(int a: approvedIDs) {
			if(printForm == a) {
				return "resources/html/finalBenco.html";
			}
		}
		return "resources/html/admin/adminOptions.html"; 
	}
}
