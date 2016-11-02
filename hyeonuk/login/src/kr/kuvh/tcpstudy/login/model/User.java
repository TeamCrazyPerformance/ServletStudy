package kr.kuvh.tcpstudy.login.model;

public class User {
	private String id;
	private String password;
	private String username;
	
	public User(String id, String username, String password){
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public String getID() {
		return id;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("{\"id\":\"");
		builder.append(this.id);
		builder.append("\",\"name\":\"");
		builder.append(this.username);
		builder.append("\",\"password\":\"");
		builder.append(this.password);
		builder.append("\"}");
		return builder.toString();
	}
}
