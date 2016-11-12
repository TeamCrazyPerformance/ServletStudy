package com.tcp.study.userio;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class UserIo extends HttpServlet {
	public void init (ServletConfig config) throws ServletException{
		try {
			String path = new String("C:\\Users\\kjhmd\\Desktop\\ServletStudy\\KangJi\\login\\User.txt");
			FileWriter fw2 = new  FileWriter(path, true);
			
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
