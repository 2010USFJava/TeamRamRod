package com.revature.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controller.AdminLoginController;
import com.revature.controller.CusLoginController;
import com.revature.controller.CustomerController;
import com.revature.controller.HomeController;
import com.revature.service.AdminService;
import com.revature.service.CustomerService;
import com.revature.service.FormService;

public class JsonRequestHelper {
	static CustomerService cServ = new CustomerService();
	static FormService fServ = new FormService();
	static AdminService aServ = new AdminService();
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		switch(req.getRequestURI()) {
		case "/ProReimbursement/getsession.json":
			CustomerController.getSessionCustomer(req, res);
			break;
		
		case "/ProReimbursement/customerHome.json":
			System.out.println("in customerHome Json rhelper");
			HomeController.customerHome(req);
			res.getWriter().write(new ObjectMapper().writeValueAsString(fServ.getAllForm(CusLoginController.currentCustomer.getEmployeeID())));
			System.out.println("after getting forms");
			break;
			
		case "/ProReimbursement/getDates.json":
			System.out.println("in getDates json rhelper");
			res.getWriter().write(new ObjectMapper().writeValueAsString(fServ.formDateStrings(AdminService.arrayFormDates)));
			break;
		
		case "/ProReimbursement/logout.json":
			System.out.println("in logout json rhelper");
			CustomerController.logoutSession(req);
			break;
		
		case "/ProReimbursement/adminForms.json":
			System.out.println("in adminForms json rhelper");
			res.getWriter().write(new ObjectMapper().writeValueAsString(aServ.getAdminList(AdminLoginController.currentAdmin.getEmployee_ID())));
			break;
		
		default:
			System.out.println("in default case");
			//res.getWriter().write(new ObjectMapper().writeValueAsString(vill));
		}
		
	}
}
