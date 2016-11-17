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
	//id, name, pwd�� �޾Ƽ� json ������ String���� ����
	public String stringToJson(String id, String name, String pwd){
		return "{\"id\":\""+id+"\",\"name\":\""+name+"\",\"password\":\""+pwd+"\"}";
	}

	//BufferedWriter�� �̿��� ó������ ������ ���ο� �������� ä���
	public void writeFile(File file, String jsonString) throws ServletException, IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		String newData = jsonString;
		writer.write(newData, 0, newData.length());
		writer.flush();
		writer.close();
	}
	
	//user.txt�� �������� ������ ���Ӱ� �����ϰ�, �����ϸ� ���� ���� ���� �ӽ� ���� ���� ���� ��ü ������ ��ü�Ѵ�.
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
			//�ߺ��˻�. jsonData�� id ����� jsonString�� id ���� ���Ͽ� ��ġ�ϴ� ���� ������ "���� �޼���"�� ��� �Լ� ����
			
			List idList = new ArrayList();
			idList = loginModel.getIdList();
			Iterator idListIt = idList.iterator();
			
			while(idListIt.hasNext()){
				if(id.equals(idListIt.next())) return "failed";
			}
			
			//������ jsonData�� ���ȣ �ȿ� ���ο� updateData�� �ֱ� ���� ���ȣ ����
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
	
	//List�� ���� error�� json ������ String���� ��ȯ
	public String writeLogJsonString(List error){
		Iterator i = error.iterator();
		String jsonString = "";
		
		//�ð� String time
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		Date currentTime = new Date();
		String time = formatter.format (currentTime);
		
		//error�� List�� �����Ǿ� ������ Log���� ���� ���� List�� �� error�� ����ϵ��� ����
		jsonString = "{\"error\":\"sign up\",\"desc\":\"ȸ������ ����\",\"message\":\""+i.next()+"\",\"time\":\""+time+"\"}";
		
		return jsonString;
	}
	
	//�α� ����
	public void writeLog(List error) throws ServletException, IOException{
		File log = new File("log.txt"); //log.txt ��� �Է�
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
