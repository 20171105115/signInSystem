package com.zby.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zby.app.entity.CheckItem;
import com.zby.app.entity.User;
import com.zby.app.util.JDBCUtil;

public class CheckItemDao {
	public int insertCheckItem(CheckItem checkItem,Long checkId) {
		String sql = "insert into check_item(check_solve_id,check_id,check_end_time,check_item_status)"
				+ "values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		int i = 0;
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, checkItem.getCheckSolveId());
			ps.setLong(2, checkId);
			ps.setTimestamp(3, new java.sql.Timestamp(checkItem.getCheckEndTime().getTime()));
			ps.setInt(4, 1);//1已经签到
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(null, ps, conn);
		}
		return i;
	}
	
	public List<CheckItem> selectCheckList(Long checkId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CheckItem> checkItemList = new ArrayList<CheckItem>();
		String sql = "select user.name,user.gender,check_item.check_end_time from user,check_item"
				+ " where check_item.check_id=? and user.user_id=check_item.check_solve_id";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, checkId);
			rs = ps.executeQuery();
			while(rs.next()) {
				CheckItem checkItem = new CheckItem();
				User user = new User();
				user.setName(rs.getString("user.name"));
				user.setGender(rs.getInt("user.gender"));
				checkItem.setUser(user);
				checkItem.setCheckEndTime(rs.getTimestamp("check_item.check_end_time"));
				checkItemList.add(checkItem);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, ps, conn);
		}
		return checkItemList;
	}
}
