package com.tcp.study.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogManager {
	private LogManager(){
		
	}
	//0 = 성공, 1 = 공백 아이디, 2 = 존재하지 않는 아이디, 3 = 공백 비밀번호, 4 = 틀린 비밀번호 - 로그인
	//0 = 성공, 5 = 아이디 미입력, 6 = 아아디 중복, 7 = 이름 미입력, 8 = 비밀번호 미입력 - 회원가입
	
	public static void setLog(int flag){
		LocalDateTime time = LocalDateTime.now();
		String path = new String("E:\\Github\\ServletStudy\\Jaewon\\SimpleLogin\\resource\\log.txt");
		String desc = new String("회원가입 실패");
		String error = null;
		String message = null;
		String logList[] = new String[1024];
		String buffer=null;
		String newLog = null;
		switch(flag){
		case 1:
			error = new String("login");
			desc = new String("로그인 실패");
			message = new String("공백 아이디");
			break;
		case 2:
			error = new String("login");
			desc = new String("로그인 실패");
			message = new String("존재하지 않는 아이디");
			break;
		case 3:
			error = new String("login");
			desc = new String("로그인 실패");
			message = new String("공백 비밀번호");
			break;
		case 4:
			error = new String("login");
			desc = new String("로그인 실패");
			message = new String("틀린 비밀번호");
			break;
		case 5:
			error = new String("sign up");
			desc = new String("회원가입 실패");
			message = new String("아이디 미입력");
			break;	
		case 6:
			error = new String("sign up");
			desc = new String("회원가입 실패");
			message = new String("아이디 중복");
			break;		
		case 7:
			error = new String("sign up");
			desc = new String("회원가입 실패");
			message = new String("이름 미입력");
			break;			
		case 8:
			error = new String("sign up");
			desc = new String("회원가입 실패");
			message = new String("비밀번호 미입력");
			break;
		}
		try {
			BufferedReader read = new BufferedReader(new FileReader(path));
			int i=0;
			while(true){
				buffer = read.readLine();
				if(buffer.equals("]")){
					i--;
					logList[i] = logList[i].concat(",");
					break;
				}
				logList[i++] = new String(buffer);
			}
			BufferedWriter write = new BufferedWriter(new FileWriter(path));
			int j=0;
			while(true){
				write.write(logList[j++]);
				write.newLine();
				if(logList[j]==null)
					break;
			}
			newLog = new String("{\"error\":\""+error+"\",\"desc\":\""+desc+"\",\"message\":\""+message+"\",\"time\":\""+time.toString()+"\"}");
			write.write(newLog);
			write.newLine();
			write.write("]");
			write.close();
			read.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
