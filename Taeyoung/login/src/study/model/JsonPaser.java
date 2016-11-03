package study.model;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class JsonPaser {
	private int object_count = 0;
	private List<UserData> list;
	

	
	public JsonPaser() {
		// TODO Auto-generated constructor stub
	}

	public List<UserData> getUserData(){
		FileControll fc = new FileControll();
		try {
			list = fc.file_Read();
			find_data();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	private void find_data() throws IOException{
		Pattern pattern = Pattern.compile("\\{\"id\":\"(.*?)\",\"name\":\"(.*?)\",\"password\":\"(.*?)\"\\}");
		int i = 1;
		while(i<list.size()-1){
			Matcher match = pattern.matcher(list.get(i).getData());
			if(match.find()){
				System.out.println(match.group(1));
				System.out.println(match.group(2));
				System.out.println(match.group(3));
				list.get(i).setId(match.group(1));
				list.get(i).setName(match.group(2));
				list.get(i).setPw(match.group(3));
			}
			i++;
		}
	}
	//{"id":"genie","name":"Youn Ji Soo","password":"password1"}
	private void makeString(int location){
		String json_data = "\\{\"id\":\""+list.get(location).getId()+"\""
				+ ",\"name\":\""+list.get(location).getName()+"\""
				+ ",\"password\":\""+list.get(location).getPw()+"\"\\}";
		list.get(location).setData(json_data);
	}
}
