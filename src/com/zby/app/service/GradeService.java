package com.zby.app.service;

import java.util.List;

import com.zby.app.entity.Grade;

public interface GradeService {
	//新建班级
	public int addGrade(Grade grade,String username);
	//更新班级
	public int editGrade(Grade grade);
	//查看所建立班级
	public List<Grade> findGradeList(Long createId);
	//查询单个班级信息
	public Grade findGrade(String gradeName);
	//查询班级列表
	public List<Grade> findAllGrade();
}
