package com.revature.beans;

public class Form {

	private int formID;
	// private date
	// time
	private String location;
	private String description;
	private double cost;
	private String gradeFormat;
	private String eventType;
	private String justification;
	private String dialogBox;
	private boolean isAttached;
	
	public Form() {
		super();
	}
	public Form(int formID, String location, String description, double cost, String gradeFormat, String eventType,
			String justification, String dialogBox, boolean isAttached) {
		super();
		this.formID = formID;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gradeFormat = gradeFormat;
		this.eventType = eventType;
		this.justification = justification;
		this.dialogBox = dialogBox;
		this.isAttached = isAttached;
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
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
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
		return "Form [formID=" + formID + ", location=" + location + ", description=" + description + ", cost=" + cost
				+ ", gradeFormat=" + gradeFormat + ", eventType=" + eventType + ", justification=" + justification
				+ ", dialogBox=" + dialogBox + ", isAttached=" + isAttached + "]";
	}
	
	
}
