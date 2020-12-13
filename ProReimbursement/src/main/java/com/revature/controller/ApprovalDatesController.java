package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.service.ApprovalDatesService;
import com.revature.service.CustomerService;

public class ApprovalDatesController {
	static ApprovalDatesService apServ = new ApprovalDatesService();
	static CustomerService cServ = new CustomerService();
	
	public static String initialDate(HttpServletRequest req) {
		int formID = 0; 
		formID = Integer.parseInt(req.getParameter("formID"));
		apServ.insertFirstDate(formID);
		return "resources/html/admin/adminHome.html";
	}
	
	public static String updateDecision(HttpServletRequest req) {
		String decision = req.getParameter("isApproved");
		System.out.println("Decision: " + decision);
		String admin;
		boolean choice;
		if(decision.equals("yes")) {
			choice = true;
			System.out.println("choice: " + choice);
			admin = AdminLoginController.currentAdmin.getTitle();
			if(admin.equals("benco")) {
				apServ.setUpdateApproval(CusLoginController.currentForm.getFormID(), choice);
				apServ.updateInitialDate(CusLoginController.currentForm.getFormID(), admin);
				System.out.println("benco yes: ");
				double tuition = cServ.findTuition(CusLoginController.currentForm.getFormID());
				double reimburse = apServ.findReimbursement(CusLoginController.currentForm.getFormID());
				double newTuition = tuition - reimburse;
				cServ.updateNewTuition(CusLoginController.currentForm.getFormID(), newTuition);
				
			} else if(admin.equals("direct supervisor") || admin.equals("department head")) { 
				apServ.updateInitialDate(CusLoginController.currentForm.getFormID(), admin);
				System.out.println("supervisor or dept head YES");
			} else { //superhead
				System.out.println("superhead yes!");
				apServ.updateInitialDate(CusLoginController.currentForm.getFormID(), "direct supervisor");
				apServ.updateInitialDate(CusLoginController.currentForm.getFormID(), "department head");
			}
		} else {
			choice = false;
			System.out.println("choice: " + choice);
			apServ.setUpdateApproval(CusLoginController.currentForm.getFormID(), choice);
			apServ.updateInitialDate(CusLoginController.currentForm.getFormID(), "direct supervisor");
			apServ.updateInitialDate(CusLoginController.currentForm.getFormID(), "department head");
			apServ.updateInitialDate(CusLoginController.currentForm.getFormID(), "benco");		
		}
		return "resources/html/admin/adminHome.html";
	}

}
