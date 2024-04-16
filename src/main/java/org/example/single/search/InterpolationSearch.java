package org.example.single.search;

public class InterpolationSearch {
    public long interpolationSearch(int arr[], int x) {
        long startTime = System.nanoTime();

        int lo = 0, hi = (arr.length - 1);

        while (lo <= hi && x >= arr[lo] && x <= arr[hi]) {
            if (lo == hi) {
                if (arr[lo] == x) {
                    long endTime = System.nanoTime();
                    return endTime - startTime;
                }
                long endTime = System.nanoTime();
                return endTime - startTime;
            }

            int pos = lo + (((hi-lo) / (arr[hi]-arr[lo]))*(x - arr[lo]));

            if (arr[pos] == x) {
                long endTime = System.nanoTime();
                return endTime - startTime;
            }

            if (arr[pos] < x)
                lo = pos + 1;
            else
                hi = pos - 1;
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
