package com.tcp.login.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcp.login.model.LoginModel;;

public class LoginControl extends HttpServlet {
	
	LoginModel loginModel = LoginModel.getInstance();
	private boolean resultLogin = false;
	
	JsonParser jsonParser = JsonParser.getInstance();
	Hashtable<String, Hashtable<String,String>> list ; 
	String filePath = "/WEB-INF/user.txt";
	
//C:\Users\�����\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\login\WEB-INF
//	 ������ �ִ� init() �Լ��� ��� override ����? 
	public void init(ServletConfig config) throws ServletException{
		String path = config.getServletContext().getRealPath(filePath);
		System.out.println(path);
		list = jsonParser.jsonReader(path);		
		loginModel.setList(list);
	}
	

	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		
		
		
		
//		PrintWriter out = res.getWriter();
//		out.println("Beer Selection Advice<br>");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		resultLogin = loginModel.checkLogin(id, pw);		
		
		
		
		req.setAttribute("resultLogin", resultLogin);
		/*req.setAttribute("styles", beerList);*/
		// request���ٰ� attribute�� ������ �ְڴ�. 
		// 
		
		res.setContentType("text/html");
		
		RequestDispatcher view;
//		view = req.getServletContext().getRequestDispatcher("result.jsp");
		view = req.getRequestDispatcher("result.jsp");
		//jsp ������ �ҷ��ִ¾�. 
		view.forward(req, res);
	}
	}
