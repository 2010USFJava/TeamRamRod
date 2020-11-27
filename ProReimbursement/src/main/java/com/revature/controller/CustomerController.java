package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Customer;

public class CustomerController {
	
	public static void getSessionCustomer(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		Customer cus = (Customer) req.getSession().getAttribute("currentcus");
		res.getWriter().write(new ObjectMapper().writeValueAsString(cus)); //write vill as JSON and add that to response
	}
}
