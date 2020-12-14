package com.revature.service;

import java.sql.SQLException;

import com.revature.beans.ApprovalDates;
import com.revature.beans.Form;
import com.revature.controller.CusLoginController;
import com.revature.dao.ApprovalDatesDao;
import com.revature.dao.CustomerDao;
import com.revature.dao.FormDao;
import com.revature.dao.ManagerDao;
import com.revature.daoimpl.ApprovalDatesDaoImpl;
import com.revature.daoimpl.CustomerDaoImpl;
import com.revature.daoimpl.FormDaoImpl;
import com.revature.daoimpl.ManagerDaoImpl;
import com.revature.util.LogThis;

public class ApprovalDatesService {
	ApprovalDatesDao addao = new ApprovalDatesDaoImpl();
	FormDao fdao = new FormDaoImpl();
	CustomerService cServ = new CustomerService();
	CustomerDao cdao = new CustomerDaoImpl();
	ManagerDao man = new ManagerDaoImpl();
	
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
		double newReimbursement = cServ.calculateReimbursementByEventNum(f.getCost(), f.getEventNum());
		if(newReimbursement > 1000) {
			newReimbursement = 1000;
		}
		appdate.setReimbursement(newReimbursement);
		LogThis.LogIt("info", "Reimbursement for form: " + f.getFormID() + ", is " + newReimbursement);
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
	
	public void updateFinalDecision(int formID, boolean decision) {
		try {
			addao.updateFinalApproval(formID, decision);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String ifPending(int formID) {
		try {
			if(!addao.grabPending(formID)) {
				return "pending";
			} else if(man.getFinalApprovalInFinalTrue(formID)) { 
				return "approved";
			} else {
				return "rejected";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setInitialInFinal(int formID) {
		try {
			addao.updateInitalApprovalInFinal(formID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public void rejectDecision(int formID) {
//		try {
//			addao.rejectRequest(formID);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

}
