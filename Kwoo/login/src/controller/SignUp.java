package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginModel;
import model.SignUpModel;

public class SignUp extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		res.setContentType("text/html");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String passwd = req.getParameter("passwd");
		SignUpModel model = new SignUpModel();
		if(!model.signUp(id, name, passwd)){
			req.setAttribute("errMsg", model.getErrMsg());
			RequestDispatcher view = req.getRequestDispatcher("signupFail.jsp");
			view.forward(req, res);
		}
		else{
			RequestDispatcher view = req.getRequestDispatcher("login.html");
			view.forward(req, res);
		}
		
	}
	
}
