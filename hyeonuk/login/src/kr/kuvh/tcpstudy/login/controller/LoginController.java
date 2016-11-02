package kr.kuvh.tcpstudy.login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kuvh.tcpstudy.login.Const.errorList;
import kr.kuvh.tcpstudy.login.model.Member;

public class LoginController extends HttpServlet {
		
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		//throws IOException, ServletException
		
		//로그인 데이터 받아옴
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		//모델에서 요청
		Member member = new Member(getServletContext());
		errorList result = member.checkUser(id, password);
		String username = member.getUserName();
		
		RequestDispatcher view = req.getRequestDispatcher("loginfail.jsp");
		
		if (result == errorList.SUCCESS) {
			view = req.getRequestDispatcher("loginsuccess.jsp");
			req.setAttribute("username", username);
		}
		
		try {
			req.setAttribute("errormsg", result);
			view.forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
