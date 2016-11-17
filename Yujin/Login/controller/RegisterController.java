package tcp.register.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tcp.register.model.RegisterModel;

public class RegisterController extends HttpServlet {
	//�Ķ���͸� �޾� user.txt�� �����ϴ� �͸� �Ϸ�. �ߺ��˻�, ����ó�� ���� X

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("idSignup");
		String name = request.getParameter("nameSignup");
		String pwd = request.getParameter("pwdSignup");
		List error = new ArrayList();
		
		if(id == "" || name == "" || pwd == ""){
			if(id == "") error.add("id empty");
			if(name == "") error.add("name empty");
			if(pwd == "") error.add("password empty");
		}
		else{
			RegisterModel model = new RegisterModel();
			
			//json ������� String �����
			String jsonUser = model.stringToJson(id, name, pwd);
			
			//���� jsonData�� ���ο� jsonUser�� �߰��Ͽ� ���ۼ� (����)
			File userData = new File("user.txt");//user.txt ��� �Է�
			/*if(model.registerUser(userData, id, name, pwd).equals( "failed")){
				error.add("id overlap");
			}; //registerUser�� String���� ���� �޼��� Ȥ�� ���� �޼����� �����Ѵ�*/
			
		}
		
	
		
		//error�� ������� ��(error�� ���� ��) login.html�� �����̷�Ʈ
		if(error.isEmpty() == true){
			/*RequestDispatcher view = request.getRequestDispatcher("login.html");
			view.forward(request, response);*/
			response.sendRedirect("login.html");
		}
		//error�� ������ �� registerFail.jsp�� �н� & �α��ۼ�
		else{
			RegisterModel model = new RegisterModel();
			model.writeLog(error);
			
			request.setAttribute("error", error);
			RequestDispatcher view = request.getRequestDispatcher("registerFail.jsp");
			view.forward(request, response);
		}
	}

}
