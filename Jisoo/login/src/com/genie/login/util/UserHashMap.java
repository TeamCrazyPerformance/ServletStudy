package com.genie.login.util;

import java.util.HashMap;

import com.genie.login.vo.User;

public class UserHashMap {

	private static UserHashMap instance;
	private static HashMap<String, User> userHashMap;
	private UserHashMap() {};
	
	public static synchronized UserHashMap getInstance(){
		if (instance == null) {
			instance = new UserHashMap();
		}
		return instance;
	}
	
	public void put(User user) {
		if (userHashMap == null) {
			setMap(new HashMap<>());
		}
		userHashMap.put(user.getId(), user);
	}
	
	public User get(String userId) {
		if (userHashMap == null) {
			setMap(new HashMap<>());
		}
		return (User) userHashMap.get(userId);
	}
	
	public void setMap(HashMap map) {
		this.userHashMap = map;
	}
}
