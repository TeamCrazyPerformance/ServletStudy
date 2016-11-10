package com.tcp.study.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcp.study.model.LoginModel;
import com.tcp.study.util.UserData;
import com.tcp.study.util.UserInfo;

public class LoginController extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		UserData.getData();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		UserInfo info = new UserInfo();
		info.setId(id);
		info.setPassword(password);
		int loginResult;
		
		LoginModel loginModel = new LoginModel();
		loginResult = loginModel.login(info);
		
		req.setAttribute("login", loginResult);
		RequestDispatcher view = req.getRequestDispatcher("loginresult.jsp");
		view.forward(req, resp);
		
	}
	/* reflection (리플렉션) - 찾아보기
	 * singleton pattern
	 */
	
	
}
