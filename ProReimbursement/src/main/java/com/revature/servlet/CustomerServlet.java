package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		System.out.println("in doGet");
		req.getRequestDispatcher(RequestHelper.process(req)).forward(req,res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		// gets called first
		System.out.println("in doPost");
		req.getRequestDispatcher(RequestHelper.process(req)).forward(req,res); //what response I need
	}

}
