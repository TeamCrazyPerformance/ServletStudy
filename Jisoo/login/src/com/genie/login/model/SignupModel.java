package com.genie.login.model;

import com.genie.login.util.UserHashMap;
import com.genie.login.vo.User;

public class SignupModel {

	public String setUser(User user) {
		try {
			if (UserHashMap.getInstance().get(user.getId()) != null) {
				return "DUPLICATE";
			}
			UserHashMap.getInstance().put(user);
		} catch (Exception e) {
			return "FAIL";
		}
		return "OK";
	}
	
	public static void main(String args[]) {
		User user = new User("123", "bonos2", "halim1209");
		SignupModel model = new SignupModel();
		model.setUser(user);
	}
}
