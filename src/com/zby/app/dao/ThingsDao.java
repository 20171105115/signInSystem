package com.zby.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zby.app.entity.Grade;
import com.zby.app.entity.Things;
import com.zby.app.entity.User;
import com.zby.app.util.JDBCUtil;

public class ThingsDao {
	
public int insertThings(Things things) {
		String sql = "insert into things(things_name,things_desc,things_create_id,things_grade_id,things_status)"
				+ "values(?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		int i = 0;
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, things.getThingsName());
			ps.setString(2, things.getThingsDesc());
			ps.setLong(3, things.getThingsCreateId());
			ps.setLong(4, things.getThingsGradeId());
			ps.setInt(5, 0);//0申请中 1申请通过 -1申请失败
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(null, ps, conn);
		}
		return i;
	}
	
	public Things selectThings(Long createId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Things things = null;
		String sql = "select * from things where things_create_id=?";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, createId);
			rs = ps.executeQuery();
			if(rs.next()) {
				things = new Things();
				things.setThingsId(rs.getLong("things_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, ps, conn);
		}
		return things;
	}
	
	public List<Things> selectThingsByGrade(Long createId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Things> thingsList = new ArrayList<Things>();
		String sql = "select things.things_id,things.things_name,things.things_grade_id,things.things_desc,grade.grade_name,things.things_create_id,user.username" + 
				" from things,grade,user" + 
				" where grade.create_id=user.user_id and things.things_grade_id=grade.grade_id and user.user_id=? and things.things_status=0;";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, createId);
			rs = ps.executeQuery();
			while(rs.next()) {
				Things things = new Things();
				things.setThingsId(rs.getLong("things_id"));
				things.setThingsName(rs.getString("things_name"));
				things.setThingsDesc(rs.getString("things_desc"));
				things.setThingsCreateId(rs.getLong("things_create_id"));
				things.setThingsGradeId(rs.getLong("things_grade_id"));
				Grade grade = new Grade();
				grade.setGradeName(rs.getString("grade.grade_name"));
				things.setGrade(grade);
				User user = new User();
				user.setUserName(rs.getString("user.username"));
				things.setCreateUser(user);
				thingsList.add(things);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, ps, conn);
		}
		return thingsList;
	}
	
	public int deleteThings(Long thingsId) {
		String sql = "delete from things where things_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		int i = 0;
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, thingsId);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(null, ps, conn);
		}
		return i;
	}
	
	public int updateThings(Things things) {
		String sql = "update things set things_solve_id=?,things_status=?"
				+ " where things_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		int i = 0;
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, things.getThingsSolveId());
			ps.setInt(2, things.getThingsStatus());
			ps.setLong(3, things.getThingsId());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(null, ps, conn);
		}
		return i;
	}
	
//	public static void main(String[] args) {
//		Things things = new Things();
//		things.setThingsId(1L);
//		things.setThingsSolveId(1L);
//		things.setThingsStatus(1);
//		ThingsDao thingsDao = new ThingsDao();
//		System.out.println(thingsDao.updateThings(things));
//	}
}
