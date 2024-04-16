package org.example.multi.search;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class PBinarySearch {

    public long binarySearch(int arr[], int key, int numThreads) {
        long startTime = System.nanoTime();
        ForkJoinPool pool = new ForkJoinPool(numThreads);
        BinarySearchTask task = new BinarySearchTask(arr, key, 0, arr.length - 1);
        int result = pool.invoke(task);
        long endTime = System.nanoTime();

        return endTime - startTime;

    }

    static class BinarySearchTask extends RecursiveTask<Integer> {
        private int[] array;
        private int key;
        private int low;
        private int high;

        public BinarySearchTask(int[] array, int key, int low, int high) {
            this.array = array;
            this.key = key;
            this.low = low;
            this.high = high;
        }

        @Override
        protected Integer compute() {
            if (low <= high) {
                int mid = low + (high - low) / 2;

                if (array[mid] == key) {
                    return mid;
                }

                if (array[mid] < key) {
                    return new BinarySearchTask(array, key, mid + 1, high).compute();
                }

                return new BinarySearchTask(array, key, low, mid - 1).compute();
            }

            return -1; // Элемент не найден
        }
    }
}

