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

import org.apache.tomcat.jni.File;

import com.tcp.study.listener.JsonListener;
import com.tcp.study.util.UserData;
import com.tcp.study.util.UserInfo;

public class SignUpModel {
	//회원가입과 관련된 logic을 담당, 처리
	//1. 회원정보를 받아서 회원가입 (id, name, pw중복확인)
	//2. 회원정보를 json file에 update
	//3. log찍기
	
	public int signUp(UserInfo paramInfo) { //회원가입 및 회원정보 추가(setUserInfo call)
		UserInfo info = new UserInfo();
		List<UserInfo> infoList = UserData.getData().getList();
		int flag; //0 = 성공, 1 = 아이디 미입력, 2 = 아아디 중복, 3 = 이름 미입력, 4 = 비밀번호 미입력
		int index=0;
		paramInfo = this.checkNull(paramInfo);
		
		String id = paramInfo.getId();
		Iterator<UserInfo> i = infoList.iterator();
		while(i.hasNext()){ //ID 중복검사
			info = i.next();
			if(paramInfo.getId()==null){
				flag = 1;
//				setLog(flag);
				return flag;//아이디 미입력
			}
			if(info.getId().equals(paramInfo.getId())){
				flag = 2;
//				setLog(flag);
				return flag;//아이디 중복
			}
			if(paramInfo.getName()==null){
				flag = 3;
//				setLog(flag);
				return flag;//이름 미입력
			}
			if(paramInfo.getPassword()==null){
				flag = 4;
//				setLog(flag);
				return flag;//비밀번호 미입력
			}
		}
		flag = 0;
		setUserInfo(paramInfo);
		
		return flag;
		
	}
	
	public void setUserInfo(UserInfo info){ //회원정보 추가(call by signUp)
		String path = new String("E:\\Github\\ServletStudy\\Jaewon\\SimpleLogin\\resource\\user.txt"); // user file path
		String userInfo[] = new String[1024];
		String buffer = null;
		String newInfo = null;
		int i=0;
		int j=0;
		try {
			BufferedReader read  = new BufferedReader(new FileReader(path));
			while(true){
				buffer = read.readLine();
				if(buffer.equals("]")){
					i--;
					userInfo[i].concat(",");
					break;
				}
				userInfo[i++] = new String(buffer);
			}
			BufferedWriter write = new BufferedWriter(new FileWriter(path));
			while(true){
				write.write(userInfo[j++]);
				write.newLine();
				if(userInfo[j]==null)
					break;
			}
			newInfo = new String("{\"id\":\""+info.getId()+"\",\"name\":\""+info.getName()+"\",\"password\":\""+info.getPassword()+"\"}");
			write.write(newInfo);
			write.newLine();
			write.write("]");
			write.close();
			read.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setLog(int flag){
		//0 = 성공, 1 = 아이디 미입력, 2 = 아아디 중복, 3 = 이름 미입력, 4 = 비밀번호 미입력
		LocalDateTime time = LocalDateTime.now();
		String path = new String("E:\\Java\\workspace\\SimpleLogin\\resource\\log.txt");
		String desc = new String("회원가입 실패");
		String message = new String("회원가입 실패 이유 : ");
//		
//		try {
////			FileWriter input = new FileWriter(path);
//			switch(flag){
//			case 1:
//				message.concat("아이디 미입력");
//			case 2:
//				message.concat("아이디 중복");
//			case 3:
//				message.concat("이름 미입력");
//			case 4:
//				message.concat("비밀번호 미입력");
//			}
////			input.flush();
////			input.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public UserInfo checkNull(UserInfo info){
		if(info.getId().trim().length()==0)
			info.setId(null);
		if(info.getName().trim().length()==0)
			info.setName(null);
		if(info.getPassword().trim().length()==0)
			info.setPassword(null);
		return info;
	}
}