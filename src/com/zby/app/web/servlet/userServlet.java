package com.zby.app.web.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zby.app.entity.User;
import com.zby.app.service.UserService;
import com.zby.app.service.impl.UserServiceImpl;

/**
 * Servlet implementation class userServlet
 */
@WebServlet("/userServlet")
public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public userServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserService userService = new UserServiceImpl();
		String option = request.getParameter("option");
		HttpSession session = request.getSession();
		if (option.equals("self")) {
			// 个人信息
			String username = ((User) session.getAttribute("user")).getUserName();
			User user = userService.findUserInfo(username);
			session.setAttribute("user", user);
			response.sendRedirect("self.jsp");
		}
		if (option.equals("toEdit")) {
			// 去修改
			response.sendRedirect("edituser.jsp");
		}
		if (option.equals("edit")) {
			// 修改
			User user = new User();
			String name = request.getParameter("name");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String birthday = request.getParameter("birthday");
			Date date = null;
			try {
				date = format.parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int gender = Integer.valueOf(request.getParameter("gender"));
			String userDesc = request.getParameter("userDesc");
			String password = ((User) session.getAttribute("user")).getPassword();
			String username = request.getParameter("username");
			user.setGender(gender);
			user.setPassword(password);
			user.setName(name);
			user.setUserDesc(userDesc);
			user.setBirthday(date);
			user.setUserName(username);
			int i = userService.editUser(user);
			if (i == 1) {
				response.sendRedirect("userServlet?option=self");
			} else {
				request.setAttribute("errorMsg", "您输入的格式有误，请重新修改");
				response.sendRedirect("edituser.jsp");
			}
		}
		if (option.equals("findUserList")) {
			// 查询班级内同学信息
			Long gradeId = Long.valueOf(request.getParameter("gradeId"));
			List<User> list = userService.findUserListInfo(gradeId);
			if (list.isEmpty()) {
				request.setAttribute("errorMsg", "您还没有加入任何班级");
				request.getRequestDispatcher("/userlist.jsp").forward(request, response);
			} else {
				request.setAttribute("list", list);
				request.getRequestDispatcher("/userlist.jsp").forward(request, response);
			}
		}
		if (option.equals("quit")) {
			session.setAttribute("user", null);
			response.sendRedirect("login.jsp");
		}
		if (option.equals("toChangePassword")) {
			response.sendRedirect("changepassword.jsp");
		}
		if (option.equals("changePassword")) {
			User user = (User) session.getAttribute("user");
			String new1 = request.getParameter("new1");
			String new2 = request.getParameter("new2");
			String old = request.getParameter("old");
			System.out.println(user.getPassword());
			if (user.getPassword().equals(old)) {
				if (new1.equals(new2)) {
					user.setPassword(new1);
					userService.changePassword(user);
					response.sendRedirect("userServlet?option=self");
				}
			} else {
				response.sendRedirect("userServlet?option=toChangePassword");
			}
		}
		if (option.equals("cut")) {//切换班级,为了发布签到
			Long gradeId = Long.valueOf(request.getParameter("gradeId"));
			User user = (User)session.getAttribute("user");
			user.setGradeId(gradeId);
			userService.editUser(user);
			response.sendRedirect("userServlet?option=self");
		}
	}
}
