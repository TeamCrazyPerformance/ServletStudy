package tcp.login.model;

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

import org.json.simple.JSONObject;

import tcp.Util.UserData;
import tcp.register.model.RegisterModel;

public class LoginModel {
	public List getIdList() throws IOException{
		UserData userData = UserData.getInstance();	
		List userDataList = userData.getUserList();
		List idList = new ArrayList();
		
		for(int k = 0; k*3 < userDataList.size(); k++){
			idList.add(userDataList.get(k*3));
		}
		
		return idList;
	}
	
	public void writeList(File file, List list) throws ServletException, IOException {
		Iterator listIt = list.iterator();
		
		if(file.exists() == false){
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			while(listIt.hasNext()){
				writer.write((String)listIt.next()+"\r\n");
			}
			writer.flush();
			writer.close();
		}
		else{
			BufferedWriter writer = new BufferedWriter (new FileWriter(file));
			while(listIt.hasNext()){
				writer.write((String)listIt.next()+"\r\n");
			}
			
			writer.flush();
			writer.close();
		}
	}
	
	public String parseJsonToLog(String userId){
		String loginErrorLog = "";
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		Date currentTime = new Date();
		String time = formatter.format (currentTime);
		
		loginErrorLog = "{\"error\":\"login\",\"desc\":\"로그인실패\",\"id\":\""+userId+"\",\"time\":\""+time+"\"}";
		
		
		return loginErrorLog;
	}
	
	public void writeLoginLog(File log, String userId) throws ServletException, IOException {
		String jsonLog = parseJsonToLog(userId);
		RegisterModel registerModel = new RegisterModel();
		
		if(log.exists() == false){
			registerModel.writeFile(log, "[\r\n"+jsonLog+"\r\n]");
		}
		else {
			/*String jsonData = registerModel.readFile(log);
			jsonData = jsonData.replace("[,", "");
			jsonData = jsonData.replace("],", "");
			jsonData = jsonData.replace(",,", ",\r\n");
			registerModel.writeFile(log, "[\r\n"+jsonData+"\r\n"+jsonLog+"\r\n]");*/
		}
		
		
	}
}
