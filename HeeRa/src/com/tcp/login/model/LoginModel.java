package com.tcp.login.model;



import java.util.Hashtable;

import com.tcp.login.controller.JsonParser;

//import com.tcp.login.controller.JsonParser;


public class LoginModel {
	public static LoginModel instance;
	private Hashtable<String,Hashtable<String,String>> list;	
	public JsonParser jsonPareser;

	
	public static LoginModel getInstance() {
		if(instance == null) {
			instance = new LoginModel();
		}
		
		return instance;
	}
	
	public LoginModel() {
//		jsonParser = JsonParser.getInstance();
////		filePath = "/login/src/com/tcp/resource/user.txt";
//		list = jsonParser.jsonReader(filePath) ;
	
	}
	
	
	public void setList(Hashtable<String,Hashtable<String,String>> list) {
		this.list = list;
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
		System.out.println(list.containsKey(id) == true);
		System.out.println(list.get(id).get("PW").equals(pw));
		if (list.containsKey(id) == true && list.get(id).get("PW").equals(pw)) {
				return true;		
		} else {
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
	public boolean isEmptyTextField(String name, String id, String pw) {
	
		if (name==null || id==null || pw ==null) {
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
		}
		
		return reason;
	}
		
		


}
