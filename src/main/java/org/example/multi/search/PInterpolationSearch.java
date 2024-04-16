package org.example.multi.search;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class PInterpolationSearch {

    public long interpolationSearch(int[] arr,int key, int numThreads) {
        long startTime = System.nanoTime();
        ForkJoinPool pool = new ForkJoinPool(numThreads);
        InterpolationSearchTask task = new InterpolationSearchTask(arr, key, 0, arr.length - 1);
        int result = pool.invoke(task);
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    static class InterpolationSearchTask extends RecursiveTask<Integer> {
        private int[] array;
        private int key;
        private int low;
        private int high;

        public InterpolationSearchTask(int[] array, int key, int low, int high) {
            this.array = array;
            this.key = key;
            this.low = low;
            this.high = high;
        }

        @Override
        protected Integer compute() {
            if (low <= high && key >= array[low] && key <= array[high]) {
                int position = low + ((high - low) / (array[high] - array[low]) * (key - array[low]));

                if (array[position] == key) {
                    return position;
                }

                if (array[position] < key) {
                    return new InterpolationSearchTask(array, key, position + 1, high).compute();
                }

                return new InterpolationSearchTask(array, key, low, position - 1).compute();
            }

            return -1; // Элемент не найден
        }
    }
}

