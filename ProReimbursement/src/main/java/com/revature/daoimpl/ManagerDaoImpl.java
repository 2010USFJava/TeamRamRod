package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Manager;
import com.revature.dao.ManagerDao;
import com.revature.util.ConnFactory;

public class ManagerDaoImpl implements ManagerDao{
	public static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public void createManager(Manager m) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "insert into manager values(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, m.getEmployee_ID());
		ps.setString(2, m.getfName());
		ps.setString(3, m.getlName());
		ps.setString(4, m.getEmail());
		ps.setString(5, m.getPassword());
		ps.setString(6, m.getTitle());
		ps.executeUpdate();	
	}

	@Override
	public Manager findManagerById(int employeeID) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "select * from manager where employee_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, employeeID);
		ResultSet rs = ps.executeQuery();
		Manager m = null;
		while(rs.next()) {
			m = new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
		}
		return m;
	}

}
