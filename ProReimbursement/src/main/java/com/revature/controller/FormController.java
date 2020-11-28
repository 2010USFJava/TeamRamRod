package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

public class FormController {
	
	public static String fillOutForm(HttpServletRequest req) {
		return "resources/html/form.html";		
	}
	
	public static String customerForm(HttpServletRequest req) {
		return "resources/html/customerInfo.html";		
	}
}
