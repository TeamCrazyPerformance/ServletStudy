class Solution{
	static int solution(int N){
		int count = 0, rcount = 0, check = 0;
		whlie(N != 1){
			if(N % 2 == 1){
				N = N / 2;
				count = 0;
				check++; 
			}
			else if(N % 2 == 0 && check == 0){
				N = N / 2;
			}
			else{
				N = N /2;
				count++;
			}
			
			if(N % 2 == 1 && count > rcount){
				rcount = count;
			}
		}
	}

	private static void whlie(boolean b) {
		// TODO Auto-generated method stub
		
	}
}