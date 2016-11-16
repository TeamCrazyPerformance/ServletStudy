package com.tcp.study.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tcp.study.util.UserDao;

public class LoginCheckModel {
	public boolean login(String id, String password) throws IOException{
		String path = "C:/Users/kjhmd/Desktop/ServletStudy/KangJi/login/Log.txt";
		Date d = new Date();
		String date = d.toString();
		if(UserDao.getInst().findById(id) == true){
			if(UserDao.getInst().findByPw(password) == true){
				return true;
			}
			FileWriter fw = new FileWriter(path, true);
			String reason = "{\"error\":\"password\",\"desc\":\"Login Fail\",\"id\":\"" + id + "\",\"time\":\"" + date +"\"}," + "\n";
			fw.write(reason);
			fw.close();
			return false;
		}
		else {
			FileWriter fw = new FileWriter(path, true);
			String reason = "{\"error\":\"login\",\"desc\":\"Login Fail\",\"Try id\":\"" + id + "\",\"time\":\"" + date +"\"}," + "\n";
			fw.write(reason);
			fw.close();
			return false;
		}
	}
}