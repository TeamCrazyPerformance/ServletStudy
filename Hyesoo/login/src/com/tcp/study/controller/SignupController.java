package com.tcp.study.controller;




import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcp.study.dto.User;
import com.tcp.study.model.UserDao;


public class SignupController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		System.out.println(name);
		
		User u= new User();
		u.setName(name);
		u.setId(id);
		u.setPassword(pw);
		UserDao ud = new UserDao();
		ud.save(u);
		out.print("success!");
		
	}
}
