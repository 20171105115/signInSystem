package com.zby.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;



import com.zby.app.entity.User;
import com.zby.app.util.JDBCUtil;

public class UserDao {

	
	public int insertUser(User user) {
		String sql = "insert into user(username,password,name,birthday,gender,identity,user_desc)"
				+ "values(?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		int i = 0;
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setDate(4, new java.sql.Date(user.getBirthday().getTime()));
			ps.setInt(5, user.getGender());
			ps.setInt(6, user.getIdentity());
			ps.setString(7, user.getUserDesc());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(null, ps, conn);
		}
		return i;
	}

	public User selectUser(String username) {//因为要查询这个人之前是否注册，所以用username检查
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		String sql = "select * from user where username=?";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getLong("user_id"));
				user.setGender(rs.getInt("gender"));
				user.setGradeId(rs.getLong("grade_id"));
				user.setBirthday(rs.getDate("birthday"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setUserDesc(rs.getString("user_desc"));
				user.setIdentity(rs.getInt("identity"));
				user.setUserName(rs.getString("username"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, ps, conn);
		}
		return user;
	}
	
	public User selectUser(Long userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		String sql = "select * from user where user_id=?";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getLong("user_id"));
				user.setGender(rs.getInt("gender"));
				user.setGradeId(rs.getLong("grade_id"));
				user.setBirthday(rs.getDate("birthday"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setUserDesc(rs.getString("user_desc"));
				user.setIdentity(rs.getInt("identity"));
				user.setUserName(rs.getString("username"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, ps, conn);
		}
		return user;
	}
	
	public int updateUser(User user) {//更新个人信息
		String sql = "update user set password=?,name=?,birthday=?,gender=?,user_desc=?"
				+ "where username=?";
		Connection conn = null;
		PreparedStatement ps = null;
		int i = 0;
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getName());
			ps.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
			ps.setInt(4, user.getGender());
			ps.setString(5, user.getUserDesc());
			ps.setString(6, user.getUserName());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(null, ps, conn);
		}
		return i;
	}
	
	public int updateUserOfGrade(User user) {//更新个人信息中的班级信息
		String sql = "update user set grade_id=? "
				+ "where username=?";
		Connection conn = null;
		PreparedStatement ps = null;
		int i = 0;
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, user.getGradeId());
			ps.setString(2, user.getUserName());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(null, ps, conn);
		}
		return i;
	}
	
	public List<User> selectUserList(Long gradeId) {//查询同学列表
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();
		String sql = "select user_id,username,name,gender from user where identity=0 and grade_id=?";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, gradeId);
			rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUserId(rs.getLong("user_id"));
				user.setUserName(rs.getString("username"));
				user.setGender(rs.getInt("gender"));
				user.setName(rs.getString("name"));
				userList.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, ps, conn);
		}
		return userList;
	}
}
