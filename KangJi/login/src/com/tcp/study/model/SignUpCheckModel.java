package com.tcp.study.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.tcp.study.util.UserDao;

public class SignUpCheckModel {
	public boolean signup(String id, String password, String name) throws IOException{
		String NoId = new String();
		String NoPw = new String();
		String NoName = new String();
		Date d = new Date();
		String date = d.toString();
		
		int i = 0;
		switch(i){
		case 0:
			if (id == null){
				NoId = "NO ID";
			}
		case 1:
			if (password == null){
				NoPw = "NO PW";
			}
		case 2:
			if (name == null){
				NoName = "NO NAME";
			}
		}
		
		if(id == null || password == null || name == null){ // Sign up error
			String path = "C:/Users/kjhmd/Desktop/ServletStudy/KangJi/login/Log.txt";
			FileWriter fw = new FileWriter(path, true);
			String info = "{\"ERROR\":\"" + "SIGNUP" + "\", \"DESC\":\"" + "SIGNUP_FAIL" + "\", \"MESSAGE\":\"" + NoId + "/" + NoPw + "/" + NoName + "\", \"DATE\":\""+ date +"\"}," + "\n";
			fw.write(info);
			fw.close();
			return false;
		}
		else if(UserDao.getInst().findById(id).getId().equals(id) == false){ // No id overlap
			String path = "C:/Users/kjhmd/Desktop/ServletStudy/KangJi/login/User.txt";
			FileWriter fw = new FileWriter(path, true);
			String info = "{\"ID\":\"" + id + "\",\"PASSWORD\":\"" + password + "\",\"NAME\":\"" + name + "\"}," + "\n";
			fw.write(info);
			fw.close();
			UserDao userDao = UserDao.getInst();
			return true;
		}
		else{ // Id overlap
			String path = "C:/Users/kjhmd/Desktop/ServletStudy/KangJi/login/Log.txt";
			FileWriter fw = new FileWriter(path, true);
			String info = "{\"ERROR\":\"" + "SIGNUP" + "\", \"DESC\":\"" + "SIGNUP_FAIL" + "\", \"MESSAGE\":\"" + "ID_OVERLAP" + "\", \"DATE\":\""+ date +"\"}," + "\n";
			fw.write(info);
			fw.close();
			return false;
		}
	}
}