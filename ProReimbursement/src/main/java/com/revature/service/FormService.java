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
		System.out.println("inside get form NULL");
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
	
	public void updateOptionalForm(String optional) {
		try {
			form.updateOptional(CusLoginController.currentForm.getFormID(), optional);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateEmailForm(boolean email) {
		try {
			form.updateEmail(CusLoginController.currentForm.getFormID(), email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Form> getAllForm(int customerID){
		List<Form> forms = new ArrayList<Form>();
		try {
			List<Integer> formIDs = form.findAllFormIDsLookUp(customerID);
			System.out.println(formIDs);
			for(int id: formIDs) {
				forms.add(form.findFormByID(id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(forms);
		
		return forms;
	}
	
	public List<String> formDateStrings(List<Integer> formIds){
		List<String> returnDates = new ArrayList<String>();
	
		try {
			for(int fid: formIds) {
				returnDates.add(form.getDate(fid).toString());
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return returnDates;
	}
	
	public List<String> getEmailAndOptional(int formID){
		List<String> eoList = new ArrayList<String>();
		try {
			eoList = form.getEmailAndOptional(formID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("email and optional list: "+ eoList);
		return eoList;
	}
}
