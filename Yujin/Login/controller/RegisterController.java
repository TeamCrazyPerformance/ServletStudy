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
	//파라미터를 받아 user.txt로 저장하는 것만 완료. 중복검사, 예외처리 구현 X

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
			
			//json 모양으로 String 만들기
			String jsonUser = model.stringToJson(id, name, pwd);
			
			//본래 jsonData에 새로운 jsonUser를 추가하여 재작성 (수정)
			File userData = new File("user.txt");//user.txt 경로 입력
			/*if(model.registerUser(userData, id, name, pwd).equals( "failed")){
				error.add("id overlap");
			}; //registerUser은 String으로 에러 메세지 혹은 성공 메세지를 리턴한다*/
			
		}
		
	
		
		//error가 비어있을 시(error가 없을 시) login.html로 리다이렉트
		if(error.isEmpty() == true){
			/*RequestDispatcher view = request.getRequestDispatcher("login.html");
			view.forward(request, response);*/
			response.sendRedirect("login.html");
		}
		//error가 존재할 시 registerFail.jsp로 패스 & 로그작성
		else{
			RegisterModel model = new RegisterModel();
			model.writeLog(error);
			
			request.setAttribute("error", error);
			RequestDispatcher view = request.getRequestDispatcher("registerFail.jsp");
			view.forward(request, response);
		}
	}

}
