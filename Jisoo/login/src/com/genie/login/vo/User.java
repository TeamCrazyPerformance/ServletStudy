package com.genie.login.vo;

public class User {

	private String id;
	private String name;
	private String password;
	
	public User(String id, String name, String password) {
		if (id == null){
			throw new NullPointerException();
		}
		if (name == null){
			throw new NullPointerException();
		}
		if (password == null){
			throw new NullPointerException();
		}
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
}
