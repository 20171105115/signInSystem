package com.zby.app.entity;

import java.util.Date;

public class Check {
	private Long checkId;
	private Long checkGradeId;//发起班级
	private Date checkStartTime;//发起时间
	private Long checkCreateId;//发起人
	private int checkTime;//可签到时间，分钟
	private String checkPwd;//签到码
	private int checkStatus;//状态 0可签到 1已过期
	private Date checkEndTime;//签到截止时间
	
	
	public Date getCheckEndTime() {
		return checkEndTime;
	}
	public void setCheckEndTime(Date checkEndTime) {
		this.checkEndTime = checkEndTime;
	}
	public Long getCheckId() {
		return checkId;
	}
	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}
	public Long getCheckGradeId() {
		return checkGradeId;
	}
	public void setCheckGradeId(Long checkGradeId) {
		this.checkGradeId = checkGradeId;
	}
	public Date getCheckStartTime() {
		return checkStartTime;
	}
	public void setCheckStartTime(Date checkStartTime) {
		this.checkStartTime = checkStartTime;
	}
	public Long getCheckCreateId() {
		return checkCreateId;
	}
	public void setCheckCreateId(Long checkCreateId) {
		this.checkCreateId = checkCreateId;
	}
	public int getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(int checkTime) {
		this.checkTime = checkTime;
	}
	public String getCheckPwd() {
		return checkPwd;
	}
	public void setCheckPwd(String checkPwd) {
		this.checkPwd = checkPwd;
	}
	public int getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}

	
	
	
}
