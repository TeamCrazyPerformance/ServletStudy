package tcp.login.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tcp.Util.UserData;
import tcp.login.model.LoginModel;

public class LoginController extends HttpServlet {
	//TODO File Read�� ���������� ó�� �ѹ��� �̷�����ϴ�. File Read�� ������ �ڷᱸ���� ȸ�������� �����ϼ���.
	
	public void init(){
		//File Read�� ���������� ó�� �ѹ��� �̷�����ϴ�.
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("idLogin");
		String pwd = request.getParameter("pwdLogin");
		
		
		UserData userData = UserData.getInstance();
		List userDataList = userData.getUserList();
		LoginModel loginModel = new LoginModel();
		
		File log = new File("C:/Users/dong9/Desktop/log.txt");
		
		//user.txt���� ��� ������ ����Ʈ�� ��ȯ
		
		//user.txt���� id ���鸸 ����Ʈ�� ��ȯ
		List idList = new ArrayList();
		idList = loginModel.getIdList();

		//���� �α�
		int index= -1;
		for(int i=0; i<idList.size(); i++){
			if(id.equals( (String)idList.get(i))) index = i;
		}
		if(index == -1){
			RequestDispatcher view = request.getRequestDispatcher("loginFailID.jsp");
			view.forward(request, response);
			loginModel.writeLoginLog(log, id);
		}
		else if(pwd.equals((String)userDataList.get(3*index+2))){
			RequestDispatcher view = request.getRequestDispatcher("loginSuccess.jsp");
			view.forward(request, response);
		}
		else{
			RequestDispatcher view = request.getRequestDispatcher("loginFail.jsp");
			view.forward(request, response);
			loginModel.writeLoginLog(log, id);
		}
		
		
		//���ҵȰ� Ȯ�ο� �ڵ�
		
		
		
		
	}

}
