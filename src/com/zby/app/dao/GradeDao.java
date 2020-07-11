package com.zby.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import com.zby.app.entity.Grade;

import com.zby.app.util.JDBCUtil;

public class GradeDao {
	
	public int insertGrade(Grade grade) {
		String sql = "insert into grade(grade_name,grade_desc,create_time,create_id)"
				+ "values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		int i = 0;
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, grade.getGradeName());
			ps.setString(2, grade.getGradeDesc());
			ps.setDate(3, new java.sql.Date(grade.getCreateTime().getTime()));
			ps.setLong(4, grade.getCreateId());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(null, ps, conn);
		}
		return i;
	}
	
	public int updateGrade(Grade grade) {
		String sql = "update grade set grade_name=?,grade_desc=?,last_edit_time=?"
				+ "where grade_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		int i = 0;
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, grade.getGradeName());
			ps.setString(2, grade.getGradeDesc());
			ps.setDate(3, new java.sql.Date(grade.getLastEditTime().getTime()));
			ps.setLong(4, grade.getGradeId());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(null, ps, conn);
		}
		return i;
	}
	
	public List<Grade> selectGradeList(Long createId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Grade> gradeList = new ArrayList<Grade>();
		String sql = "select grade_name,create_time,grade_id from grade where create_id=?";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, createId);
			rs = ps.executeQuery();
			while(rs.next()) {
				Grade grade = new Grade();
				grade.setGradeId(rs.getLong("grade_id"));
				grade.setGradeName(rs.getString("grade_name"));
				grade.setCreateTime(rs.getDate("create_time"));
				gradeList.add(grade);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, ps, conn);
		}
		return gradeList;
	}
	
	public List<Grade> selectAllGrade() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Grade> gradeList = new ArrayList<Grade>();
		String sql = "select grade_id,grade_name,create_time from grade";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Grade grade = new Grade();
				grade.setGradeId(rs.getLong("grade_id"));
				grade.setGradeName(rs.getString("grade_name"));
				grade.setCreateTime(rs.getDate("create_time"));
				gradeList.add(grade);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, ps, conn);
		}
		return gradeList;
	}
	
	public Grade selectGrade(String gradeName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Grade grade = null;
		String sql = "select * from grade where grade_name=?";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, gradeName);
			rs = ps.executeQuery();
			if(rs.next()) {
				grade = new Grade();
				grade.setGradeName(rs.getString("grade_name"));
				grade.setCreateTime(rs.getDate("create_time"));
				grade.setGradeDesc(rs.getString("grade_desc"));
				grade.setCreateId(rs.getLong("create_id"));
				grade.setLastEditTime(rs.getDate("last_edit_time"));
				grade.setGradeId(rs.getLong("grade_id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, ps, conn);
		}
		return grade;
	}
	
//	public static void main(String[] args) {
//		Grade grade = new Grade();
//		GradeDao gradeDao = new GradeDao();
//		grade.setGradeName("软件");
//		grade.setGrageDesc("...");
//		grade.setLastEditTime(new Date());
//		grade.setGradeId(1L);
//		System.out.println(gradeDao.updateGrade(grade));
//	}
	
//	public static void main(String[] args) {
//		GradeDao gradeDao = new GradeDao();
//		System.out.println(gradeDao.selectGrade("软件"));
//	}

}
