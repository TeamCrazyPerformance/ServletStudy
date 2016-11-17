package com.tcp.login.model;



import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.tcp.login.controller.JsonParser;



public class LoginModel {
	public static LoginModel instance;
	private JsonParser jsonParser;
	private Hashtable<String,Hashtable<String,String>> list;	
	private Calendar calendar;
	private Date date ;


	public static LoginModel getInstance(ServletContext cntxt) {
		if(instance == null) {
			instance = new LoginModel(cntxt);
		}	
		return instance;
	}
	

	public LoginModel(ServletContext cntxt) {
		jsonParser = JsonParser.getInstance(cntxt);
		calendar = Calendar.getInstance();
		date = calendar.getTime();
		list = jsonParser.getList();
	}

	public void appendList(String id, String pw, String name) {
		Hashtable<String,String> tmp = new Hashtable<>();
		tmp.put("ID", id);
		tmp.put("PW", pw);
		tmp.put("NAME", name);
		list.put(id, tmp);
	}


	/**
	 *  ID�� PW�� ��ġ ���θ� �Ǵ��ϴ� �޼���
	 * @param id
	 * @param pw
	 * @return boolean
	 */
	public boolean checkLogin(String id, String pw) {
//		System.out.println(list.containsKey(id) == true);
//		System.out.println(list.get(id).get("PW").equals(pw));
		if (list.containsKey(id) == true && list.get(id).get("PW").equals(pw)) {
			return true;		
		} else {
			jsonParser.logWrite("Login", id, date.toLocaleString());
			return false;
		}
	}

	/**
	 * ȸ�� ���Խ� �ش� id�� �����ϴ� �� Ȯ���ϴ� method
	 * id�� ������ false, ������ true�� ��ȯ�Ѵ�.
	 * @param id
	 * @return boolean
	 */
	public boolean isUsedID (String id) {
		if (list.containsKey(id) == true) {
			return true;
		} else {
			String mesg = reasonError(1);
			jsonParser.logWrite("sign up", mesg, date.toLocaleString());
			return false;
		}
	}

	/**
	 * ȸ�����Խ� �� ĭ�� �ִ� �� Ȯ�����ִ� �޼���.
	 * �ϳ��� ����� ��� false�� ��ȯ�Ѵ�.
	 * @param name
	 * @param id
	 * @param pw
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public boolean isEmptyTextField(String name, String id, String pw) {

		if (name==null || id==null || pw ==null) {
			String mesg;
			if (name == null) {
				mesg = reasonError(3);
			} else if (id == null) {
				mesg = reasonError(2);
			} else {
				mesg = reasonError(4);
			}

			jsonParser.logWrite("sign up", mesg, date.toLocaleString());

			return true;
		} else {
			return false;
		}

	}


	public String reasonError(int num) {
		String reason = null;
		switch (num) {
		case 1:
			reason = "���̵� �ߺ�";
			break;
		case 2:
			reason ="���̵� ���Է�";
			break;
		case 3:
			reason ="�̸� ���Է�";
			break;
		case 4:
			reason ="��й�ȣ ���Է�";
			break;
		default:
			break;
		}

		return reason;
	}




}
