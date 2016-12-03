package Arrays;

/**
 * Created by Sonkrat on 2016. 11. 20..
 */

class CycleRotation {
    public int[] solution(int[] A, int K) {
        // write your code in Java SE 8
        int size = A.length;
        int [] result = new int[size];
        int idx = K;

        for (int n : A) {
            result[(idx++)%size] = n;
        }

        return result;
    }
}