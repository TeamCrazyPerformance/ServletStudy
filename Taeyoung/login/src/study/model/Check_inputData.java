package study.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Check_inputData {
	private String user_name,user_id,user_pw;
	public Check_inputData(String name, String id, String pw){
		user_name = name;
		user_id = id;
		user_pw = pw;
		Chk_trim();
		System.out.println("Check_inputData()");

	}
	public Check_inputData(String id, String pw){
		user_name = "";
		user_id = id;
		user_pw = pw;
		Chk_trim();
	}
	private void Chk_trim(){
		if(user_id.trim().length()==0)
			user_id = null;
		if(user_pw.trim().length()==0)
			user_pw = null;
		if(user_name.trim().length()==0)
			user_name = null;
	}
	public int Chk_Login(){
		JsonPaser jp = new JsonPaser();
		System.out.println("Login");
		List<UserData> list = new ArrayList<UserData>();
		list = jp.getUserData();
		int i = 1;
		while(i<list.size()-1){
			System.out.println(list.get(i).getId().equals(user_id));
			System.out.println(list.get(i).getPw().equals(user_pw));
			if(list.get(i).getId().equals(user_id) && list.get(i).getPw().equals(user_pw)){
				System.out.println("로그인 성공");
				return 0;
			}
			else{
				i++;
			}
		}
		System.out.println("로그인 실패");
		return 1;
	}
	public int Chk_Signup(){
		JsonPaser jp = new JsonPaser();
		System.out.println("Signup");
		List<UserData> list = new ArrayList<UserData>();
		list = jp.getUserData();
		int i = 1;
		while(i<list.size()-1){
			System.out.println("이름 :"+list.get(i).getName());
			if(list.get(i).getName().equals(user_name)){
				System.out.println("중복 : name ");
				return 1;
			}
			if(list.get(i).getId().equals(user_id)){
				System.out.println("중복 : id ");
				return 1;
			}
			i++;
		}
		System.out.println("중복없음 ");
		return 0;
	}
}
