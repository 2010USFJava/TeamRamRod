package com.revature.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Form;
import com.revature.controller.CusLoginController;
import com.revature.dao.FormDao;
import com.revature.daoimpl.FormDaoImpl;

public class FormService {
	FormDao form = new FormDaoImpl();
	
	public Form getForm(int formID) {
		System.out.println("inside getForm");
		try {
			return form.findFormByID(formID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertNewForm(Form f) {
		try {
			form.createForm(f);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertFormIdLookUp(Form f) {
		try {
			form.insertFormIdCustomerLookUp(f);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateOptional(String optional) {
		try {
			int customerID = CusLoginController.currentCustomer.getEmployeeID();
			List<Integer> fList = form.findAllFormIDsLookUp(customerID);
			int temp = 0;
			for(int i: fList) {
				if(i > temp) {
					temp = i;
				}
			}
			form.updateOptional(temp, optional);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateEmail(boolean email) {
		try {
			int customerID = CusLoginController.currentCustomer.getEmployeeID();
			List<Integer> fList = form.findAllFormIDsLookUp(customerID);
			int temp = 0;
			for(int i: fList) {
				if(i > temp) {
					temp = i;
				}
			}
			form.updateEmail(temp, email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Form> getAllForm(int customerID){
		List<Form> forms = new ArrayList<Form>();
		try {
			List<Integer> formIDs = form.findAllFormIDsLookUp(customerID);
			for(int id: formIDs) {
				forms.add(form.findFormByID(id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return forms;
	}

}
