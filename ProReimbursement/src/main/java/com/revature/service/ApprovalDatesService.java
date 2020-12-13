package com.revature.service;

import java.sql.SQLException;

import com.revature.beans.ApprovalDates;
import com.revature.beans.Form;
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
	
	public ApprovalDates createApprovalBean(Form f) {
		ApprovalDates appdate = new ApprovalDates();
		appdate.setFormID(f.getFormID());
		appdate.setDateEntered(f.getDate());
		appdate.setReimbursement(cServ.calculateReimbursementByEventNum(f.getCost(), f.getEventNum()));
		return appdate;
	}
	
	public ApprovalDates getApprovalDate(int formID) {
		try {
			return addao.getApprovalDate(formID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// updates initial date and reimbursement
	public void updateInitialDate(int formID, String title) {
		try {
			addao.updateApprovalDate(formID, title);
			CusLoginController.currentForm = fdao.findFormByID(formID);
			int employeeID = fdao.findCustomerIDByFormIDLookUp(formID);
			CusLoginController.currentCustomer = cdao.getCustomerById(employeeID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setUpdateApproval(int formID, boolean decision) {
		try {
			addao.approvalInitial(formID, decision);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertFirstDate(int formID) {
		try {
			addao.enterFirstDate(formID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateInitialApprovalTuition(int formID, double reimburse) {
		try {
			addao.updateReimbursement(formID, reimburse);;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public double findReimbursement(int formID) {
		double reimburse = 0.0;
		try {
			reimburse = addao.getReimbursement(formID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimburse;
	}

}
