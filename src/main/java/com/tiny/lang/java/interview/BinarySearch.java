package com.tiny.lang.java.interview;

public class BinarySearch {

    public static void main(String[] args) {
        var arr = new int[]{1, 2, 4, 5, 6, 8, 9, 19, 23, 45, 65};
        System.out.println(search(arr, 7));
        System.out.println(search(arr, 9));
        System.out.println(search(arr, 45));
    }

    private static int search(int[] arr, int target) {
        var left = 0;
        var right = arr.length - 1;

        while (left <= right) {
            var mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

}
