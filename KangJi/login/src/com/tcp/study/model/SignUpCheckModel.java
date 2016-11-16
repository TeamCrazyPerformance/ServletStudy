package com.tcp.study.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.tcp.study.util.UserDao;

public class SignUpCheckModel {
	public boolean signup(String id, String password, String name) throws IOException{
		if(UserDao.getInst().findById(id) == false){
			String path = "C:/Users/kjhmd/Desktop/ServletStudy/KangJi/login/User.txt";
			FileWriter fw = new FileWriter(path, true);
			String info = "{\"id\":\"" + id + "\",\"password\":\"" + password + "\",\"name\":\"" + name + "\"}," + "\n";
			fw.write(info);
			fw.close();
			
			UserDao userDao = UserDao.getInst();
			
			return true;
		}
		else {
			return false;
		}
	}
}