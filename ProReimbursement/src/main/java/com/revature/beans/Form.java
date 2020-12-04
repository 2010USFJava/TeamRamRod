package com.revature.beans;

import java.time.LocalDate;

public class Form {

	private int formID;
	private LocalDate date;
	private String time;
	private String location;
	private String description;
	private double cost;
	private String gradeFormat;
	private int eventNum;
	private String justification;
	private String dialogBox;
	private boolean isAttached;
	
	public Form() {
		super();
	}
	public Form(int formID, LocalDate date, String time, String location, String description, double cost, String gradeFormat, int eventNum,
			String justification, String dialogBox, boolean isAttached) {
		super();
		this.date = date;
		this.time = time;
		this.formID = formID;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gradeFormat = gradeFormat;
		this.eventNum = eventNum;
		this.justification = justification;
		this.dialogBox = dialogBox;
		this.isAttached = isAttached;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getFormID() {
		return formID;
	}
	public void setFormID(int formID) {
		this.formID = formID;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getGradeFormat() {
		return gradeFormat;
	}
	public void setGradeFormat(String gradeFormat) {
		this.gradeFormat = gradeFormat;
	}
	public int getEventNum() {
		return eventNum;
	}
	public void setEventNum(int eventNum) {
		this.eventNum = eventNum;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public String getDialogBox() {
		return dialogBox;
	}
	public void setDialogBox(String dialogBox) {
		this.dialogBox = dialogBox;
	}
	public boolean isAttached() {
		return isAttached;
	}
	public void setAttached(boolean isAttached) {
		this.isAttached = isAttached;
	}
	@Override
	public String toString() {
		return "Form [formID=" + formID + ", date=" + date + ", time=" + time + ", location=" + location
				+ ", description=" + description + ", cost=" + cost + ", gradeFormat=" + gradeFormat + ", eventNum="
				+ eventNum + ", justification=" + justification + ", dialogBox=" + dialogBox + ", isAttached="
				+ isAttached + "]";
	}
	
	
	
	
}
