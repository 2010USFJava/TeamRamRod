package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		System.out.println("in doGet");
		req.getRequestDispatcher(RequestHelper.process(req)).forward(req,res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		System.out.println("in Master doPost");
		System.out.println("URI: " + req.getRequestURI());
		String nextServlet = "";
		
		if(req.getRequestURI().contains("customer")) {
			nextServlet = "login.customer";			
		} else if(req.getRequestURI().contains("admin")) {
			nextServlet = "login.admin";
		} else if(req.getRequestURI().contains("form")) {
			nextServlet = "info.form";
		}
		System.out.println("Next servlet: " + nextServlet);
		RequestDispatcher rd = req.getRequestDispatcher(nextServlet);
		rd.forward(req, res);
	}

}
