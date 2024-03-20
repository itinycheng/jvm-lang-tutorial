package com.tiny.lang.java.interview;

import java.util.Arrays;

public class Backtrace {

    public static void main(String[] args) {
        var arr = new String[]{"a", "b", "c"};
        backtrack(arr, 0);
    }

    private static <T> void backtrack(T[] arr, int idx) {
        if (arr.length - 1 == idx) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            swap(arr, i, idx);
            backtrack(arr, idx + 1);
            swap(arr, i, idx);
        }
    }

    private static <T> void swap(T[] arr, int i, int idx) {
        var tmp = arr[i];
        arr[i] = arr[idx];
        arr[idx] = tmp;
    }


}
