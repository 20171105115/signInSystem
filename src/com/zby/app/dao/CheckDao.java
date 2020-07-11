package com.zby.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zby.app.entity.Check;

import com.zby.app.util.JDBCUtil;

public class CheckDao {
	
	public int insertCheck(Check check) {
		String sql = "insert into check1(check_grade_id,check_start_time,check_create_id,check_time,check_status,check_end_time,check_pwd)"
				+ "values(?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		int i = 0;
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, check.getCheckGradeId());
			ps.setTimestamp(2, new java.sql.Timestamp(check.getCheckStartTime().getTime()) );
			ps.setLong(3, check.getCheckCreateId());
			ps.setInt(4, check.getCheckTime());
			ps.setInt(5, check.getCheckStatus());
			ps.setTimestamp(6, new java.sql.Timestamp(check.getCheckEndTime().getTime()));
			ps.setString(7, check.getCheckPwd());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(null, ps, conn);
		}
		return i;
	}
	
	public List<Check> selectCheckList(Long createId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Check> checkList = new ArrayList<Check>();
		String sql = "select check_id,check_start_time,check_end_time,check_pwd,check_status from check1 where check_create_id=? "
				+ "order by check_start_time DESC";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, createId);
			rs = ps.executeQuery();
			while(rs.next()) {
				Check check = new Check();
				check.setCheckId(rs.getLong("check_id"));
				check.setCheckStartTime(rs.getTimestamp("check_start_time"));
				check.setCheckEndTime(rs.getTimestamp("check_end_time"));
				check.setCheckPwd(rs.getString("check_pwd"));
				check.setCheckStatus(0);//签到进行中
				checkList.add(check);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, ps, conn);
		}
		return checkList;
	}
	
	public Check selectCheck(Long createId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Check check=null;
		String sql = "select * from check1 where check_create_id=? order by check_start_time DESC";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, createId);
			rs = ps.executeQuery();
			if(rs.next()) {
				check = new Check();
				check.setCheckId(rs.getLong("check_id"));
				check.setCheckStartTime(rs.getTimestamp("check_start_time"));
				check.setCheckEndTime(rs.getTimestamp("check_end_time"));
				check.setCheckPwd(rs.getString("check_pwd"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, ps, conn);
		}
		return check;
	}
	
	public Check selectCheckOfStudent(Long gradeId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Check check=null;
		String sql = "select * from check1 where check_grade_id=? order by check_id DESC limit 1";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, gradeId);
			rs = ps.executeQuery();
			if(rs.next()) {
				check = new Check();
				check.setCheckId(rs.getLong("check_id"));
				check.setCheckStartTime(rs.getTimestamp("check_start_time"));
				check.setCheckEndTime(rs.getTimestamp("check_end_time"));
				check.setCheckStatus(rs.getInt("check_status"));
				check.setCheckPwd(rs.getString("check_pwd"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, ps, conn);
		}
		return check;
	}
	
	public int updateCheck(Long checkId) {
		String sql = "update check1 set check_status=1"
				+ " where check_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		int i = 0;
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, checkId);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(null, ps, conn);
		}
		return i;
	}

}
