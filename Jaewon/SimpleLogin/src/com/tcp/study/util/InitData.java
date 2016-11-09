package com.tcp.study.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitData {

	private static InitData data;
	private List<UserInfo> list = new ArrayList();
	private int count = 0;
	
	private InitData(){
		UserInfo userInfo = new UserInfo();
		String path = new String("E:\\Java\\workspace\\SimpleLogin\\resource\\user.txt");
		String infoList = null;
		String splitList[] = null;
		try {
			BufferedReader read = new BufferedReader(new FileReader(path));
			try {
				infoList = read.readLine();
				while(true){ //3 ID / 5 name / 7 password
					infoList = read.readLine();
					if(infoList.equals("]"))
						break;
					splitList = infoList.split("\"");
					userInfo.setId(splitList[3]);
					userInfo.setName(splitList[5]);
					userInfo.setPassword(splitList[7]);
					list.add(userInfo);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List getList(){
		return list;
	}
	
	public static synchronized InitData getData(){
//		data.count++;
//		System.out.println(data.count);  test
		if(data == null)
			data = new InitData();
		
		return data;
	}
} 
