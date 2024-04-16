package org.example.single.search;

import java.util.Arrays;

public class BinarySearch {
    public long binarySearch(int arr[], int x) {


        Arrays.sort(arr);
        long startTime = System.nanoTime();
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            if (arr[m] == x) {
                long endTime = System.nanoTime();
                return endTime - startTime;
            }

            if (arr[m] < x)
                l = m + 1;
            else
                r = m - 1;
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
