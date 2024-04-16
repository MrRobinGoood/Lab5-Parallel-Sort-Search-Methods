package org.example.multi.sort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class PHeapSort {

    public long sort(int[] arr, int numThreads) {

        long startTime = System.nanoTime();
        ForkJoinPool pool = new ForkJoinPool(numThreads);
        HeapSortTask task = new HeapSortTask(arr, arr.length);
        pool.invoke(task);
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    static class HeapSortTask extends RecursiveAction {
        private int[] array;
        private int n;

        public HeapSortTask(int[] array, int n) {
            this.array = array;
            this.n = n;
        }

        @Override
        protected void compute() {
            buildMaxHeap(array, n);
            for (int i = n - 1; i >= 0; i--) {
                swap(array, 0, i);
                maxHeapify(array, i, 0);
            }
        }

        private void buildMaxHeap(int[] array, int n) {
            for (int i = n / 2 - 1; i >= 0; i--) {
                maxHeapify(array, n, i);
            }
        }

        private void maxHeapify(int[] array, int n, int i) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && array[left] > array[largest]) {
                largest = left;
            }

            if (right < n && array[right] > array[largest]) {
                largest = right;
            }

            if (largest != i) {
                swap(array, i, largest);
                maxHeapify(array, n, largest);
            }
        }

        private void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
