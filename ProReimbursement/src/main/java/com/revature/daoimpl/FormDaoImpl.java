package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Form;
import com.revature.controller.CusLoginController;
import com.revature.dao.FormDao;

public class FormDaoImpl implements FormDao {
	static {
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private String url= "jdbc:postgresql://java2010usf.cgdcd13do7zd.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=reimbursement";
	private String username = "sukanya";
	private String password = "sukanya14";
	
	
	@Override
	public void createForm(Form f) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "insert into form values(default,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
			
		ps.setObject(1, f.getDate());
		ps.setString(2, f.getTime());
		ps.setString(3,f.getLocation());
		ps.setString(4, f.getDescription());
		ps.setDouble(5, f.getCost());
		ps.setString(6, f.getGradeFormat());
		ps.setInt(7, f.getEventNum());
		ps.setString(8, f.getJustification());
		ps.setBoolean(9, f.isAttached());
		ps.executeUpdate();		
	}
 
	@Override
	public Form findFormByID(int formID) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select * from form where form_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, formID);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		while(rs.next()) {
			f = new Form (rs.getInt(1), rs.getObject(2, LocalDate.class), rs.getString(3), rs.getString(4),
						rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getInt(8),rs.getString(9),
						rs.getString(10),rs.getBoolean(11));
		}
		return f;
	}
	
	@Override
	public void insertFormIdCustomerLookUp(Form f) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "update customer_lookup set form_num=? where customer_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, f.getFormID());
		ps.setInt(2, CusLoginController.currentCustomer.getEmployeeID());
		ps.executeUpdate();		
	}

	@Override
	public List<Integer> findAllFormIDsLookUp(int customerID) throws SQLException {
		List<Integer> fList = new ArrayList<Integer>();
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select form_num from customer_lookup where customer_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, customerID);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			fList.add(rs.getInt(1));
		}
		return fList;
	}
	
	public int findCustomerIDByFormIDLookUp(int formID) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select customer_id from customer_lookup where form_num=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, formID);
		ResultSet rs = ps.executeQuery();
		int cusNum = 0;
		while(rs.next()) {
			cusNum = rs.getInt(1);
		}
		return cusNum;
	}

	@Override
	public void updateOptional(int formID, String optional) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "update form set optional=? where form_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, optional);
		ps.setInt(2, formID);
		ps.executeUpdate();	
		
	}

	@Override
	public void updateEmail(int formID, boolean email) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "update form set has_email=? where form_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setBoolean(1, email);
		ps.setInt(2, formID);
		ps.executeUpdate();	
		
	}

//	@Override
//	public List<Form> findAllForms(int customerID) throws SQLException {
//		List<Form> fList = new ArrayList<Form>();
//		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
//		List<Integer> formIDs = findAllFormIDsLookUp(customerID);
//		String sql = "select * from form where form_id=?";
//		PreparedStatement ps = conn.prepareStatement(sql);
//		ps.setInt(1, customerID);
//		ResultSet rs = ps.executeQuery();
//		while(rs.next()) {
//			fList.add(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
//		}
//		return fList;
//		
//	}

}
