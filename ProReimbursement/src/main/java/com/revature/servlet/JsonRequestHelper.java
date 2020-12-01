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

public class JsonRequestHelper {
	static CustomerService cServ = new CustomerService();
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		switch(req.getRequestURI()) {
		case "/ProReimbursement/getsession.json":
			CustomerController.getSessionCustomer(req, res);
			break;
		
		case "/ProReimbursement/customerHome.json":
			System.out.println("in customerHome Json rhelper");
			HomeController.customerHome(req);
			cServ.getCurrentForm(CusLoginController.currentCustomer);
			res.getWriter().write(new ObjectMapper().writeValueAsString(CusLoginController.currentForm));
			System.out.println(res.toString());
			
		default:
			System.out.println("in default case");
			//SuperVillain vill = new SuperVillain("?","?", 0);
			//res.getWriter().write(new ObjectMapper().writeValueAsString(vill));
		}
		
	}
}
