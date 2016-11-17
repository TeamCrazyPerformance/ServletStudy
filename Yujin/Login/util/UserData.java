package tcp.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//파일을 읽어오기
//JSON.java로 보내서 리스트에 담아오기
//밖으로 전달
public class UserData {
	private static UserData userData = new UserData();
	public static UserData getInstance(){
		return userData;
	}
	
	public File readFile(){
		File file = new File("C:/Users/dong9/Desktop/user.txt");
		return file;
	}
	
	public List getUserList() throws IOException{
		List userDataList = new ArrayList();
		
		JSON jSON = new JSON();
		userDataList = jSON.parseJsonToList(readFile());	
		
		return userDataList;
	}
	
	public File getFile(){
		return readFile(); 
	}
}
