package com.zby.app.entity;

public class Things {
	private Long thingsId;
	
	private String thingsName;
	
	private String thingsDesc;
	
	private Long thingsCreateId;//申请者
	
	private Long thingsSolveId;//解决者
	
	private int thingsStatus;//处理状态 0待处理 1已处理
	
	private Long thingsGradeId;//申请班级
	
	private User createUser;//申请者
	
	private Grade grade;//申请班级
	

	public Long getThingsId() {
		return thingsId;
	}

	public void setThingsId(Long thingsId) {
		this.thingsId = thingsId;
	}

	public String getThingsName() {
		return thingsName;
	}

	public void setThingsName(String thingsName) {
		this.thingsName = thingsName;
	}

	public String getThingsDesc() {
		return thingsDesc;
	}

	public void setThingsDesc(String thingsDesc) {
		this.thingsDesc = thingsDesc;
	}

	public Long getThingsCreateId() {
		return thingsCreateId;
	}

	public void setThingsCreateId(Long thingsCreateId) {
		this.thingsCreateId = thingsCreateId;
	}

	public Long getThingsSolveId() {
		return thingsSolveId;
	}

	public void setThingsSolveId(Long thingsSolveId) {
		this.thingsSolveId = thingsSolveId;
	}

	public int getThingsStatus() {
		return thingsStatus;
	}

	public void setThingsStatus(int thingsStatus) {
		this.thingsStatus = thingsStatus;
	}

	public Long getThingsGradeId() {
		return thingsGradeId;
	}

	public void setThingsGradeId(Long thingsGradeId) {
		this.thingsGradeId = thingsGradeId;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}


	
	
}
