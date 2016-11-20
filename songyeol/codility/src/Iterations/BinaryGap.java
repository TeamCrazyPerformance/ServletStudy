package Iterations;

import java.util.Scanner;

/**
 * Created by Sonkrat on 2016. 10. 25..
 */
public class BinaryGap {

    static int solution(int N) {
//        int size = (int)Math.ceil(Math.sqrt(N));
//        int[] binary = new int[N];
//        int i = 0;
        int check = 0;
        int result = 0;
        boolean start = false;

        while (N>0) {
//            binary[i] = N % 2;
            int num = N % 2;
            N = N/2;

            if (num == 1 && start) {
                result = check > result ? check : result;
                check = 0;
            }
            else if (num == 1 && !start) {
                check = 0;
                start = true;
            }
            else if (num == 0 && start) {
                check++;
            }
//            i++;
        }

        return result;
    }

    public static void main(String[] args) {
        while(true) {
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            System.out.println(solution(num));
        }
    }
}
