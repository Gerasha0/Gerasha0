package org.atsd;

import java.util.Arrays;

/**
 * Клас з алгоритмами сортування для дослідження ефективності
 */
public class SortingAlgorithms {

    /**
     * Сортування розподіленого підрахунку (Counting Sort)
     * Ефективне для сортування цілих чисел у обмеженому діапазоні
     */
    public static void countingSort(int[] array) {
        if (array.length <= 1) {
            return;
        }

        // Знаходимо мінімальне та максимальне значення
        int min = Arrays.stream(array).min().orElse(0);
        int max = Arrays.stream(array).max().orElse(0);
        int range = max - min + 1;

        // Створюємо масив для підрахунку
        int[] count = new int[range];

        // Підраховуємо кількість кожного елемента
        for (int num : array) {
            count[num - min]++;
        }

        // Модифікуємо масив підрахунку для отримання позицій
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Створюємо результуючий масив
        int[] result = new int[array.length];

        // Заповнюємо результуючий масив у зворотному порядку для стабільності
        for (int i = array.length - 1; i >= 0; i--) {
            result[count[array[i] - min] - 1] = array[i];
            count[array[i] - min]--;
        }

        // Копіюємо результат назад в оригінальний масив
        System.arraycopy(result, 0, array, 0, array.length);
    }

    /**
     * Порозрядне сортування (Radix Sort)
     * Використовує counting sort для кожного розряду
     */
    public static void radixSort(int[] array) {
        if (array.length <= 1) {
            return;
        }

        // Знаходимо максимальне значення для визначення кількості розрядів
        int max = Arrays.stream(array).max().orElse(0);

        // Виконуємо counting sort для кожного розряду
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp);
        }
    }

    /**
     * Допоміжний метод counting sort для певного розряду
     */
    private static void countingSortByDigit(int[] array, int exp) {
        int n = array.length;
        int[] result = new int[n];
        int[] count = new int[10]; // Для цифр 0-9

        // Підраховуємо кількість кожної цифри в поточному розряді
        for (int i = 0; i < n; i++) {
            count[(array[i] / exp) % 10]++;
        }

        // Модифікуємо count[i] так, щоб він містив фактичну позицію цифри
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Будуємо результуючий масив
        for (int i = n - 1; i >= 0; i--) {
            result[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        // Копіюємо результат назад в оригінальний масив
        System.arraycopy(result, 0, array, 0, n);
    }

    /**
     * Генерує випадковий масив заданого розміру
     */
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 10000); // Числа від 0 до 9999
        }
        return array;
    }

    /**
     * Генерує відсортований масив (найкращий випадок)
     */
    public static int[] generateSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    /**
     * Генерує зворотно відсортований масив (найгірший випадок для деяких алгоритмів)
     */
    public static int[] generateReversedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i - 1;
        }
        return array;
    }

    /**
     * Генерує масив з багатьма повтореннями (середній випадок)
     */
    public static int[] generateArrayWithDuplicates(int size) {
        int[] array = new int[size];
        int uniqueValues = Math.max(size / 10, 1); // 10% унікальних значень

        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * uniqueValues);
        }
        return array;
    }

    /**
     * Створює копію масиву
     */
    public static int[] copyArray(int[] original) {
        return Arrays.copyOf(original, original.length);
    }

    /**
     * Перевіряє, чи відсортований масив
     */
    public static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
