package kr.kuvh.tcpstudy.login.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import kr.kuvh.tcpstudy.login.Const.errorList;
import kr.kuvh.tcpstudy.login.Util;

public class Member {
	private static String dbPath = "/database/member.json";
	private static String absolutePath;
	private static String absoluteDBPath;
	
	private String username;
	
	ServletContext mContext;
	
	public Member(ServletContext context) {
		mContext = context;
		
		absolutePath = mContext.getRealPath("/");
		
		System.out.println(absolutePath);
		
		if(!isDatabaseExist())
			createDatabase();
	}
	
	private boolean isDatabaseExist() {
		File dir = new File(absolutePath, dbPath);
		absoluteDBPath = dir.getAbsolutePath();
		return dir.exists();
	}
	
	private void createDatabase() {
		File dir = new File(absolutePath, "database");
		if (!dir.exists())
			dir.mkdir();
		
		JSONArray arr = new JSONArray();
		
		try {
			FileWriter dbFile = new FileWriter(absoluteDBPath);
			dbFile.write(arr.toJSONString());
			
			dbFile.flush();
			dbFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public errorList checkUser(String id, String password) {
		JSONParser parser = new JSONParser();
		Util util = new Util();
		
		try {
			Object jsonDB = parser.parse(new FileReader(absoluteDBPath));
			
			JSONArray arr = (JSONArray)jsonDB;
			
			int idx = util.findJSONValueByKey(arr, "id", id);
			if (idx == -1)
				return errorList.NOT_FOUND_USER;
			
			JSONObject obj = (JSONObject)arr.get(idx);
			if (!util.encryptPassword(password).equals(obj.get("password").toString()))
				return errorList.NOT_VALID_PASSWORD;
			
			username = obj.get("name").toString();
			
			return errorList.SUCCESS;			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorList.UNKNOWN;
	}
	
	public String getUserName() {
		return username;
	}
		
}
