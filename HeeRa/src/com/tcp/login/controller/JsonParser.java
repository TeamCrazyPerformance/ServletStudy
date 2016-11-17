package com.tcp.login.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletContext;


/**
 * JSON file을 읽고 써주는 클래스
 * 
 * @author 김희라
 *
 */
public class JsonParser {

	public static JsonParser Instance = null;

	private static String jsonParser = ",";
	private static String jsonParser2 = ":";

	private Hashtable<String, Hashtable<String,String>> list;
	private String userPath;
	
	private String filePath = "/WEB-INF/user.txt";
	private String logPath = "/WEB-INF/log.txt";



	public static JsonParser getInstance(ServletContext cntxt) {
		if (Instance == null) {
			Instance = new JsonParser(cntxt);
		} 
		return Instance;
	}
	
	JsonParser(ServletContext cntxt) {
		filePath = cntxt.getRealPath(filePath);
		System.out.println(filePath);
		jsonReader(filePath);
		logPath = cntxt.getRealPath(logPath);
	}

	public void setPath(String path) {
		this.userPath = path;
	}

	public Hashtable<String, Hashtable<String,String>> getList () {
		return list;
	}


	/**
	 * 회원 가입시 사용자의 정보를 json 파일에 저장해주는 메서드
	 * @param id
	 * @param pw
	 * @param name
	 */
	public void jsonWriter(String id, String pw, String name) {

		try (
				OutputStreamWriter ow = new OutputStreamWriter( new FileOutputStream(filePath, true), "UTF-8");
				BufferedWriter bw = new BufferedWriter(ow);
				PrintWriter out = new PrintWriter(bw))

		{
			String str = "\r\n" + "{\"ID\":\"" + id + "\",\"PW\":\"" + pw + "\",\"NAME\":\"" + name + "\"}," ;

			bw.write(str);
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 로그인이나 회원가입 실패시 log를 남기는 메서드
	 * @param err	"login" || "sign up"
	 * @param mesg	"id" || err mesg
	 * @param time	current time
	 */
	public void logWrite(String err, String mesg, String time) {
		try (
				OutputStreamWriter ow = new OutputStreamWriter( new FileOutputStream(logPath, true), "UTF-8");
				BufferedWriter bw = new BufferedWriter(ow);
				PrintWriter out = new PrintWriter(bw))

		{
			String str = null ;

			switch (err) {
			case "login" :
				str = "\r\n" + "{\"error\":\"" + err + "\",\"ID\":\"" + mesg + "\",\"time\":\"" + time + "\"}," ;
				break;
			case "sign up":
				str = "\r\n" + "{\"error\":\"" + err + "\",\"message\":\"" + mesg + "\",\"time\":\"" + time + "\"}," ;
				break;
			default :
				str = "error";
				break;
			} 
			bw.write(str);
			bw.close();	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	/**
	 * 사용자 정보가 담긴 json 형식의 파일을 읽어 Hashtable에 저장
	 * @param filePath
	 * @return
	 */
	public void jsonReader (String filePath) {
		Hashtable<String, Hashtable<String,String>> setList = new Hashtable<>();

		/*
		 *  브이오, user~깔깔깔ㄹㄹㄹ
		 *
		 */

		String input;

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader (filePath));

			while((input = br.readLine()) != null) {
				//			String[] data = input.split(jsonParser);
				String[] data = input.substring(1, input.length()-2).split(jsonParser);

				Hashtable<String,String> putList = new Hashtable<>();

				for(int i =0; i<data.length; i ++ ) {

					String[] tmp = data[i].split(jsonParser2);
					putList.put( tmp[0].substring(1, tmp[0].length()-1), tmp[1].substring(1, tmp[1].length()-1));

					//				putList.put(data[i], data[i+1]);
				}

				setList.put(putList.get("ID"), putList);

			}
			br.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("File Read");
		list = setList;
	}

}
