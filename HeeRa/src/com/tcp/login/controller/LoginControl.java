package com.tcp.login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcp.login.model.LoginModel;;

public class LoginControl extends HttpServlet {

	private LoginModel loginModel;
	private boolean resultLogin = false;
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		
		loginModel = LoginModel.getInstance(getServletContext());
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		String id = req.getParameter("id");
		String pw = req.getParameter("pw");

		resultLogin = loginModel.checkLogin(id, pw);		

		req.setAttribute("resultLogin", resultLogin);

		res.setContentType("text/html");

		RequestDispatcher view;

		view = req.getRequestDispatcher("result.jsp");

		view.forward(req, res);
	}

}
