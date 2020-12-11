package com.revature.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Form;
import com.revature.beans.Manager;
import com.revature.dao.FormDao;
import com.revature.dao.ManagerDao;
import com.revature.daoimpl.FormDaoImpl;
import com.revature.daoimpl.ManagerDaoImpl;

public class AdminService {
	ManagerDao addao = new ManagerDaoImpl();
	FormDao fdao = new FormDaoImpl();
	public static List<Integer>arrayFormDates = null;
	
	public AdminService() {}
	
	public boolean loginVerify(String email, String password) {
		List<Manager> aList = new ArrayList<Manager>();
		try {
			aList = addao.getAllAdmins();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean verify = false;
		for(Manager ad: aList) {
			if(ad.getEmail().equals(email) && ad.getPassword().equals(password)) {
				verify = true;
			}
		}
		return verify;
	}
	
	public Manager logGetAdmin(String email, String password) {
		if(loginVerify(email, password)) {
			try {
				return addao.getAdminByEmail(email);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void insertNewAdmin(Manager ad) {
		try {
			addao.createAdmin(ad);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addDepartment(int customerID, String department) {
		try {
			addao.insertDeptLookUp(customerID, department);
		} catch (SQLException e) {
			//e.printStackTrace();
		}
	}
	
	public List<Form> getAdminList(int employeeID) {
		List<Integer> fList = new ArrayList<Integer>();
		List<Integer> returnList = null;
		List<Integer> secondList = null;
		List<Form> allForms = new ArrayList<Form>();
		List<Integer> thirdList = new ArrayList<Integer>();
		List<Integer> fourthList = new ArrayList<Integer>();
		
		try {
			String title = addao.getTitle(employeeID);
			System.out.println("title: " + title);
		
			String dept = addao.getDepartment(employeeID);
			System.out.println("dept:" + dept);
			List<Integer> cList = addao.departmentListLookUp(dept);
			
			for(int cid: cList) {
				System.out.println("cid from dept_lookup: " + cid);
				fList = fdao.findAllFormIDsLookUp(cid);
				for(int f: fList) {
					thirdList.add(f);
				}
				System.out.println(thirdList);
				arrayFormDates = thirdList;
				
			}
			//System.out.println("form id list: " + fList);
			if(title.equals("direct supervisor") || title.equals("department head")) {
				for(int fid: thirdList) {
					System.out.println("fid: " + fid);
					returnList = addao.findBlankInApprovalDate(fid, title);
					for(int r: returnList) {
						fourthList.add(r);
					}
				}
			}
			
			if(title.equals("superhead")) {
				for(int fid: thirdList) {
					String dsTitle = "direct supervisor";
					returnList = addao.findBlankInApprovalDate(fid, dsTitle);
					for(int r: returnList) {
						fourthList.add(r);
					}
					String dhTitle = "department head";
					secondList = addao.findBlankInApprovalDate(fid, dhTitle);
					for(int r: secondList) {
						fourthList.add(r);
					}
				}
				for(int s: secondList) {
					fourthList.add(s);
				}
			}
			 
			for(int fid: fourthList) {
				System.out.println("all form num that match empty: " + fid);
				Form f = fdao.findFormByID(fid); 
				allForms.add(f);
			}
			System.out.println(allForms);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allForms;
	}

}
