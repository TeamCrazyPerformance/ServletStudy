package javastudy;

import java.util.ArrayList;

class BinaryGap {
	public static int solution(int N){
		int result = 0;
		int count = 0;
		ArrayList<Integer> numList = new ArrayList<Integer>();
		
		//Change to Binary String
		String numString = Integer.toBinaryString(N);
		for(int i=1; i<numString.length(); i++){
			if(numString.charAt(i) == '0'){
				count++;
			}
			else if(numString.charAt(i) == '1'){
				numList.add(count);
				count = 0;
			}
		}
		
		for(int i=0; i<numList.size(); i++){
			if(numList.get(i) >= result){
				result = numList.get(i);
			}
		}
		
		return result;
	}
	public static void main(String[] args){
		
		System.out.println("result : "+solution(1041));
	
	}
}
