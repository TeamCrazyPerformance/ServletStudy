package study.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import study.model.SignUpModel;
public class SignUpController extends HttpServlet{
	public void	doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		String user_name = req.getParameter("user_name"),
				user_id = req.getParameter("user_id"),
				user_pw = req.getParameter("user_pw");
		System.out.println("SignUp Controller");
		SignUpModel signup = new SignUpModel(user_name, user_id, user_pw);
		signup.Success();
	}
}
