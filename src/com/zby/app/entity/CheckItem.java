package com.zby.app.entity;

import java.util.Date;

public class CheckItem {

	private Long checkItemId;
	private Long checkId;
	private Long checkSolveId;//签到人
	private Date checkEndTime;//签到时间
	private int checkItemStatus;//0 未签到 1 已签到 
	
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Long getCheckItemId() {
		return checkItemId;
	}
	public void setCheckItemId(Long checkItemId) {
		this.checkItemId = checkItemId;
	}
	public Long getCheckId() {
		return checkId;
	}
	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}
	public Long getCheckSolveId() {
		return checkSolveId;
	}
	public void setCheckSolveId(Long checkSolveId) {
		this.checkSolveId = checkSolveId;
	}
	public Date getCheckEndTime() {
		return checkEndTime;
	}
	public void setCheckEndTime(Date checkEndTime) {
		this.checkEndTime = checkEndTime;
	}
	public int getCheckItemStatus() {
		return checkItemStatus;
	}
	public void setCheckItemStatus(int checkItemStatus) {
		this.checkItemStatus = checkItemStatus;
	}
	
	
	
}
