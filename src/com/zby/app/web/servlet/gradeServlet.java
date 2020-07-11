package com.zby.app.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zby.app.entity.Grade;
import com.zby.app.entity.User;
import com.zby.app.service.GradeService;
import com.zby.app.service.impl.GradeServiceImpl;

/**
 * Servlet implementation class gradeServlet
 */
@WebServlet("/gradeServlet")
public class gradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gradeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GradeService gradeService = new GradeServiceImpl();
		String option = request.getParameter("option");
		if (option.equals("toAdd")) {
			response.sendRedirect("addgrade.jsp");
		}
		if (option.equals("add")) {
			String gradeName = request.getParameter("gradeName");
			String gradeDesc = request.getParameter("gradeDesc");
			Long createId = ((User)request.getSession().getAttribute("user")).getUserId();
			Grade grade = new Grade();
			grade.setCreateId(createId);
			grade.setCreateTime(new Date());
			grade.setGradeName(gradeName);
			grade.setGradeDesc(gradeDesc);
			if (gradeService.findGrade(gradeName)==null) {
				String username = ((User)request.getSession().getAttribute("user")).getUserName();
				gradeService.addGrade(grade,username);
				response.sendRedirect("userServlet?option=self");
			}else {
				request.setAttribute("errorMsg", "该班级信息已被注册，请重新填写注册信息");
				request.getRequestDispatcher("gradeServlet?option=toAdd").forward(request, response);
			}
			//增加班级信息
		}
		if (option.equals("toEdit")) {
			//去修改
			String gradeName = request.getParameter("gradeName");
			Grade grade = gradeService.findGrade(gradeName);
			request.setAttribute("grade", grade);
			request.getRequestDispatcher("editgrade.jsp").forward(request, response);
		}
		if (option.equals("edit")) {
			//修改班级信息
			String gradeName = request.getParameter("gradeName");
			String gradeDesc = request.getParameter("gradeDesc");
			Long gradeId = Long.valueOf(request.getParameter("gradeId"));
			Grade grade = new Grade();
			grade.setGradeName(gradeName);
			grade.setGradeDesc(gradeDesc);
			grade.setGradeId(gradeId);
			int i = gradeService.editGrade(grade);
			if (i==1) {
				response.sendRedirect("gradeServlet?option=findGradeList");
			}else {
				request.setAttribute("errorMsg", "修改失败，请重新修改");
				request.getRequestDispatcher("/toEdit").forward(request, response);
			}
		}
		if (option.equals("findGradeList")) {
			//查询所创建班级列表
			Long createId = ((User)request.getSession().getAttribute("user")).getUserId();
			List<Grade> list = gradeService.findGradeList(createId);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/gradelist.jsp").forward(request, response);
		}
	}

}
