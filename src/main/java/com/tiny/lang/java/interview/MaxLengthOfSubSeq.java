package com.tiny.lang.java.interview;

/**
 * name.
 */
public class MaxLengthOfSubSeq {

    public static void main(String[] args) {
        var arr = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int max = maxSubSeqLength(arr);
        System.out.println(max);
    }

    private static int maxSubSeqLength(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        if (arr.length == 1) {
            return 1;
        }

        int max = 1;
        int[] ints = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ints[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    ints[i] = Math.max(ints[i], ints[j] + 1);
                }
            }

            max = Math.max(max, ints[i]);
        }

        return max;
    }
}
