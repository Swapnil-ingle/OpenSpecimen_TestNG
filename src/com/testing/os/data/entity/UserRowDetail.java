package com.testing.os.data.entity;

import java.util.Date;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.os.data.util.Utility;

public class UserRowDetail extends BaseRowEntity {
	private Long id;

	private String firstName;

	private String lastName;

	private String emailAddress;

	private String domainName;

	private String loginName;

	private Long instituteId;

	private String instituteName;

	private String primarySite;

	private String type;

	private String phoneNumber;

	private Boolean manageForms;

	private Boolean dnd;

	private String address;

	private String timeZone;

	private Date creationDate;

	private String activityStatus;

	public UserRowDetail() {
	}

	public UserRowDetail(String jsonString) {
		JSONObject object = Utility.getJsonObjectFromString(jsonString);

		this.setId(Utility.getLongAttrFromJSON(object, "id"));
		this.setFirstName(Utility.getStringAttrFromJSON(object, "firstName"));
		this.setLastName(Utility.getStringAttrFromJSON(object, "lastName"));
		this.setEmailAddress(Utility.getStringAttrFromJSON(object, "emailAddress"));
		this.setDomainName(Utility.getStringAttrFromJSON(object, "domainName"));
		this.setLoginName(Utility.getStringAttrFromJSON(object, "loginName"));
		this.setInstituteName(Utility.getStringAttrFromJSON(object, "instituteName"));
		this.setPrimarySite(Utility.getStringAttrFromJSON(object, "primarySite"));
		this.setType(Utility.getStringAttrFromJSON(object, "type"));
		this.setPhoneNumber(Utility.getStringAttrFromJSON(object, "phoneNumber"));
		this.setManageForms(Utility.getBooleanAttrFromJSON(object, "managerForms"));
		this.setDnd(Utility.getBooleanAttrFromJSON(object, "dnd"));
		this.setAddress(Utility.getStringAttrFromJSON(object, "address"));
		this.setTimeZone(Utility.getStringAttrFromJSON(object, "timeZone"));
		this.setCreationDate(new Date(Utility.getLongAttrFromJSON(object, "creationDate")));
		this.setActivityStatus(Utility.getStringAttrFromJSON(object, "activityStatus"));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Long getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(Long instituteId) {
		this.instituteId = instituteId;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getPrimarySite() {
		return primarySite;
	}

	public void setPrimarySite(String primarySite) {
		this.primarySite = primarySite;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean isManageForms() {
		return manageForms;
	}

	public void setManageForms(Boolean manageForms) {
		this.manageForms = manageForms;
	}

	public Boolean isDnd() {
		return dnd;
	}

	public void setDnd(boolean dnd) {
		this.dnd = dnd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	public String toJsonString() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(this);
	}
}
