package com.genie.login.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.util.List;


public class FileUtil {

	public List readFile(){
		
		URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
		File f = new File(url+"/user.txt");
		Writer writer = null;
		if (!f.isFile()) {
			System.out.println("non file");
			try {
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static void main (String args[]) {
		FileUtil util = new FileUtil();
		util.readFile();
	}
}
