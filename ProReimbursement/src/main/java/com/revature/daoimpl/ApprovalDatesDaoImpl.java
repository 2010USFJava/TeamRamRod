package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.ApprovalDates;
import com.revature.beans.Customer;
import com.revature.dao.ApprovalDatesDao;

public class ApprovalDatesDaoImpl implements ApprovalDatesDao {

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
	public void createApprovalDate(ApprovalDates ad) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "insert into approval_dates values(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, ad.getFormID());
		ps.setDate(2,(Date) ad.getDateEntered());
		ps.setDate(3, (Date) ad.getdSuperApproval());
		ps.setDate(4, (Date) ad.getdHeadApproval());
		ps.setDate(5,(Date) ad.getBenCoApproval());
		ps.setBoolean(6, ad.isApproved());
		ps.executeUpdate();		
	}

	@Override
	public ApprovalDates getApprovalDate(int formID) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select * from customer where customer_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, formID);
		ResultSet rs = ps.executeQuery();
		ApprovalDates ad = null;
		while(rs.next()) {
			ad = new ApprovalDates(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDate(4), rs.getDate(5), rs.getBoolean(6));
		}
		return ad;
	}

}
