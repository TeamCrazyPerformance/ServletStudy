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
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;

import javax.servlet.ServletContext;

public class Util {
	private static String logPath = "/database/log.json";
	private static String absolutePath;
	private static String absoluteLogPath;
	
	ServletContext mContext;
	
	public Util(ServletContext context) {
		mContext = context;
		
		absolutePath = mContext.getRealPath("/");
				
		if(!isLogFileExist())
			createLogFile();
	}
	
	private boolean isLogFileExist() {
		File dir = new File(absolutePath, logPath);
		absoluteLogPath = dir.getAbsolutePath();
		return dir.exists();
	}
	
	private void createLogFile() {
		File dir = new File(absolutePath, "database");
		if (!dir.exists())
			dir.mkdir();
				
		try {
			File file = new File(absoluteLogPath);
			file.createNewFile();
			
			BufferedWriter logFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(absoluteLogPath), "UTF8"));
			logFile.write("[]");
			
			logFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void logErrorLogin(String id) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		String line = "{\"error\":\"login\",\"desc\":\"로그인실패\",\"id\":\""
						+ id
						+ "\",\"time\":\"" + dateFormat.format(calendar.getTime()) + "\"}";
		
		logError(line);
	}
	
	public void logErrorSignUp(String msg) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		String line = "{\"error\":\"login\",\"desc\":\"회원가입 실패\",\"message\":\""
						+ msg
						+ "\",\"time\":\"" + dateFormat.format(calendar.getTime()) + "\"}";
		
		logError(line);
	}
	
	private void logError(String logLine) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(absoluteLogPath),"UTF8"));
			
			String line = "";
			String temp = "";
			StringBuilder builder = new StringBuilder("[");
			
			reader.readLine();
			
			while ((temp = reader.readLine()) != null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
				line = temp;
			}
			
			if(!line.equals(""))
				builder.append(",");
			else
				builder.append(System.getProperty("line.separator"));
			builder.append(logLine);
			builder.append(System.getProperty("line.separator"));
			builder.append("]");
			
			BufferedWriter logFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(absoluteLogPath), "UTF8"));
			
			logFile.write(builder.toString());
			
			logFile.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String encryptPassword(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
}
