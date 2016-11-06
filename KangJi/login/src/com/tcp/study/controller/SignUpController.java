package com.tcp.study.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcp.study.model.SignUpCheckModel;

public class SignUpController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		SignUpCheckModel check = new SignUpCheckModel();
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		
		if (check.signup(id, password, name) == false){
			
		};
	}
}