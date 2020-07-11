package com.zby.app.service;

import java.util.List;

import com.zby.app.entity.Things;

public interface ThingsService {
	
	public int addThings(Things things);
	
	public int editThings(Things things);
	
	public List<Things> findThingsList(Long gradeId);
	
}
