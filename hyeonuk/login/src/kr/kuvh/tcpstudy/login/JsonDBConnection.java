package kr.kuvh.tcpstudy.login;

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

public class JsonDBConnection {
	
	private static ServletContext mContext;
	
	private static JsonDBConnection instance;
	
	private static ArrayList<User> userList;
	
	private static String dbPath = "/database/member.json";
	private static String absolutePath;
	private static String absoluteDBPath;
	
	private JsonDBConnection() { }
	
	public static JsonDBConnection getInstance(ServletContext context) {
		if (instance == null) {
			 instance = new JsonDBConnection();
			 mContext = context;
			 
			 System.out.println("DB 연결 생성을 시도합니다.");
			 absolutePath = mContext.getRealPath("/");
			 
			 if(!isDatabaseExist())
					createDatabase();
				
				getUsers();				
		}		
		
		return instance;
	}
	
	private static boolean isDatabaseExist() {
		File dir = new File(absolutePath, dbPath);
		absoluteDBPath = dir.getAbsolutePath();
		return dir.exists();
	}
	
	private static void createDatabase() {
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
	
	private static void getUsers() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(absoluteDBPath),"UTF8"));
			Pattern pattern = Pattern.compile("\\{\"id\":\"(.*?)\",\"name\":\"(.*?)\",\"password\":\"(.*?)\"\\}");
			Matcher matcher;
			
			String userData;
			
			userList = new ArrayList<User>();
			
			while ((userData = reader.readLine()) != null) {
				matcher = pattern.matcher(userData);
				
				if(matcher.find()) {
					User user = new User(matcher.group(1), matcher.group(2), matcher.group(3));
					userList.add(user);
				}
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public ArrayList<User> getUserList() {
		return userList;
	}
	
	public void writeDB(User user) {
		userList.add(user);
		
		try {
			BufferedWriter dbFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(absoluteDBPath), "UTF8"));
			
			StringBuilder builder = new StringBuilder("[");
			builder.append(System.getProperty("line.separator"));
			
			Iterator<User> iterator = userList.iterator();
			while(iterator.hasNext()) {
				builder.append(iterator.next().toString());
				builder.append(",");
				builder.append(System.getProperty("line.separator"));				
			}
			
			builder.append(user.toString());
			builder.append(System.getProperty("line.separator"));
			builder.append("]");
			
			dbFile.write(builder.toString());
			
			dbFile.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
