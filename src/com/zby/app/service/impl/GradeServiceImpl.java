package com.zby.app.service.impl;

import java.util.Date;
import java.util.List;

import com.zby.app.dao.GradeDao;
import com.zby.app.dao.UserDao;
import com.zby.app.entity.Grade;
import com.zby.app.entity.User;
import com.zby.app.service.GradeService;

public class GradeServiceImpl implements GradeService{

	GradeDao gradeDao = new GradeDao();
	UserDao userDao = new UserDao();
	
	@Override
	public int addGrade(Grade grade,String username) {//创建班级，创建后该班级成为教师默认班级
		int i = gradeDao.insertGrade(grade);
		Grade test = gradeDao.selectGrade(grade.getGradeName());//查询刚创建班级的ID
		User user = userDao.selectUser(username);
		user.setGradeId(test.getGradeId());
		userDao.updateUserOfGrade(user);//更新个人信息
		return i;
	}

	@Override
	public int editGrade(Grade grade) {
		// TODO Auto-generated method stub
		grade.setLastEditTime(new Date());
		return gradeDao.updateGrade(grade);
	}

	@Override
	public List<Grade> findGradeList(Long createId) {
		// TODO Auto-generated method stub
		return gradeDao.selectGradeList(createId);
	}

	@Override
	public Grade findGrade(String gradeName) {
		// TODO Auto-generated method stub
		return gradeDao.selectGrade(gradeName);
	}

	@Override
	public List<Grade> findAllGrade() {
		// TODO Auto-generated method stub
		return gradeDao.selectAllGrade();
	}

}
