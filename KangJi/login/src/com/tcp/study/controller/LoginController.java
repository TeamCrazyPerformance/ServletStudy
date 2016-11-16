package com.tcp.study.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcp.study.model.LoginCheckModel;
import com.tcp.study.model.SignUpCheckModel;
import com.tcp.study.util.UserDao;

public class LoginController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		LoginCheckModel check = new LoginCheckModel();
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		if(check.login(id, password) == true){
			res.sendRedirect("LoginSuccess.html");
		}
		else{
			res.sendRedirect("LoginFail.html");
		}
	}
}