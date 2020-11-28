package com.revature.beans;

import java.util.Date;

public class ApprovalDates {
	private int formID;
	private Date dateEntered;
	private Date dSuperApproval;
	private Date dHeadApproval;
	private Date benCoApproval;
	private boolean isApproved;
	
	public ApprovalDates() {
		super();
	}

	public ApprovalDates(int formID, Date dateEntered, Date dSuperApproval, Date dHeadApproval, Date benCoApproval,
			boolean isApproved) {
		super();
		this.formID = formID;
		this.dateEntered = dateEntered;
		this.dSuperApproval = dSuperApproval;
		this.dHeadApproval = dHeadApproval;
		this.benCoApproval = benCoApproval;
		this.isApproved = isApproved;
	}

	public int getFormID() {
		return formID;
	}

	public void setFormID(int formID) {
		this.formID = formID;
	}

	public Date getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public Date getdSuperApproval() {
		return dSuperApproval;
	}

	public void setdSuperApproval(Date dSuperApproval) {
		this.dSuperApproval = dSuperApproval;
	}

	public Date getdHeadApproval() {
		return dHeadApproval;
	}

	public void setdHeadApproval(Date dHeadApproval) {
		this.dHeadApproval = dHeadApproval;
	}

	public Date getBenCoApproval() {
		return benCoApproval;
	}

	public void setBenCoApproval(Date benCoApproval) {
		this.benCoApproval = benCoApproval;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	@Override
	public String toString() {
		return "ApprovalDates [formID=" + formID + ", dateEntered=" + dateEntered + ", dSuperApproval=" + dSuperApproval
				+ ", dHeadApproval=" + dHeadApproval + ", benCoApproval=" + benCoApproval + ", isApproved=" + isApproved
				+ "]";
	}
}
