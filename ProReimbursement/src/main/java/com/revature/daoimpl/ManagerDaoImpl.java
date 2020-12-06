package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Manager;
import com.revature.dao.ManagerDao;
import com.revature.util.ConnFactory;

public class ManagerDaoImpl implements ManagerDao{
	static {
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ConnFactory cf = ConnFactory.getInstance();
	private String url= "jdbc:postgresql://java2010usf.cgdcd13do7zd.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=reimbursement";
	private String username = "sukanya";
	private String password = "sukanya14";
	
	@Override
	public void createAdmin(Manager a) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "insert into manager values(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, a.getEmployee_ID());
		ps.setString(2, a.getfName());
		ps.setString(3, a.getlName());
		ps.setString(4, a.getEmail());
		ps.setString(5, a.getPassword());
		ps.setString(6, a.getTitle());
		ps.executeUpdate();	
	}

	@Override
	public Manager findAdminById(int employeeID) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select * from manager where employee_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, employeeID);
		ResultSet rs = ps.executeQuery();
		Manager a = null;
		while(rs.next()) {
			a = new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
		}
		return a;
	}

	@Override
	public Manager getAdminByEmail(String email) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select * from manager where email=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		Manager a = null;
		while(rs.next()) {
			a = new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
		}
		return a;
	}

	@Override
	public List<Manager> getAllAdmins() throws SQLException {
		List<Manager> aList = new ArrayList<Manager>();
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select * from manager";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			aList.add(new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
		}
		return aList;
	}

	@Override
	public void insertDeptLookUp(int customerID, String department) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "insert into dept_lookup values(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, customerID);
		ps.setString(2, department);
		ps.executeUpdate();	
		
	}

}
