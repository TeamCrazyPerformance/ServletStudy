package test;

public class BinaryGap {
	public int solution(int N) {
		String binary = Integer.toBinaryString(N);
		int maxLength = 0;
		int temp = 0;
		for (int i = 0; i < binary.length(); i++) {
			if (binary.charAt(i) == '1') {
				if (temp > maxLength)
					maxLength = temp;
				temp = 0;
			} else
				temp++;
		}
		return maxLength;
	}

	public static void main(String[] args) {
		String testString = Integer.toBinaryString(12314);
		System.out.println(testString);
		BinaryGap test = new BinaryGap();
		System.out.println(test.solution(12314));
	}
}
