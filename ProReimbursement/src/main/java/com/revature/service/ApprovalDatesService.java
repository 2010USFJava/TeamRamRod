package com.revature.service;

import java.sql.SQLException;

import com.revature.beans.ApprovalDates;
import com.revature.controller.CusLoginController;
import com.revature.dao.ApprovalDatesDao;
import com.revature.dao.CustomerDao;
import com.revature.dao.FormDao;
import com.revature.daoimpl.ApprovalDatesDaoImpl;
import com.revature.daoimpl.CustomerDaoImpl;
import com.revature.daoimpl.FormDaoImpl;

public class ApprovalDatesService {
	ApprovalDatesDao addao = new ApprovalDatesDaoImpl();
	FormDao fdao = new FormDaoImpl();
	CustomerService cServ = new CustomerService();
	CustomerDao cdao = new CustomerDaoImpl();
	
	public void newApprovalDate(ApprovalDates ad) {
		try {
			addao.createApprovalDate(ad);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ApprovalDates getApprovalDate(int formID) {
		try {
			return addao.getApprovalDate(formID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// updates initial date and tuition
	public void updateInitialDate(int formID) {
		try {
			addao.updateApprovalDate(formID);
			CusLoginController.currentForm = fdao.findFormByID(formID);
			int employeeID = fdao.findCustomerIDByFormIDLookUp(formID);
			CusLoginController.currentCustomer = cdao.getCustomerById(employeeID);
			cServ.updateTuition();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
