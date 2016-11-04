package study.model;

public class UserData {
	private String name=null;
	private String id=null;
	private String pw=null;
	private String data=null;
	UserData(String data){
		setData(data);
	}
	UserData(String name, String id, String pw, String data){
		setName(name);
		setId(id);
		setPw(pw);
		setData(data);
		
	}
	public void setName(String user_name){ name = user_name; }
	public void setId(String user_id){ id = user_id; }
	public void setPw(String user_pw){ pw = user_pw; }
	public void setData(String user_data){ data = user_data; } 
	public String getName(){return name;}
	public String getId(){return id;}
	public String getPw(){return pw;}
	public String getData(){return data; }
}
