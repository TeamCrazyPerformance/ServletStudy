package com.tcp.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcp.login.model.LoginModel;

public class SignControl extends HttpServlet {
	
	LoginModel loginModel = LoginModel.getInstance(); ;
	private boolean result = false;
	String path;
	
	JsonParser jsonParser = JsonParser.getInstance();
	Hashtable<String, Hashtable<String,String>> list ; 
	String filePath = "/WEB-INF/user.txt";
	
	
	public void init(ServletConfig config) throws ServletException{
		
		path = config.getServletContext().getRealPath(filePath);
		list = jsonParser.jsonReader(path);		
		loginModel.setList(list);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		
		res.setContentType("text/html");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");	
		String name = req.getParameter("name");
		
		System.out.println(id);
		
		result = loginModel.isUsedID(id) || loginModel.isEmptyTextField(name, id, pw);
		System.out.println(result);
		
		if (result == false) {
			System.out.println("com result false");
			loginModel.appendList(id, pw, name);
			jsonParser.jsonWriter(id, pw, name, path);
		}
//		 else  {
//			num = 
//			mesg = loginModel.reasonError();
//			jsonParser.logWrite("sign up", mesg, time, name);
//		}
		
		req.setAttribute("resultSignUp", result);
		
		RequestDispatcher view = req.getRequestDispatcher("signUpResult.jsp");
		view.forward(req, res);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("here");
	}
}
