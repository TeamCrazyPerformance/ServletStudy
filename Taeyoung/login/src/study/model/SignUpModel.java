package study.model;

public class SignUpModel {
	private String user_name, user_id, user_pw;
	Check_inputData check;
	public SignUpModel(String name, String id, String pw){
		user_name = name;
		user_id = id;
		user_pw = pw;
		check = new Check_inputData(user_name, user_id, user_pw);
	}
	public int Success(){
		System.out.println("SignUpModel");
		return check.Chk_Signup();
	}
}
