package org.example.multi.sort;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;
import java.util.Arrays;

public class PMergeSort {

    public long sort(int[] arr, int numThreads) {

        long startTime = System.nanoTime();
        ForkJoinPool pool = new ForkJoinPool(numThreads);
        MergeSortTask task = new MergeSortTask(arr, 0, arr.length - 1);
        pool.invoke(task);
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    static class MergeSortTask extends RecursiveAction {
        private int[] array;
        private int left;
        private int right;

        public MergeSortTask(int[] array, int left, int right) {
            this.array = array;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (left < right) {
                int middle = (left + right) / 2;

                // Создаем подзадачи для левой и правой половин массива
                MergeSortTask leftTask = new MergeSortTask(array, left, middle);
                MergeSortTask rightTask = new MergeSortTask(array, middle + 1, right);

                // Выполняем подзадачи параллельно
                invokeAll(leftTask, rightTask);

                // Сливаем отсортированные подмассивы
                merge(array, left, middle, right);
            }
        }

        private void merge(int[] array, int left, int middle, int right) {
            int n1 = middle - left + 1;
            int n2 = right - middle;

            int[] leftArray = new int[n1];
            int[] rightArray = new int[n2];

            System.arraycopy(array, left, leftArray, 0, n1);
            System.arraycopy(array, middle + 1, rightArray, 0, n2);

            int i = 0, j = 0, k = left;

            while (i < n1 && j < n2) {
                if (leftArray[i] <= rightArray[j]) {
                    array[k] = leftArray[i];
                    i++;
                } else {
                    array[k] = rightArray[j];
                    j++;
                }
                k++;
            }

            while (i < n1) {
                array[k] = leftArray[i];
                i++;
                k++;
            }

            while (j < n2) {
                array[k] = rightArray[j];
                j++;
                k++;
            }
        }
    }
}
