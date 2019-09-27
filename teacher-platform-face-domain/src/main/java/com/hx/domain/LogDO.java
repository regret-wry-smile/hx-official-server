package com.hx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;

import java.io.Serializable;
import java.util.Date;

public class LogDO implements Serializable {

	private String tableName;

	private Long id;

	private String organization;

	private Integer userId;

	private String username;

	private String title;

	private String type;

	private String operation;

	private Integer time;

	private String http;

	private String method;

	private String params;

	private String ip;

	private String sys;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation == null ? null : operation.trim();
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method == null ? null : method.trim();
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params == null ? null : params.trim();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHttp() {
		return http;
	}

	public void setHttp(String http) {
		this.http = http;
	}

	public String getSys() {
		return sys;
	}

	public void setSys(String sys) {
		this.sys = sys;
	}

	@Override
	public String toString() {
		return "LogDO{" +
				"tableName='" + tableName + '\'' +
				", id=" + id +
				", organization='" + organization + '\'' +
				", userId=" + userId +
				", username='" + username + '\'' +
				", title='" + title + '\'' +
				", type='" + type + '\'' +
				", operation='" + operation + '\'' +
				", time=" + time +
				", http='" + http + '\'' +
				", method='" + method + '\'' +
				", params='" + params + '\'' +
				", ip='" + ip + '\'' +
				", sys='" + sys + '\'' +
				", gmtCreate=" + gmtCreate +
				'}';
	}
}