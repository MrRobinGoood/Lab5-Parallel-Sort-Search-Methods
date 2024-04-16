//import java.util.Arrays;
//import java.util.concurrent.ForkJoinPool;
//import java.util.concurrent.RecursiveTask;

//public class PHashSearch {}
//
//    public static long hashSearch(int[] hashTable, int key, int numThreads) {
//        // Выполняем поиск в хеш-таблице
//        return hashSearch(hashTable, key);
//
//    }
//
//    public static int[] createHashTable(int[] array, int size) {
//        int[] hashTable = new int[size];
//        Arrays.fill(hashTable, -1); // Инициализируем все элементы как -1
//
//        for (int key : array) {
//            int hash = Math.abs(key % size); // Вычисляем хеш
//            while (hashTable[hash] != -1) {
//                hash = (hash + 1) % size; // Линейное пробирование в случае коллизии
//            }
//            hashTable[hash] = key;
//        }
//
//        return hashTable;
//    }
//
//    public static long hashSearch(int[] hashTable, int key) {
//        int size = hashTable.length;
//        int hash = Math.abs(key % size);
//        long startTime = System.nanoTime();
//
//        while (hashTable[hash] != -1) {
//            if (hashTable[hash] == key) {
//                // Возвращаем затраченное время, если ключ найден
//                long endTime = System.nanoTime();
//                return endTime - startTime;
//            }
//            hash = (hash + 1) % size; // Линейное пробирование
//            if (hash == Math.abs(key % size)) {
//                // Обнаружен цикл, ключ не найден
//                break;
//            }
//        }
//
//        // Возвращаем затраченное время, если ключ не найден
//        long endTime = System.nanoTime();
//        return endTime - startTime;
//    }
//}
