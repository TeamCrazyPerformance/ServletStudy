package com.tcp.study.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcp.study.model.LoginModel;

public class LoginController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("password");

		
		LoginModel loginModel = new LoginModel();
	}
	/* reflection (리플렉션) - 찾아보기
	 * singleton pattern
	 */
	
	
}
