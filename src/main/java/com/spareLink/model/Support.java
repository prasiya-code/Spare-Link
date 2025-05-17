package com.spareLink.model;

public class Support {

	private int supportId;
	private String issueType;
	private String fullName;
	private String email;
	private String contactNo;
	private String inquiry;
	
	
	public Support(int supportId, String issueType, String fullName, String email, String contactNo, String inquiry)
	{
		super();
		this.supportId = supportId;
		this.issueType = issueType;
		this.fullName = fullName;
		this.email = email;
		this.contactNo = contactNo;
		this.inquiry = inquiry;
	}


	public int getSupportId() {
		return supportId;
	}


	public void setSupportId(int supportId) {
		this.supportId = supportId;
	}


	public String getIssueType() {
		return issueType;
	}


	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContactNo() {
		return contactNo;
	}


	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	public String getInquiry() {
		return inquiry;
	}


	public void setInquiry(String inquiry) {
		this.inquiry = inquiry;
	}
	
	
	
}
