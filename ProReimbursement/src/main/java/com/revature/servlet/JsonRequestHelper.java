package com.revature.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controller.CusLoginController;
import com.revature.controller.CustomerController;
import com.revature.controller.HomeController;
import com.revature.service.CustomerService;
import com.revature.service.FormService;

public class JsonRequestHelper {
	static CustomerService cServ = new CustomerService();
	static FormService fServ = new FormService();
	
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
		default:
			System.out.println("in default case");
			//res.getWriter().write(new ObjectMapper().writeValueAsString(vill));
		}
		
	}
}
