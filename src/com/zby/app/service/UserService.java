package com.zby.app.service;

import java.util.List;

import com.zby.app.entity.User;

public interface UserService {
	
	//增加人员信息
	public int addUser(User user) ;
	
	//修改人员信息
	public int editUser(User user);
	
	//查询人员信息
	public User findUserInfo(String username);
	
	//查询班级内同学
	public List<User> findUserListInfo(Long gradeId);
	
	//登录
	public User login(String username,String password);
	
	//修改密码
	public int changePassword(User user);
}
