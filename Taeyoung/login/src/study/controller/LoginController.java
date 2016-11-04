package study.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import study.model.*;
import study.view.*;


public class LoginController extends HttpServlet{
	@Override
	public void	doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		String user_id = req.getParameter("user_id");
		String user_pw = req.getParameter("user_pw");
		System.out.println("succes");
		loginData(user_id,user_pw);
	}
	private int loginData(String id, String pw){
		LoginModel login = new LoginModel(id,pw);
		return login.Success();
	}
}
