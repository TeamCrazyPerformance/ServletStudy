package com.tcp.study.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tcp.study.dto.UserDto;

public class JsonParser {
	private String UserPath = "";
	
	public JsonParser(String UserPath) {
		this.UserPath = UserPath;
	}
	
	private BufferedReader loadText() {
		BufferedReader text = null;
		try {
			text = new BufferedReader(new FileReader(UserPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}
	
	private UserDto parseLine(String line) {
		if(line.equals("[") || line.equals("]")){
			return null;
		}
		
		String string[] = line.split(",");
		string[0].substring(7, string[0].length()-1);
		string[1].substring(12, string[1].length()-1);
		string[2].substring(8, string[2].length()-1);
		
		UserDto user_class = new UserDto();
		user_class.setId(string[0]);
		user_class.setPw(string[1]);
		user_class.setName(string[2]);
		
		return user_class;
	}
	
	public List<UserDto> parseJson() throws IOException {
		List<UserDto> users = new ArrayList<UserDto>();
		while(true) {
			String temp = loadText().readLine();
			if(temp == null) break;
			String line[] = temp.split("}");
			users.add(parseLine(line[0]));
		}
		return users;
	}
}
