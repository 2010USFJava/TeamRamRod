package com.revature.service;

import java.sql.SQLException;

import com.revature.beans.ApprovalDates;
import com.revature.dao.ApprovalDatesDao;
import com.revature.daoimpl.ApprovalDatesDaoImpl;

public class ApprovalDatesService {
	ApprovalDatesDao addao = new ApprovalDatesDaoImpl();
	
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

}
