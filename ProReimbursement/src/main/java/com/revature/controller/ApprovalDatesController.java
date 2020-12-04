package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.service.ApprovalDatesService;

public class ApprovalDatesController {
	static ApprovalDatesService apServ = new ApprovalDatesService();
	
	public static String initialDate(HttpServletRequest req) {
		int formID = 0; 
		formID = Integer.parseInt(req.getParameter("formID"));
		apServ.updateInitialDate(formID);
		return "resources/html/benCoHome.html";
	}

}
