package com.zby.app.service.impl;

import java.util.List;

import com.zby.app.dao.UserDao;
import com.zby.app.entity.User;
import com.zby.app.service.UserService;

public class UserServiceImpl implements UserService{
	
	UserDao userDao = new UserDao();
	
	@Override
	public int addUser(User user) {
		if(userDao.selectUser(user.getUserName())==null) {
			user.setIdentity(0);//身份
			user.setPassword("123456");
			user.setGradeId(null);
			return userDao.insertUser(user);
		}else {
			return -1;//该账号已被注册
		}
	}

	@Override
	public int editUser(User user) {
		if (user.getGradeId()!=null) {
			return userDao.updateUserOfGrade(user);
		}else {
			return userDao.updateUser(user);
		}
		
	}

	@Override
	public User findUserInfo(String username) {
		// TODO Auto-generated method stub
		return userDao.selectUser(username);
	}

	@Override
	public List<User> findUserListInfo(Long gradeId) {

		return userDao.selectUserList(gradeId);
	}

	@Override
	public User login(String username, String password) {
		Boolean flag = false;
		User user = userDao.selectUser(username);
		if (user==null) {
			return null;
		}else {
			if(user.getPassword().equals(password)) {
				flag = true;
			}
			if (flag) {
				return user;
			}else {
				return null;
			}
		}
		
	}

	@Override
	public int changePassword(User user) {
		return userDao.updateUser(user);
	}
	
	
}
