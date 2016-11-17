
package app;

//you can also use imports, for example:
//import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
//you can write to stdout for debugging purposes, e.g.
//System.out.println("this is a debug message");

class Solution {

	public int solution(int N) {
		ArrayList<Integer> countList = new ArrayList<>();
		int count = 0;
		String Bnum = Integer.toBinaryString(N);
		System.out.println("binary : " + Bnum);
		for (int i = 0; i < Bnum.length(); i++) {
			if (i == Bnum.length() - 1) {
				if (Bnum.substring(i, i + 1).equals("0")) {
					count = 0;
					countList.add(count);
				}
				else{
					countList.add(count);
				}
			} else {
				if (Bnum.substring(i, i + 1).equals("0")) {
					count++;
				}

				else {
					// countList.add(count);
					// count = 0;

					if (count == 0) {
						continue;
					} else {
						countList.add(count);
						count = 0;
					}
				}
			}

		}

		int max=0;
		int current;
		Iterator<Integer> it = countList.iterator();
		while (it.hasNext()) {
			current = it.next();
			System.out.println(current);
			if (current> max) {
				max = current;
			}
		}
		
		System.out.println("max : " + max);
		return max;
	}

	public static void main(String[] args) {
		
		Solution s = new Solution();
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		s.solution(n);

	}

}
