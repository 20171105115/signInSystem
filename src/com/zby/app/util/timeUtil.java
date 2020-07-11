package com.zby.app.util;


import java.util.TimerTask;

import com.zby.app.dao.CheckDao;

public class timeUtil extends TimerTask{
	
	CheckDao checkDao = new CheckDao();
	
	Long checkId = null;
	

	public timeUtil(Long checkId) {
		this.checkId=checkId;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		checkDao.updateCheck(checkId);
	}

}
