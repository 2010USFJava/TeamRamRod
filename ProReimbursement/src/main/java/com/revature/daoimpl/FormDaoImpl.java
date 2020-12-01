package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.revature.beans.Customer;
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
	
	private String url = "jdbc:postgresql://postgres.cyxh07df0zfy.us-west-2.rds.amazonaws.com:5432/postgres?currentSchema=reimbursement";
	private String username = "aquamiguel";
	private String password = "3tyme4be!";
	
	
	@Override
	public void createForm(Form f) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "insert into form values(default,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
			
		ps.setDate(1, f.getDate());
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
			f = new Form (rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4),
						rs.getString(5),rs.getInt(6),rs.getString(7),rs.getInt(8),rs.getString(9),
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

}
