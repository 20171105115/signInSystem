package com.zby.app.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zby.app.entity.Grade;
import com.zby.app.entity.Things;
import com.zby.app.entity.User;
import com.zby.app.service.GradeService;
import com.zby.app.service.ThingsService;
import com.zby.app.service.impl.GradeServiceImpl;
import com.zby.app.service.impl.ThingsServiceImpl;

/**
 * Servlet implementation class thingsServlet
 */
@WebServlet("/thingsServlet")
public class thingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public thingsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String option  = request.getParameter("option");
		
		GradeService gradeService = new GradeServiceImpl();
		ThingsService thingsService = new ThingsServiceImpl();
		
		if (option.equals("toAdd")) {
			List<Grade> list = gradeService.findAllGrade();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/gradelist.jsp").forward(request, response);
		}
		if (option.equals("add")) {
			String thingsName = "加入班级";
			String gradeName = request.getParameter("gradeName");
			String thingsDesc = ((User)session.getAttribute("user")).getName() + "请求加入班级"+gradeName;
			Long gradeId = Long.valueOf(request.getParameter("gradeId"));
			Long userId = ((User)session.getAttribute("user")).getUserId();
			Things things = new Things();
			things.setThingsName(thingsName);
			things.setThingsDesc(thingsDesc);
			things.setThingsGradeId(gradeId);
			things.setThingsCreateId(userId);
			thingsService.addThings(things);
			response.sendRedirect("userServlet?option=self");
		}
		if (option.equals("update")) {//处理请求
			String status = request.getParameter("status");
			String thingsId= request.getParameter("thingsId");
			Long gradeId = Long.valueOf(request.getParameter("gradeId"));
			Long userId = Long.valueOf(request.getParameter("userId"));
			Things things = new Things();
			things.setThingsCreateId(userId);
			things.setThingsGradeId(gradeId);
			things.setThingsId(Long.valueOf(thingsId));
			things.setThingsStatus(Integer.valueOf(status));
			things.setThingsSolveId(((User)session.getAttribute("user")).getUserId());
			thingsService.editThings(things);
			response.sendRedirect("thingsServlet?option=findMineThings");
		}
		if (option.equals("findMineThings")) {//查看请求列表
			Long createId = ((User)session.getAttribute("user")).getUserId();
			List<Things> thingsList = thingsService.findThingsList(createId);
			if (thingsList.isEmpty()) {
				request.setAttribute("msgError", "这里空空如也，还没有请求");
				request.getRequestDispatcher("thingslist.jsp").forward(request, response);
			}else {
				request.setAttribute("list", thingsList);
				request.getRequestDispatcher("thingslist.jsp").forward(request, response);
			}
			
		}
		
	}

}
