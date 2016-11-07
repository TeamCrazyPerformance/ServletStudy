package kr.kuvh.tcpstudy.login.model;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import kr.kuvh.tcpstudy.login.Const.errorList;
import kr.kuvh.tcpstudy.login.JsonDBConnection;
import kr.kuvh.tcpstudy.login.User;
import kr.kuvh.tcpstudy.login.Util;

public class Member {
	
	private String username;
	
	ServletContext mContext;
	
	public Member(ServletContext context) {
		mContext = context;
	}
	
	public errorList checkUser(String id, String password) {
		Util util = new Util(mContext);
		
		ArrayList<User> list = JsonDBConnection.getInstance(mContext).getUserList();
		int idx = getUserIndex(list, id);
			
		if (idx == -1) {
			return errorList.NOT_FOUND_USER;
		}
			
		User mUser = list.get(idx);
		
		if (!util.encryptPassword(password).equals(mUser.getPassword()))
			return errorList.NOT_VALID_PASSWORD;
			
		username = mUser.getUsername();
			
		return errorList.SUCCESS;
	}
	
	public errorList insertUser(String id, String password, String username) {
		Util util = new Util(mContext);
		
		ArrayList<User> list = JsonDBConnection.getInstance(mContext).getUserList();
			
		if(getUserIndex(list, id) != -1) {
			util.logErrorSignUp("아이디중복");
			return errorList.ALREADY_USER_EXIST;
		}
		
		User user = new User(id, username, util.encryptPassword(password));
		JsonDBConnection.getInstance(mContext).writeDB(user);
			
		return errorList.SUCCESS;
	}
	
	private int getUserIndex(ArrayList<User> arr, String id) {
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i).getID().equals(id))
				return i;
		}
		return -1;
	}
	
	public String getUserName() {
		return username;
	}
		
}
