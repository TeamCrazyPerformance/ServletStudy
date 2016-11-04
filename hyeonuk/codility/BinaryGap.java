import java.util.*;

class Solution {
    public int solution(int N) {
		int n = N;
		int countTemp = 0;
		int count = 0;
		boolean isFirstZero = (n % 2 == 0);
		
		while(n > 0) {
			switch(n % 2) {
			case 0:
				countTemp++;
				break;
			case 1:
				if(countTemp > count && !isFirstZero)
					count = countTemp;
				countTemp = 0;
				isFirstZero = false;
				break;
			}
			
			n = n / 2;
		}
		
		return count;
	}
}