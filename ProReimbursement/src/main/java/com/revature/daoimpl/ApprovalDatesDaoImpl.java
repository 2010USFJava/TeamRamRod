package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

import com.revature.beans.ApprovalDates;
import com.revature.dao.ApprovalDatesDao;

public class ApprovalDatesDaoImpl implements ApprovalDatesDao {
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
	
	private String url= "jdbc:postgresql://java2010usf.cgdcd13do7zd.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=reimbursement";
	private String username = "sukanya";
	private String password = "sukanya14";
	
	@Override
	public void createApprovalDate(ApprovalDates ad) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "insert into approval_dates values(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, ad.getFormID());
		ps.setObject(2,todayLocalDate);
		ps.setObject(3, ad.getdSuperApproval());
		ps.setObject(4, ad.getdHeadApproval());
		ps.setObject(5, ad.getBenCoApproval());
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
			ad = new ApprovalDates(rs.getInt(1), rs.getObject(2, LocalDate.class), rs.getObject(3, LocalDate.class), rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class), rs.getBoolean(6));
		}
		return ad;
	}

	@Override
	public void updateApprovalDate(int formID) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "update approval_dates set benco_approval_date=? where form_id=?";
		PreparedStatement ps = conn.prepareStatement(sql); 
		ps.setObject(1, todayLocalDate);
		ps.setInt(2, formID); 
		ps.executeUpdate();	
		
	}

}
