package com.tcp.study.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcp.study.dto.User;

public class LoginController extends HttpServlet{
	public  void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		BufferedReader br = new BufferedReader(new FileReader(new File("test.txt")));
		
	User test = new User();
		
		
		if(id.equals(test._id)){
			if(pw.equals(test._pw)){
				out.println("success!");	
			}
		}
		else{
			out.println("fail");
		}
	} 

}
