package com.tcp.login.controller;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcp.login.model.LoginModel;

public class SignControl extends HttpServlet {

	private LoginModel loginModel; 
	private JsonParser jsonParser;

	private boolean result;

	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		
		loginModel = LoginModel.getInstance(getServletContext());
		jsonParser = JsonParser.getInstance(getServletContext());

		result = false;
//		filePath = config.getServletContext().getRealPath(filePath);
//		logPath = config.getServletContext().getRealPath(logPath);
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
			jsonParser.jsonWriter(id, pw, name);
		}

		req.setAttribute("resultSignUp", result);

		RequestDispatcher view = req.getRequestDispatcher("signUpResult.jsp");
		view.forward(req, res);
	}

}
