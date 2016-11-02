package kr.kuvh.tcpstudy.login.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

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
				
		try {
			File file = new File(absoluteDBPath);
			file.createNewFile();
			
			BufferedWriter dbFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(absoluteDBPath), "UTF8"));
			dbFile.write("[]");
			
			dbFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public errorList checkUser(String id, String password) {
		Util util = new Util();
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(absoluteDBPath),"UTF8"));
			Pattern pattern = Pattern.compile("\\{\"id\":\"(.*?)\",\"name\":\"(.*?)\",\"password\":\"(.*?)\"\\}");
			Matcher matcher;
			
			String userData;
			
			ArrayList<User> list = new ArrayList<User>();
			
			while ((userData = reader.readLine()) != null) {
				matcher = pattern.matcher(userData);
				
				if(matcher.find()) {
					User user = new User(matcher.group(1), matcher.group(2), matcher.group(3));
					list.add(user);
				}
			}
			
			int idx = getUserIndex(list, id);
			
			if (idx == -1) {
				return errorList.NOT_FOUND_USER;
			}
			
			User mUser = list.get(idx);
			
			if (!util.encryptPassword(password).equals(mUser.getPassword()))
				return errorList.NOT_VALID_PASSWORD;
			
			username = mUser.getUsername();
			
			return errorList.SUCCESS;			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorList.UNKNOWN;
	}
	
	public errorList insertUser(String id, String password, String username) {
		Util util = new Util();
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(absoluteDBPath),"UTF8"));
			Pattern pattern = Pattern.compile("\\{\"id\":\"(.*?)\",\"name\":\"(.*?)\",\"password\":\"(.*?)\"\\}");
			Matcher matcher;
			
			String userData;
			
			ArrayList<User> list = new ArrayList<User>();
			
			while ((userData = reader.readLine()) != null) {
				matcher = pattern.matcher(userData);
				
				if(matcher.find()) {
					User user = new User(matcher.group(1), matcher.group(2), matcher.group(3));
					list.add(user);
				}
			}
			
			if(getUserIndex(list, id) != -1) {
				return errorList.ALREADY_USER_EXIST;
			}
			
			BufferedWriter dbFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(absoluteDBPath), "UTF8"));
			
			StringBuilder builder = new StringBuilder("[");
			builder.append(System.getProperty("line.separator"));
			
			Iterator<User> iterator = list.iterator();
			while(iterator.hasNext()) {
				builder.append(iterator.next().toString());
				builder.append(",");
				builder.append(System.getProperty("line.separator"));				
			}
			
			User user = new User(id, username, util.encryptPassword(password));
			
			builder.append(user.toString());
			builder.append(System.getProperty("line.separator"));
			builder.append("]");
			
			dbFile.write(builder.toString());
			
			dbFile.close();
			
			return errorList.SUCCESS;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorList.UNKNOWN;	
	}
	
	private int getUserIndex(ArrayList<User> arr, String id) {
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i).getID().equals(id))
				return i;
		}
		return -1;
	}
	
	public String getUserName() {
		return username;
	}
		
}
