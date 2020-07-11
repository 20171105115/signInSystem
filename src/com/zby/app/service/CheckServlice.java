package com.zby.app.service;
import java.util.List;

import com.zby.app.entity.Check;
import com.zby.app.entity.CheckItem;
public interface CheckServlice {
	
	/**
	 * 发起签到
	 * @param check
	 * @return 
	 */
	public int addCheck(Check check);
	/**
	 * 查询签到列表
	 * @param createId 发起人Id
	 * @return
	 */
	public List<Check> findCheckList(Long createId);
	/**
	 * 进行签到
	 * @param checkItem 签到详情
	 * @param checkId 签到ID
	 * @return
	 */
	public int toCheck(CheckItem checkItem,Long checkId);
	/**
	 * 	查看签到详情
	 * @param checkId 签到Id
	 * @return 签到详情列表
	 */
	public List<CheckItem> findcheckItemList(Long checkId);
	/**
	 * 查询单个签到,为定时关闭
	 * @param createId 签到Id
	 * @return 
	 */
	public Check selectCheck(Long createId);
	/**
	 * 查询最近的一条签到信息，为学生签到准备
	 * @param gradeId
	 * @return
	 */
	public Check selectCheckOfStudent(Long gradeId);
}
