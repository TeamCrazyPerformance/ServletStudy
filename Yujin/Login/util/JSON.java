package tcp.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSON {
	public List parseJsonToList(File file) throws IOException{
		List line = new ArrayList();
		Iterator lineIt = line.iterator();
		String buffer = "";
		String lineString = "";
		int j=0;
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while((buffer = reader.readLine()) != null){
			line.add(buffer);
		}
		reader.close();
		
		List list = new ArrayList();
		Iterator listIt = list.iterator();
		while(lineIt.hasNext()){
			lineString = (String)line.get(j);
			String[] splitString = lineString.split("\"");
			if (splitString.length > 1){
				for(int i=1; 4*i-1<splitString.length; i++){
					list.add(splitString[4*i-1]);
				}
			}
			j++;
		}
		
		return list;
	}
}
