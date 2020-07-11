package com.zby.app.web.servlet;

import java.io.IOException;
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
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserServiceImpl();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.login(username, password);
		HttpSession session = request.getSession();
		if(user!=null) {
			session.setAttribute("user", user);
			response.sendRedirect("userServlet?option=self");
		}else {
			request.setAttribute("errorMsg", "您的账号或密码有误，请重新输入");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
