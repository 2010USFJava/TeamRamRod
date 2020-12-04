package com.revature.beans;

import java.time.LocalDate;
import java.util.Date;

public class ApprovalDates {
	private int formID;
	private LocalDate dateEntered;
	private LocalDate dSuperApproval;
	private LocalDate dHeadApproval;
	private LocalDate benCoApproval;
	private boolean isApproved;
	
	public ApprovalDates() {
		super();
	}

	public ApprovalDates(int formID, LocalDate dateEntered, LocalDate dSuperApproval, LocalDate dHeadApproval, LocalDate benCoApproval,
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

	public LocalDate getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(LocalDate dateEntered) {
		this.dateEntered = dateEntered;
	}

	public LocalDate getdSuperApproval() {
		return dSuperApproval;
	}

	public void setdSuperApproval(LocalDate dSuperApproval) {
		this.dSuperApproval = dSuperApproval;
	}

	public LocalDate getdHeadApproval() {
		return dHeadApproval;
	}

	public void setdHeadApproval(LocalDate dHeadApproval) {
		this.dHeadApproval = dHeadApproval;
	}

	public LocalDate getBenCoApproval() {
		return benCoApproval;
	}

	public void setBenCoApproval(LocalDate benCoApproval) {
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
