package com.tcp.study.util;

public class JsonParser {
	public void parseJson(String data){
		String[] lines = data.split(",");
		for(int i= 0; i<lines.length;i++){
			String[] words = lines[i].split(":");
			for(int j=0;j<words.length;j++){
				System.out.println(words[j]);
			}
		}
	}
	public String stringfyJson(String name, String id, String password){
		String data = "{" + "�̸�" + ":" + "\"" + name + "\"" + "," + "���̵�" + ":" + "\"" + id +"\"" +"," + "��й�ȣ" + ":" + "\"" + name + "\"" +"}";
		return data;
	}
}
