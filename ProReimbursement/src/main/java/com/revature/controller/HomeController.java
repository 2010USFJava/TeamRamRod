package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

public class HomeController {
	
	public static String customerHome(HttpServletRequest req) {
		return "resources/html/customerHome.html";		
	}
	
	public static String adminHome(HttpServletRequest req) {
		return "resources/html/adminHome.html";		
	}
}
