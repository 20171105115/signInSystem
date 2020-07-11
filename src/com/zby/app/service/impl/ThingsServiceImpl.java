package com.zby.app.service.impl;

import java.util.List;

import com.zby.app.dao.GradeDao;
import com.zby.app.dao.ThingsDao;
import com.zby.app.dao.UserDao;
import com.zby.app.entity.Things;
import com.zby.app.entity.User;
import com.zby.app.service.ThingsService;

public class ThingsServiceImpl implements ThingsService {

	ThingsDao thingsDao = new ThingsDao();
	UserDao userDao = new UserDao();
	GradeDao gradeDao = new GradeDao();
	
	@Override
	public int addThings(Things things) {//申请加入班级
		Things thingscopy = thingsDao.selectThings(things.getThingsCreateId());
		if (thingscopy!=null) {//如果该学生已有申请，则删除之前的
			thingsDao.deleteThings(thingscopy.getThingsId());
			return thingsDao.insertThings(things);
		}else {
			return thingsDao.insertThings(things);
		}
	}

	@Override//审批申请
	public int editThings(Things things) {
		if (things.getThingsStatus()==-1) {
			thingsDao.deleteThings(things.getThingsId());//删除申请
			return -1;//不通过返回-1
		}else {
			User user = userDao.selectUser(things.getThingsCreateId());//查询到这个人
			user.setGradeId(things.getThingsGradeId());//将他的班级信息更新
			userDao.updateUserOfGrade(user);
			return thingsDao.updateThings(things);
		}
	}

	@Override
	public List<Things> findThingsList(Long createId) {
		return thingsDao.selectThingsByGrade(createId);
	}

	
	
}
