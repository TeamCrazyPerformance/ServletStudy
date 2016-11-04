package study.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LoginModel {
	private String user_id, user_pw;
	FileControll fc = new FileControll();
	public LoginModel(String id, String pw) {
		user_id = id;
		user_pw = pw;
	}
	public int Success(){
		checkData();
		return 0;
	}
	private void checkData(){
		Check_inputData check = new Check_inputData(user_id, user_pw);
		check.Chk_Login();
	}
	private int logData(){
		//fc.file_Write(data, "log");
		return 0;
	}
}
