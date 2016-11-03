package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        System.out.println("result:"+solution(1041));
        System.out.println("----------------------");
        System.out.println("result:"+solution(15));
        System.out.println("----------------------");
        System.out.println("result:"+solution(9));
        System.out.println("----------------------");


    }
    public static int solution(int N) {
        // write your code in Java SE 8
        String binary = Integer.toBinaryString(N);
        int max = 0,count=0, temp_count=0;
        for(int i=0; i<binary.length(); i++){
            System.out.println(binary.charAt(i));
            if(binary.charAt(i)=='1'){
                if(temp_count!=1) {
                    temp_count = 1;
                    System.out.println("temp_count : "+temp_count);
                }
                else if(i!=binary.length() && max<=count) {
                    max = count;
                    count = 0;
                    System.out.println("catch : "+max+" and temp_count : "+temp_count);
                }
                else if(i!=binary.length() && max>=count) {
                    count = 0;
                    System.out.println("reset");
                }
            }
            else if(binary.charAt(i)=='0')
                count++;

        }
        return max;
    }
}
