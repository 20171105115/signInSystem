package com.zby.app.web.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.zby.app.entity.User;
import com.zby.app.service.UserService;
import com.zby.app.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserDao
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		PrintWriter  out = response.getWriter();
		UserService userService = new UserServiceImpl();
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String userDesc = request.getParameter("userDesc");
		//设置日期格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String birthday = request.getParameter("birthday");
		Date date = null;
		try {
			date = format.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//为对象赋值
		User user = new User();
		user.setUserDesc(userDesc);
		user.setUserName(username);
		user.setName(name);
		user.setGender(Integer.parseInt(gender));
		user.setBirthday(date);
		int i = userService.addUser(user);
		if(i==1) {
			out.write("1");
		}else {
			out.write("-1");
		}
	}

}
