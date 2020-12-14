package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Customer;
import com.revature.beans.Form;
import com.revature.beans.Manager;
import com.revature.util.LogThis;

public class CustomerController extends HttpServlet{
	
	private static final long serialVersionUID = 8517198254731504443L;

	public static void getSessionCustomer(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		Customer cus = (Customer) req.getSession().getAttribute("currentcus");
		res.getWriter().write(new ObjectMapper().writeValueAsString(cus)); 
	}
	
	public static String logoutSession(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		//res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
		res.setHeader("Cache-Control", "no-cache");
		res.setHeader("Cache-Control", "no-store");
		res.setDateHeader("Expires", 0);
		HttpSession session = req.getSession();
		
		 //session.removeAttribute(CusLoginController.currentCustomer.getEmail());
	     session.invalidate(); 
		 session = req.getSession(false);

		 //res.sendRedirect("http://localhost:8080/ProReimbursement/resources/html/index.html");
		 
		 CusLoginController.currentCustomer = new Customer();
		 CusLoginController.currentForm = new Form();
		 AdminLoginController.currentAdmin = new Manager();
		 
		 LogThis.LogIt("info", "User has logged out");
	     System.out.println("Session : " + session);
	     return "resources/html/index.html";
	}
}
