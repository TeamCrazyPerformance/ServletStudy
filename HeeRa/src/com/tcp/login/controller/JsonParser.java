package com.tcp.login.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Hashtable;

public class JsonParser {
	
	public static JsonParser Instance = null;
	
	private static String jsonParser = ",";
	private static String jsonParser2 = ":";

	
	
	public static JsonParser getInstance() {
		if (Instance == null) {
			Instance = new JsonParser();
		} 
		return Instance;
	}
	/*FileWriter fw = new FileWriter(filePath, true);*/
	public void jsonWriter(String id, String pw, String name, String filePath) {
		
		try (
				OutputStreamWriter ow = new OutputStreamWriter( new FileOutputStream(filePath, true), "UTF-8");
			    BufferedWriter bw = new BufferedWriter(ow);
			    PrintWriter out = new PrintWriter(bw))
		
		{
			String str = "\r\n" + "{\"ID\":\"" + id + "\",\"PW\":\"" + pw + "\",\"NAME\":\"" + name + "\"}," ;
			
			bw.write(str);
			bw.close();
//			out.println(str);
			
//			br = new BufferedWriter( new FileWriter(filePath)) ;
//			String str = "{\"ID\":\"" + id + "\",\"PW\":\"" + pw + "\",\"NAME\":\"" + name + "\"}," + "\r\n";
//			
//			br.close();
//			System.out.println("file write");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void logWrite(String err, String mesg, String time, String path) {
		try (
				OutputStreamWriter ow = new OutputStreamWriter( new FileOutputStream(path, true), "UTF-8");
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
		} 
		
		
		
		bw.write(str);
		bw.close();	
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public Hashtable<String, Hashtable<String,String>> jsonReader (String filePath) {
		Hashtable<String, Hashtable<String,String>> setList = new Hashtable<>();
		
		/*
		 *  ∫Í¿Ãø¿, user~±Ú±Ú±Ú§©§©§©
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
		return setList;
		
		
	}

}
