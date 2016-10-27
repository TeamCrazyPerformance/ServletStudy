package algorithm;

public class binaryGap {
	public int solution(int N){
		int gap = 0;
		int higher = 0;
		boolean isFirst = true;

		while(N!=0){
			if((N%2)==1){
				if(gap>higher&&(!isFirst)) higher = gap;
				gap = 0;
				N = (int)(N/2);
				isFirst = false;
			}
			else{
				gap++;
				N = (int)(N/2);
			}
		}

		return higher;	
	}
}
