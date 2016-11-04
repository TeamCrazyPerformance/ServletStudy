package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class LoginModel {
	private Path errPath;
	private String errMsg;
	public LoginModel() {
		errPath = Paths.get("C:\\Users\\samsung\\Documents\\git\\login\\Kwoo\\login\\resource\\error.txt");
		errMsg="";
	}
	public boolean login(String id, String passwd) throws IOException{
		Iterator<User> it = UserModel.getInstance().getUsers().iterator();
		User temp;
		while(it.hasNext()){
			temp = it.next();
			if(id.equals(temp.getId())){
				if(passwd.equals(temp.getPasswd())){
					return true;
				}
				else{
					errMsg="옳지 않은 비밀번호";
					loginErr(temp.getId());
					return false;
				}
			}
		}
		errMsg="없는 아이디";
		loginErr(null);
		return false;
	}
	
	
	private void loginErr(String id) throws IOException{
		LocalDateTime timePoint = LocalDateTime.now();
		String time = timePoint.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		List<String> errLines  = Files.readAllLines(errPath);
		BufferedWriter errWriter = Files.newBufferedWriter(errPath,StandardOpenOption.TRUNCATE_EXISTING);
		if(errLines.isEmpty()){
			errLines.add("[");
			errLines.add("{");
		}
		errLines.replaceAll(s->s.replace("]", ",{"));
		errLines.add("\"error\":\"login\", \"desc\":\"로그인실패\", \"id\":\""+id+"\", \"message\":\""+errMsg+"\",\"time\":\""+time+"\"");
		errLines.add("}");
		errLines.add("]");
		Iterator<String> it = errLines.iterator();
		while(it.hasNext()){
			errWriter.append(it.next());
			errWriter.newLine();
		}
		errWriter.close();
	}

	public String getErrMsg(){
		return errMsg;
	}
}
