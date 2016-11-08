package com.tcp.study.listener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import com.tcp.study.util.UserInfo;


public class JsonListener implements ServletContextListener {
	private static List<UserInfo> list = new ArrayList();
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		initList();
		System.out.println(list.get(0).getId());
	}
	
	public static List<UserInfo> getUserInfo(){
		System.out.println("log");
		return list;
	}
	
	public void initList(){
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
}
