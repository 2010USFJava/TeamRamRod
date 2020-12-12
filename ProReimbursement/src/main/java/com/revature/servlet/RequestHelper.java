package com.revature.servlet;

import javax.servlet.http.HttpServletRequest;

import com.revature.controller.AdminController;
import com.revature.controller.AdminLoginController;
import com.revature.controller.ApprovalDatesController;
import com.revature.controller.CusLoginController;
import com.revature.controller.FormController;
import com.revature.controller.HomeController;

public class RequestHelper {
	
	public static String process(HttpServletRequest req) {
		System.out.println(req.getRequestURI()); //URI: what resource am I going to get from this domain
		
		switch(req.getRequestURI()) {
		case "/ProReimbursement/index.customer":
			System.out.println("in index customer rhelper");
			return CusLoginController.index(req);
			
		case "/ProReimbursement/login.customer":
			System.out.println("in customer login rhelper");
			return CusLoginController.login(req);
		
		case "/ProReimbursement/home.customer":
			System.out.println("in customer home rhelper");
			return HomeController.customerHome(req);
		
		case "/ProReimbursement/login.admin":
			System.out.println("in admin login rhelper");
			return AdminLoginController.login(req);
			
		case "/ProReimbursement/home.admin":
			System.out.println("in admin home rhelper");
			return HomeController.adminHome(req); 
			
		case "/ProReimbursement/options.admin":
			System.out.println("in admin options rhelper");
			return AdminController.getOptionForm(req);
			
		case "/ProReimbursement/info.form":
			System.out.println("in customer form rhelper");
			return FormController.customerForm(req); 
			
		case "/ProReimbursement/fill.form":
			System.out.println("in form rhelper");
			return FormController.fillOutForm(req);
			
		case "/ProReimbursement/newRequest.form":
			System.out.println("in form rhelper");
			return FormController.newRequest(req);
			
		case "/ProReimbursement/optional.form":
			System.out.println("in form optional rhelper");
			return FormController.enterNewForm(req);
		
		case "/ProReimbursement/submitGrade.form":
			System.out.println("in form optional rhelper");
			return FormController.submitGrade(req);
			
		case "/ProReimbursement/received.form":
			System.out.println("in customer form rhelper");
			return FormController.fillOptional(req); 
		
		case "/ProReimbursement/initialApproval.form":
			System.out.println("in initial approval form rhelper");
			return ApprovalDatesController.initialDate(req);
			
		case "/ProReimbursement/decision.approval":
			System.out.println("in decision approval form rhelper");
			return ApprovalDatesController.updateDecision(req);
			
		default:
			System.out.println("in default case");
			return "resources/html/unsuccessfullogin.html";
		}
	}
}
