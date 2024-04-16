package org.example.multi.sort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class PQuickSort {

    public long sort(int[] arr, int numThreads) {

        long startTime = System.nanoTime();
        ForkJoinPool pool = new ForkJoinPool(numThreads);
        QuickSortTask task = new QuickSortTask(arr, 0, arr.length - 1);
        pool.invoke(task);
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    static class QuickSortTask extends RecursiveAction {
        private int[] array;
        private int low;
        private int high;

        public QuickSortTask(int[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (low < high) {
                int pivotIndex = partition(array, low, high);
                QuickSortTask leftTask = new QuickSortTask(array, low, pivotIndex - 1);
                QuickSortTask rightTask = new QuickSortTask(array, pivotIndex + 1, high);

                invokeAll(leftTask, rightTask);
            }
        }

        private int partition(int[] array, int low, int high) {
            int pivot = array[high];
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (array[j] <= pivot) {
                    i++;
                    swap(array, i, j);
                }
            }
            swap(array, i + 1, high);
            return i + 1;
        }

        private void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}

