package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Customer;

public class CustomerController extends HttpServlet{
	
	private static final long serialVersionUID = 8517198254731504443L;

	public static void getSessionCustomer(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		Customer cus = (Customer) req.getSession().getAttribute("currentcus");
		res.getWriter().write(new ObjectMapper().writeValueAsString(cus)); //write vill as JSON and add that to response
	}
	
	public static void logoutSession(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		 HttpSession session = req.getSession();
		
	  
	        session.invalidate(); 
		 
	        
	        session = req.getSession(false);
	
	        System.out.println("Session : " + session);
	}
}
