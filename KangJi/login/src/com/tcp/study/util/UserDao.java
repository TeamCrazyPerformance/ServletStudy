package com.tcp.study.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.tcp.study.dto.UserDto;

public class UserDao {
	//singleton
	private static UserDao inst = null;
	private List<UserDto> users = null;
	
	public static UserDao getInst() {
		if (inst == null) {
			synchronized(UserDao.class) {
				if (inst == null) {
					UserDao.inst = new UserDao();
				}
			}
		}
		
		return UserDao.inst;
	}
	
	UserDao() {
		JsonParser p = new JsonParser("C:/Users/kjhmd/Desktop/ServletStudy/KangJi/login/User.txt");
		try {
			this.users = p.parseJson();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean findById(String id) {
		for (int i=0; i<this.users.size(); i++) {
			if (this.users.get(i).getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean findByPw(String pw) {
		for (int i=0; i<this.users.size(); i++) {
			if (this.users.get(i).getPw().equals(pw)) {
				return true;
			}
		}
		return false;
	}
}