package com.tiny.lang.java.interview;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(solve1(4));
        System.out.println(solve2(4));
    }

    private static int solve1(int num) {
        if (num <= 1) {
            return num;
        }

        return solve1(num - 1) + solve1(num - 2);
    }

    private static int solve2(int num) {
        if (num <= 1){
            return num;
        }
        
        int l, m = 0, r = 1;
        for (int i = 2; i <= num; i++) {
            l = m;
            m = r;
            r = l + m;
        }

        return r;
    }
}
