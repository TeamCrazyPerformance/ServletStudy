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
	 *  ID와 PW의 일치 여부를 판단하는 메서드
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
	 * 회원 가입시 해당 id가 존재하는 지 확인하는 method
	 * id가 있으면 false, 없으면 true를 반환한다.
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
	 * 회원가입시 빈 칸이 있는 지 확인해주는 메서드.
	 * 하나라도 비었을 경우 false를 반환한다.
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
			reason = "아이디 중복";
			break;
		case 2:
			reason ="아이디 미입력";
			break;
		case 3:
			reason ="이름 미입력";
			break;
		case 4:
			reason ="비밀번호 미입력";
			break;	
		}
		
		return reason;
	}
		
		


}
