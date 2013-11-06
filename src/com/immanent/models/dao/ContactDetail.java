package com.immanent.models.dao;

public class ContactDetail {
	
	private String relatedHandle;
	private String firstName;
	private String lastName;
	private String diasporaHandle;
	private String location;
	private String dob;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getRelatedHandle() {
		return relatedHandle;
	}
	public void setRelatedHandle(String relatedHandle) {
		this.relatedHandle = relatedHandle;
	}
	public String getDiasporaHandle() {
		return diasporaHandle;
	}
	public void setDiasporaHandle(String diasporaHandle) {
		this.diasporaHandle = diasporaHandle;
	}
	
}
