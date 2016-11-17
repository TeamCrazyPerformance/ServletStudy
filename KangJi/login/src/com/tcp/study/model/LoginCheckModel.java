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
		if(UserDao.getInst().findById(id).getId().equals(id) == true){
			if(UserDao.getInst().findById(id).getPw().equals(password) == true){
				return true;
			}
			FileWriter fw = new FileWriter(path, true);
			String reason = "{\"ERROR\":\"password\",\"DESC\":\"LOGIN_FAIL\",\"ID\":\"" + id + "\",\"TIME\":\"" + date +"\"}," + "\n";
			fw.write(reason);
			fw.close();
			return false;
		}
		else {
			FileWriter fw = new FileWriter(path, true);
			String reason = "{\"ERROR\":\"LOGIN\",\"DESC\":\"LOGIN_FAIL\",\"TRY_ID\":\"" + id + "\",\"TIME\":\"" + date +"\"}," + "\n";
			fw.write(reason);
			fw.close();
			return false;
		}
	}
}