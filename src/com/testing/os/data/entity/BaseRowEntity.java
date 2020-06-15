package com.testing.os.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseRowEntity {
	@JsonIgnore
	private boolean ignore;

	@JsonIgnore
	private String apiType;

	@JsonIgnore
	private String runAs;

	@JsonIgnore
	private String tcId;

	@JsonIgnore
	private String desc;

	@JsonIgnore
	private String tcType;

	@JsonIgnore
	private String runStatus;

	@JsonIgnore
	private String date;

	@JsonIgnore
	private String msg;

	public boolean isIgnore() {
		return ignore;
	}

	public void setIgnore(boolean ignore) {
		this.ignore = ignore;
	}

	public String getApiType() {
		return apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}

	public String getRunAs() {
		return runAs;
	}

	public void setRunAs(String runAs) {
		this.runAs = runAs;
	}

	public String getTcId() {
		return tcId;
	}

	public void setTcId(String tcId) {
		this.tcId = tcId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTcType() {
		return tcType;
	}

	public void setTcType(String tcType) {
		this.tcType = tcType;
	}

	public String getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
