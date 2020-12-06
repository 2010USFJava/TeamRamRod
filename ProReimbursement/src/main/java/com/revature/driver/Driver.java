package com.revature.driver;

import java.sql.SQLException;

import com.revature.controller.CusLoginController;
import com.revature.controller.FormController;
import com.revature.dao.CustomerDao;
import com.revature.daoimpl.CustomerDaoImpl;
import com.revature.service.ApprovalDatesService;
import com.revature.service.CustomerService;
import com.revature.util.Calculate;

public class Driver {

	public static void main(String[] args) {
		Calculate cal = new Calculate();
		CustomerDao c = new CustomerDaoImpl();
		ApprovalDatesService apServ = new ApprovalDatesService();
		CustomerService cServ = new CustomerService();
		
		System.out.println("in driver");
		String newString = FormController.fixDate("12/05/2020");
		
		System.out.println("new: "+newString);
		
//		try {
//			cServ.getCurrentForm(c.getCustomerById(1010), 101);
//			System.out.println(CusLoginController.currentForm);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
//		apServ.updateInitialDate(100);
//		System.out.println(CusLoginController.currentCustomer.getTuition());
//		System.out.println(CusLoginController.currentCustomer);
//	
//		try {
//			
////			CusLoginController.currentCustomer = c.getCustomerById(1234);
////			cal.setModifyTuition(200);
////			System.out.println(CusLoginController.currentCustomer);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
//		CustomerDao c = new CustomerDaoImpl();
//		ManagerDao m = new ManagerDaoImpl();
//		FormDao f = new FormDaoImpl();
//		FormService fServ = new FormService();
//		
//		Date sqlDate = Date.valueOf("2020-11-30");
//		System.out.println(sqlDate);
//			Form newForm = new Form(1,sqlDate,"1pm","Los Angeles","Network+ Cert", 
//							200.0,"1000 point, 750 pass", 5,"Need it","", true);
//			
//			fServ.insertNewForm(newForm);
//			System.out.println(newForm);
//			System.out.println(fServ.getForm(3));
//	}
//			System.out.println(CustomerService.logGetCustomer("email1", "password1"));
//			//c.createCustomer(new Customer(2, "Mike", "Garcia", "email1", "password1"));
//			//System.out.println(c.getCustomerByEmail("email1"));
//			//m.createManager(new Manager(1, "John", "Doe", "email", "password", " direct supervisor"));
//			//System.out.println(m.findManagerById(1));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}




