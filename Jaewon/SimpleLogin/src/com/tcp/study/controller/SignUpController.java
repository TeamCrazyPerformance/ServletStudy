package com.tcp.study.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcp.study.listener.JsonListener;
import com.tcp.study.model.SignUpModel;
import com.tcp.study.util.InitData;
import com.tcp.study.util.UserInfo;

public class SignUpController extends HttpServlet {
	//회원가입 컨트롤러
	//1. id, name, password get
	//2. model에 회원정보 전달, 성공여부 get
	//3. 성공여부에 따라 jsp결과창 변경 (view로 전달해서 처리)
	
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
		String name = req.getParameter("name");
		String password = req.getParameter("password");
//		InitData.getData();
//		System.out.println(id);
//		System.out.println(name);
//		System.out.println(password);
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		userInfo.setName(name);
		userInfo.setPassword(password);
		int resultFlag; //0 = 성공, 1 = 아이디 미입력, 2 = 아아디 중복, 3 = 이름 미입력, 4 = 비밀번호 미입력

		SignUpModel model = new SignUpModel();
		resultFlag = model.signUp(userInfo);
		
		req.setAttribute("signup", resultFlag);
		RequestDispatcher view = req.getRequestDispatcher("signupResult.jsp");
		view.forward(req, resp);
	}
}
