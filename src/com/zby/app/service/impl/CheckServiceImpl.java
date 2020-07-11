package com.zby.app.service.impl;

import java.util.List;

import com.zby.app.dao.CheckDao;
import com.zby.app.dao.CheckItemDao;
import com.zby.app.entity.Check;
import com.zby.app.entity.CheckItem;
import com.zby.app.service.CheckServlice;

public class CheckServiceImpl implements CheckServlice{

	CheckDao checkDao = new CheckDao();
	CheckItemDao checkItemDao = new CheckItemDao();
	
	@Override
	public int addCheck(Check check) {
		return checkDao.insertCheck(check);
	}

	@Override
	public List<Check> findCheckList(Long createId) {
		return checkDao.selectCheckList(createId);
	}

	@Override
	public int toCheck(CheckItem checkItem, Long checkId) {
		return checkItemDao.insertCheckItem(checkItem, checkId);
	}

	@Override
	public List<CheckItem> findcheckItemList(Long checkId) {
		return checkItemDao.selectCheckList(checkId);
	}

	@Override
	public Check selectCheck(Long createId) {
		// TODO Auto-generated method stub
		return checkDao.selectCheck(createId);
	}

	@Override
	public Check selectCheckOfStudent(Long gradeId) {
		// TODO Auto-generated method stub
		return checkDao.selectCheckOfStudent(gradeId);
	}

}
