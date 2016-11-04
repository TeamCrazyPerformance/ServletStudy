package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginModel;
import model.UserModel;

public class Login extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		res.setContentType("text/html");
		String id = req.getParameter("id");
		String passwd = req.getParameter("passwd");
		LoginModel model = new LoginModel();
		if(!model.login(id, passwd)){
			req.setAttribute("errMsg", model.getErrMsg());
			RequestDispatcher view = req.getRequestDispatcher("loginFail.jsp");
			view.forward(req, res);
		}
		else{
			RequestDispatcher view = req.getRequestDispatcher("loginComplete.html");
			view.forward(req, res);
		}
		
	}
	
}
