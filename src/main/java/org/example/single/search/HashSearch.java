package org.example.single.search;

import java.util.Arrays;

public class HashSearch {
    public static int[] createHashTable(int[] array, int size) {
        int[] hashTable = new int[size];
        Arrays.fill(hashTable, -1); // Инициализируем все элементы как -1

        for (int key : array) {
            int hash = Math.abs(key % size); // Вычисляем хеш
            while (hashTable[hash] != -1) {
                hash = (hash + 1) % size; // Линейное пробирование в случае коллизии
            }
            hashTable[hash] = key;
        }

        return hashTable;
    }

    // Метод для хеш-поиска
    public static long hashSearch(int[] hashTable, int key) {
        int size = hashTable.length;
        int hash = Math.abs(key % size);
        long startTime = System.nanoTime();

        while (hashTable[hash] != -1) {
            if (hashTable[hash] == key) {
                // Возвращаем затраченное время, если ключ найден
                long endTime = System.nanoTime();
                return endTime - startTime;
            }
            hash = (hash + 1) % size; // Линейное пробирование
            if (hash == Math.abs(key % size)) {
                // Обнаружен цикл, ключ не найден
                break;
            }
        }

        // Возвращаем затраченное время, если ключ не найден
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
