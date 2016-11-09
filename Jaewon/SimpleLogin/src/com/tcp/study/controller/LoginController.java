package com.tcp.study.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcp.study.model.LoginModel;
import com.tcp.study.util.InitData;

public class LoginController extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		InitData data = InitData.getData();
	}
	
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
