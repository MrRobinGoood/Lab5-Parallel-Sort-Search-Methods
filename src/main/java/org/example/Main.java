package org.example;

import org.example.multi.search.PBinarySearch;
import org.example.multi.search.PInterpolationSearch;
import org.example.multi.sort.PHeapSort;
import org.example.multi.sort.PMergeSort;
import org.example.multi.sort.PQuickSort;
import org.example.single.search.BinarySearch;
import org.example.single.search.HashSearch;
import org.example.single.search.InterpolationSearch;
import org.example.single.sort.HeapSort;
import org.example.single.sort.MergeSort;
import org.example.single.sort.QuickSort;

public class Main {


    // Пример использования
    public static void main(String[] args) {
        int[] countElements = new int[]{100, 1000, 10000};
        int[] countThreads = new int[]{2, 4, 8};
        //однопоточные сортировки
        QuickSort quickSort = new QuickSort();
        HeapSort heapSort = new HeapSort();
        MergeSort mergeSort = new MergeSort();
        //однопоточный поиск
        BinarySearch binarySearch = new BinarySearch();
        InterpolationSearch interpolationSearch = new InterpolationSearch();
        HashSearch hashSearch = new HashSearch();
        //многопоточные сортировки
        PMergeSort pMergeSort = new PMergeSort();
        PHeapSort pHeapSort = new PHeapSort();
        PQuickSort pQuickSort = new PQuickSort();
        //многопоточный поиск
        PBinarySearch pBinarySearch = new PBinarySearch();
        PInterpolationSearch pInterpolationSearch = new PInterpolationSearch();
//        PHashSearch pHashSearch = new PHashSearch();


        for (int countElement : countElements) {
            int[] array = new int[countElement];
            for (int i = 0; i < array.length; i++) {
                array[i] = (int) Math.round((Math.random() * 99) - 0);
            }

            System.out.println("\n-----------ГИПЕРПАРАМЕТРЫ-----------\nЕдиницы измерения: наносекунды\nРазмер коллекции: " + array.length + "");
            System.out.println("\n-----------ОДНОПОТОЧНЫЕ АЛГОРИТМЫ:------------\n");
            System.out.println("Время затраченное на сортировку \n- быстрая сортировка: " + quickSort.sort(array) +
                    "\n- пирамидальная сортировка: " + heapSort.sort(array) + "\n- сортировка вставкой: " +
                    mergeSort.sort(array));

            int[] hashTable = hashSearch.createHashTable(array, array.length);
            int keyToSearch = array[countElement / 2]; // Ключ для поиска
            System.out.println("\nВремя затраченное на поиск числа " + keyToSearch + "\n- поиск по хешу: "
                    + hashSearch.hashSearch(hashTable, keyToSearch) + "\n- бинарный поиск(без сортировки): " + binarySearch.binarySearch(array, keyToSearch) + "\n- интерполяционный поиск: " + interpolationSearch.interpolationSearch(array, keyToSearch));

            System.out.println("\n-----------МНОГОПОТОЧНЫЕ АЛГОРИТМЫ:------------");
            for (int countThread : countThreads) {
                System.out.println("\nПОТОКОВ: " + countThread + "\n");
                System.out.println("Время затраченное на сортировку \n- быстрая сортировка: " + pQuickSort.sort(array, countThread) + "\n- пирамидальная сортировка: " + pHeapSort.sort(array, countThread) + "\n- сортировка вставкой: " +
                        pMergeSort.sort(array, countThread));

                System.out.println("\nВремя затраченное на поиск числа " + keyToSearch + "\n- бинарный поиск(без сортировки): " + pBinarySearch.binarySearch(array, keyToSearch, countThread) + "\n- интерполяционный поиск: " + pInterpolationSearch.interpolationSearch(array, keyToSearch, countThread));

            }

        }

//        PMergeSort pMergeSort = new PMergeSort();
//        System.out.println(pMergeSort.parallelMergeSort(array));


    }

}
