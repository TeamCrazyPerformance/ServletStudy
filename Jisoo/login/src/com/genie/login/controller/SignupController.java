package com.genie.login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.genie.login.model.SignupModel;
import com.genie.login.vo.User;

public class SignupController extends HttpServlet {
	
	private SignupModel model = new SignupModel();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		RequestDispatcher view = req.getRequestDispatcher("signup.jsp");
		view.forward(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		User user = new User(req.getParameter("id"), req.getParameter("name"), req.getParameter("password"));
		RequestDispatcher view = null;
		String result = model.setUser(user);
		if (result.equals("DUPLICATE")) {
			System.out.println("id duplicated");
			view = req.getRequestDispatcher("joinFail.jsp");
		} else if (result.equals("FAIL")) {
			System.out.println("internal error");
			view = req.getRequestDispatcher("joinFail.jsp");
		} else if (result.equals("OK")) {
			System.out.println("SUCCESS");
			view = req.getRequestDispatcher("joinSuccess.jsp");
		}
		view.forward(req, res);
	}
	
}
