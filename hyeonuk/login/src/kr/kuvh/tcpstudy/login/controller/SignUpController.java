package kr.kuvh.tcpstudy.login.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kuvh.tcpstudy.login.Const.errorList;
import kr.kuvh.tcpstudy.login.JsonDBConnection;
import kr.kuvh.tcpstudy.login.Util;
import kr.kuvh.tcpstudy.login.model.Member;

public class SignUpController extends HttpServlet {
	
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		
		System.out.println("LOG: [SignUpController] init...");
		
		JsonDBConnection.getInstance(getServletContext());
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
		Util util = new Util(getServletContext());
		
		//������ ���� ������ �޾ƿ�
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String username = req.getParameter("username");
		if(username != null) username = new String(username.getBytes("8859_1"), "UTF8");
		
		errorList result;
		
		if(id.isEmpty()) {
			util.logErrorSignUp("���̵���Է�");
			result = errorList.EMPTY_ID_VALUE;
		} else if(password.isEmpty()) {
			util.logErrorSignUp("��й�ȣ���Է�");
			result = errorList.EMPTY_PASSWORD_VALUE;
		} else if(username.isEmpty()) {
			util.logErrorSignUp("�̸����Է�");
			result = errorList.EMPTY_USERNAME_VALUE;
		} else {
			//�𵨿��� ��û
			Member member = new Member(getServletContext());
			result = member.insertUser(id, password, username);
		}
			
		
		RequestDispatcher view = req.getRequestDispatcher("signupfail.jsp");
		
		if (result == errorList.SUCCESS) {
			view = req.getRequestDispatcher("login.html");
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
