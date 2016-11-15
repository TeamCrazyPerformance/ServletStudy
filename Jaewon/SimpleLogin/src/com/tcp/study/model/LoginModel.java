package com.tcp.study.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import com.tcp.study.util.UserData;
import com.tcp.study.util.UserInfo;

public class LoginModel {
	//

	public int login(UserInfo paramInfo) {
		List<UserInfo> list = UserData.getData().getList();
		UserInfo temp;
		int flag;

		paramInfo = checkNull(paramInfo);

		//0 = 성공, 1 = 존재하지 않는 아이디, 2 = 틀린 비밀번호 - 로그인
		//0 = 성공, 5 = 아이디 미입력, 6 = 아아디 중복, 7 = 이름 미입력, 8 = 비밀번호 미입력 - 회원가입
		Iterator<UserInfo> i = list.iterator();
		while(i.hasNext()){
			temp = i.next();
			if(paramInfo.getId().equals(temp.getId())){
				if(paramInfo.getPassword().equals(temp.getPassword())){
					flag = 0;
					return flag;
				}
				else{
					flag = 2; //비밀번호 오류
					setLog(paramInfo.getId());
					return flag;
				}
			}
		}
		flag = 1; //아이디 오류
		setLog(paramInfo.getId());
		return flag;
	}
	
	private void setLog(String id){
		LocalDateTime time = LocalDateTime.now();
		String path = new String("E:\\Github\\ServletStudy\\Jaewon\\SimpleLogin\\resource\\log.txt");
		String desc = new String("로그인 실패");
		String error = new String("login");
		String logList[] = new String[1024];
		String buffer=null;
		String newLog = null;
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
			newLog = new String("{\"error\":\""+error+"\",\"desc\":\""+desc+"\",\"id\":\""+id+"\",\"time\":\""+time.toString()+"\"}");
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

	private UserInfo checkNull(UserInfo info) {
		if (info.getId().trim().length() == 0)
			info.setId(null);
		if (info.getPassword().trim().length() == 0)
			info.setPassword(null);
		return info;
	}
}
