package com.zby.app.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zby.app.entity.Check;
import com.zby.app.entity.CheckItem;
import com.zby.app.entity.User;
import com.zby.app.service.CheckServlice;
import com.zby.app.service.impl.CheckServiceImpl;
import com.zby.app.util.timeUtil;

/**
 * Servlet implementation class checkServlet
 */
@WebServlet("/checkServlet")
public class checkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		CheckServlice checkServlice = new CheckServiceImpl();
		
		String option = request.getParameter("option");
		if (option.equals("toAdd")) {
			response.sendRedirect("addcheck.jsp");
		}
		if (option.equals("add")) {
			int checkTime = Integer.valueOf(request.getParameter("checkTime"));
			Date startTime = new Date();
			
			Random r = new Random();
			String checkPwd = String.valueOf(r.nextInt(8999) + 1000);
			Date endTime = new Date(startTime.getTime() + checkTime*60000);
			Long createId =  ((User)session.getAttribute("user")).getUserId();
			Long gradeId =  ((User)session.getAttribute("user")).getGradeId();
			
			Check check = new Check();
			check.setCheckStartTime(startTime);
			check.setCheckTime(checkTime);
			check.setCheckEndTime(endTime);
			check.setCheckPwd(checkPwd);
			check.setCheckGradeId(gradeId);
			check.setCheckStatus(0);
			check.setCheckCreateId(createId);
			checkServlice.addCheck(check);
			//查询签到,定时执行关闭签到
			Check check2 = checkServlice.selectCheck(createId);
			Long checkId = check2.getCheckId();
			Timer timer = new Timer();
			timeUtil task = new timeUtil(checkId);
			timer.schedule(task, endTime);
			response.sendRedirect("checkServlet?option=findCheckList");
		}
		if (option.equals("findCheckList")) {
			Long createId =  ((User)session.getAttribute("user")).getUserId();
			List<Check> checkList = checkServlice.findCheckList(createId);
			if (checkList==null) {
				request.setAttribute("errorMsg", "没有签到信息");
			}else {
				request.setAttribute("list", checkList);
			}
			request.getRequestDispatcher("checklist.jsp").forward(request, response);
		}
		if (option.equals("findCheckItemList")) {
			Long checkId = Long.valueOf(request.getParameter("checkId"));
			List<CheckItem> checkItemList = checkServlice.findcheckItemList(checkId);
			if (checkItemList==null) {
				request.setAttribute("errorMsg", "无签到者");
			}else {
				request.setAttribute("itemList", checkItemList);
			}
			request.getRequestDispatcher("checkitemlist.jsp").forward(request, response);;
		}
		if (option.equals("toCheck")) {//去签到
			Long gradeId =  ((User)session.getAttribute("user")).getGradeId();
			Check check = checkServlice.selectCheckOfStudent(gradeId);
			request.setAttribute("check", check);
			request.getRequestDispatcher("check.jsp").forward(request, response);
		}
		if (option.equals("check")) {
			String pwd = request.getParameter("pwd");
			Long gradeId =  ((User)session.getAttribute("user")).getGradeId();
			Long createId =  ((User)session.getAttribute("user")).getUserId();
			Check check = checkServlice.selectCheckOfStudent(gradeId);
			if (pwd.equals(check.getCheckPwd())) {
				CheckItem checkItem = new CheckItem();
				checkItem.setCheckSolveId(createId);
				checkItem.setCheckEndTime(new Date());
				checkItem.setCheckItemStatus(1);
				checkServlice.toCheck(checkItem, check.getCheckId());
				response.sendRedirect("userServlet?option=self");
			}else {
				request.setAttribute("errorMsg", "验证码有误，请重新输入!!!");
				request.getRequestDispatcher("checkServlet?option=toCheck").forward(request, response);
			}

		}
		
	}
}
