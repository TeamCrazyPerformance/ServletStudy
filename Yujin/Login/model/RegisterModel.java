package tcp.register.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import tcp.Util.UserData;
import tcp.login.model.LoginModel;

public class RegisterModel {
	//id, name, pwd를 받아서 json 형태의 String으로 리턴
	public String stringToJson(String id, String name, String pwd){
		return "{\"id\":\""+id+"\",\"name\":\""+name+"\",\"password\":\""+pwd+"\"}";
	}

	//BufferedWriter를 이용해 처음부터 끝까지 새로운 내용으로 채우기
	public void writeFile(File file, String jsonString) throws ServletException, IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		String newData = jsonString;
		writer.write(newData, 0, newData.length());
		writer.flush();
		writer.close();
	}
	
	//user.txt가 존재하지 않으면 새롭게 생성하고, 존재하면 원래 값과 합쳐 임시 값을 만든 다음 전체 내용을 교체한다.
	public String registerUser(String id, String name, String pwd) throws ServletException, IOException {
		UserData userData = UserData.getInstance();
		LoginModel loginModel = new LoginModel();
		List userDataList = userData.getUserList();
		
		String result = "";
		String jsonString = stringToJson(id, name, pwd);
		
		
		if(userDataList.isEmpty() == true){
			writeFile(userData.getFile(), "[\r\n"+jsonString+"\r\n]");
		}
		else{
			//중복검사. jsonData의 id 값들과 jsonString의 id 값을 비교하여 일치하는 것이 있으면 "에러 메세지"를 뱉고서 함수 종료
			
			List idList = new ArrayList();
			idList = loginModel.getIdList();
			Iterator idListIt = idList.iterator();
			
			while(idListIt.hasNext()){
				if(id.equals(idListIt.next())) return "failed";
			}
			
			//원형인 jsonData의 대괄호 안에 새로운 updateData를 넣기 위해 대괄호 삭제
			/***********************************************
			jsonData = jsonData.replace("[,", "");
			
			jsonData = jsonData.replace("],", "");
			jsonData = jsonData.replace(",,", ",\r\n");
			
			writeFile(userData, "[\r\n"+jsonData+"\r\n"+jsonString+"\r\n]");
			************************************************/
			
			result = "success";
		}
		
		return result;
	}
	
	//List로 받은 error를 json 형식의 String으로 반환
	public String writeLogJsonString(List error){
		Iterator i = error.iterator();
		String jsonString = "";
		
		//시간 String time
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		Date currentTime = new Date();
		String time = formatter.format (currentTime);
		
		//error는 List로 구성되어 있지만 Log에는 가장 먼저 List에 들어간 error만 출력하도록 했음
		jsonString = "{\"error\":\"sign up\",\"desc\":\"회원가입 실패\",\"message\":\""+i.next()+"\",\"time\":\""+time+"\"}";
		
		return jsonString;
	}
	
	//로그 쓰기
	public void writeLog(List error) throws ServletException, IOException{
		File log = new File("log.txt"); //log.txt 경로 입력
		String jsonString = "";
		if(log.exists() == false){
			jsonString = "[\r\n"+writeLogJsonString(error)+"\r\n]";
			writeFile(log, jsonString);
		}
		else{
			/*String jsonData = readFile(log);
			jsonData = jsonData.replace("[,", "");
			jsonData = jsonData.replace("],", "");
			jsonData = jsonData.replace(",,", ",\r\n");
			writeFile(log, "[\r\n"+jsonData+",\r\n"+jsonString+"\r\n]");*/
		}
	}
	
	
	
}
