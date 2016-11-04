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

public class SignUpModel {
	private Path errPath;
	private String errMsg;
	public SignUpModel() {
		errPath = Paths.get("C:\\Users\\samsung\\Documents\\git\\login\\Kwoo\\login\\resource\\error.txt");
		errMsg = "";
	}
	
	public boolean signUp(String id, String name, String passwd) throws IOException{
		User temp;
		User newUser = new User();
		Iterator<User> userIt = null;
		if(!(UserModel.getInstance().getUsers()==null)){
			userIt = UserModel.getInstance().getUsers().iterator();
		}
		while(userIt.hasNext()){
			temp=userIt.next();
			if(id.equals(temp.getId())){
				errMsg = "아이디중복";
				signUpErr();
				return false;
			}
			else if(id.equals("")){
				errMsg = "아이디 공백";
				signUpErr();
				return false;
			}
			else if(name.equals("")){
				errMsg = "이름 공백";
				signUpErr();
				return false;
			}
			else if(passwd.equals("")){
				errMsg = "비밀번호 공백";
				signUpErr();
				return false;
			}
		}
		newUser.setId(id);
		newUser.setName(name);
		newUser.setPasswd(passwd);
		UserModel.getInstance().addUser(newUser);
		
		return true;
	}
	
	private void signUpErr() throws IOException{
		LocalDateTime timePoint = LocalDateTime.now();
		String time = timePoint.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		List<String> errLines  = Files.readAllLines(errPath);
		BufferedWriter errWriter = Files.newBufferedWriter(errPath,StandardOpenOption.TRUNCATE_EXISTING);
		if(errLines.isEmpty()){
			errLines.add("[");
			errLines.add("{");
		}
		errLines.replaceAll(s->s.replace("]", ",{"));
		errLines.add("\"error\":\"sign up\",\"desc\":\"회원가입실패\",\"message\":\""+errMsg+"\",\"time\":\""+time+"\"");
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
