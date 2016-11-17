package com.tcp.study.model;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.tcp.study.dto.User;
import com.tcp.study.util.JsonParser;

public class UserDao {
	public boolean save(String id, String password, String name) {
		User u = new User();
		u.setId(id);
		u.setPassword(password);
		u.setName(name);
		JsonParser j = new JsonParser();
		FileWriter fout = null;
		try{
			fout = new FileWriter("user.txt");
			String info = j.stringfyJson(u.getName(), u.getId(), u.getPassword());
			fout.write(info);
			fout.close();
			
		}
		catch(IOException e){
			System.out.println("입출력 오류");
		}
		
		return false;
	}
	
	public boolean save(User user) {
		return this.save(user.getId(), user.getName(), user.getPassword());
	}
	
	public boolean login(String id, String pw) {
		return true;
	}
}
