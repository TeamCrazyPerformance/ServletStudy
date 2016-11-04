package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserModel {
	private static UserModel instance;
	private List<User> users;
	private Path path;
	private UserModel() throws IOException{
		users = new LinkedList<User>();
		users.add(new User());
		path = Paths.get("C:/Users/samsung/Documents/git/login/Kwoo/login/resource/users.txt");
		read(Files.readAllLines(path, StandardCharsets.UTF_8)); // json file parsing
		
	}

	public static synchronized UserModel getInstance() throws IOException{
		if(instance == null){
			instance = new UserModel();
		}
		return instance;
	}
	
	public List<User> getUsers(){
		return users;
	}
	
	void read(List<String> jsonText){
		User user = null;
		Iterator<String> it = jsonText.iterator();
		while(it.hasNext()){
			String line = it.next();
			
			if(line.contains("{")){
				user = new User();
			}
			if(line.contains(":")){
				String[] data = line.split("\"");

				for(int i = 0; i < data.length; i++){
					if(data[i].equals(":")){
						String attribute = data[i-1];	// ':' 이전에 오는 값
						String value = data[i+1];		// ':' 이후에 오는 값
						switch(attribute){
						case "id":
							user.setId(value);
							break;
						case "passwd":
							user.setPasswd(value);
							break;
						case "name":
							user.setName(value);
							break;
						}
					}
				}
			}
			if(line.contains("}")){
				this.users.add(user);
			}		
		}
	}

	public void addUser(User newUser) throws IOException{
		List<String> userLines = Files.readAllLines(path);
		BufferedWriter userWriter = Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING);

		if(userLines.isEmpty()){
			userLines.add("[");
			userLines.add("{");
		}
		userLines.replaceAll(s->s.replace("]", ",{"));
		userLines.add("\"id\":\""+newUser.getId()+"\",");
		userLines.add("\"passwd\":\""+newUser.getPasswd()+"\",");
		userLines.add("\"name\":\""+newUser.getName()+"\"");
		userLines.add("}");
		userLines.add("]");
		Iterator<String> it = userLines.iterator();
		while(it.hasNext()){
			userWriter.append(it.next());
			userWriter.newLine();
		}
		userWriter.close();
		users.add(newUser);
	}
}

class User{
	private String id;
	private String passwd;
	private String name;
	public User(){
		id = null;
		passwd = null;
		name = null;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public void setName(String name) {
		this.name = name;
	}
}
