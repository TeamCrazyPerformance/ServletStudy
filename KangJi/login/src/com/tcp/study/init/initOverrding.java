package com.tcp.study.init;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class initOverrding extends GenericServlet{
	public void init(){
		String path = "C:\\Users\\kjhmd\\Desktop\\ServletStudy\\KangJi\\login\\User.txt";
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap<String, String> idpw = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> idpw_list = new ArrayList<HashMap<String, String>>();
		ArrayList<String> name_list = new ArrayList<String>();
		ArrayList<String> id_list = new ArrayList<String>();
		
		while(true){
			String line = null;
			try {
				line = br.readLine();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(line == null) break;
			if(line == "]") break;
			String[] trash1 = line.split(":\"");
			String[] userid = line.split("\"");
			String[] trash2 = line.split(":\"");
			String[] userpw = line.split("\"");
			String[] trash3 = line.split(":\"");
			String[] username = line.split("\"");
			
			idpw.put(userid[0], userpw[0]);
			idpw_list.add(idpw);
			name_list.add(username[0]);
			id_list.add(userid[0]);
		}
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}