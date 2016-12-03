package Arrays;

import java.util.Stack;

/**
 * Created by Sonkrat on 2016. 11. 20..
 */
public class OddOccurrencesInArray {
//    public int solution(int[] A) {
//        // write your code in Java SE 8
//        int result = 0;
//        int size = A.length;
//        int[] temp = new int[size];
//
//        for (int i = 0; i < size; i++) {
//            result += A[i];
//            for (int j = 0; j < i; j++) {
//                if (A[i] == A[j]) {
//                    result -= A[i] * 2;
//                    A[i] = A[j] = 0;
//                }
//            }
//        }
//        return result;
//    }

    // 결국 찾아봤지만.. 어떻게 이런생각을....?
    public int solution(int[] A) {
        int odd = 0;
        for (int n : A) {
            odd ^= n;
        }

        return odd;
    }
}