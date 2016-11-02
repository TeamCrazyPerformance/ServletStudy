/**
 * Created by eunsoo on 2016-11-02.
 */
class Solution {
    public int solution(int N) {
        // write your code in Java SE 8public class Solution {
        int gap=0;
        int num=0;
        while (N%2 ==0){
            N /= 2;
        }

        while (N !=0){
            if (N % 2 == 1 && num > gap){
                gap = num;
                num = 0;
            }else if (N % 2 == 1){
                num =0;
            }else{
                num++;
            }

            N /= 2;
        }
        return gap;
    }
}