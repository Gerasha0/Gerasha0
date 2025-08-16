package org.atsd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

/**
 * Юніт-тести для додатку.
 */
public class AppTest {

    @Test
    public void testCountingSortCorrectness() {
        // Тест на коректність сортування
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        int[] expected = {11, 12, 22, 25, 34, 64, 90};

        SortingAlgorithms.countingSort(array);

        assertArrayEquals(expected, array);
        assertTrue(SortingAlgorithms.isSorted(array));
    }

    @Test
    public void testRadixSortCorrectness() {
        // Тест на коректність сортування
        int[] array = {170, 45, 75, 90, 2, 802, 24, 66};
        int[] expected = {2, 24, 45, 66, 75, 90, 170, 802};

        SortingAlgorithms.radixSort(array);

        assertArrayEquals(expected, array);
        assertTrue(SortingAlgorithms.isSorted(array));
    }

    @Test
    public void testEmptyArray() {
        int[] emptyArray = {};

        SortingAlgorithms.countingSort(emptyArray);
        assertEquals(0, emptyArray.length);

        SortingAlgorithms.radixSort(emptyArray);
        assertEquals(0, emptyArray.length);
    }

    @Test
    public void testSingleElementArray() {
        int[] singleElement = {42};

        SortingAlgorithms.countingSort(singleElement);
        assertArrayEquals(new int[]{42}, singleElement);

        int[] singleElement2 = {42};
        SortingAlgorithms.radixSort(singleElement2);
        assertArrayEquals(new int[]{42}, singleElement2);
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 6, 9};

        // Тестуємо counting sort
        int[] arrayForCounting = Arrays.copyOf(array, array.length);
        SortingAlgorithms.countingSort(arrayForCounting);
        assertArrayEquals(expected, arrayForCounting);

        // Тестуємо radix sort
        int[] arrayForRadix = Arrays.copyOf(array, array.length);
        SortingAlgorithms.radixSort(arrayForRadix);
        assertArrayEquals(expected, arrayForRadix);
    }

    @Test
    public void testAlreadySortedArray() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] expected = Arrays.copyOf(array, array.length);

        // Тестуємо counting sort
        int[] arrayForCounting = Arrays.copyOf(array, array.length);
        SortingAlgorithms.countingSort(arrayForCounting);
        assertArrayEquals(expected, arrayForCounting);

        // Тестуємо radix sort
        int[] arrayForRadix = Arrays.copyOf(array, array.length);
        SortingAlgorithms.radixSort(arrayForRadix);
        assertArrayEquals(expected, arrayForRadix);
    }

    @Test
    public void testReverseSortedArray() {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        // Тестуємо counting sort
        int[] arrayForCounting = Arrays.copyOf(array, array.length);
        SortingAlgorithms.countingSort(arrayForCounting);
        assertArrayEquals(expected, arrayForCounting);

        // Тестуємо radix sort
        int[] arrayForRadix = Arrays.copyOf(array, array.length);
        SortingAlgorithms.radixSort(arrayForRadix);
        assertArrayEquals(expected, arrayForRadix);
    }

    @Test
    public void testArrayGenerators() {
        int size = 100;

        // Тест генератора випадкових даних
        int[] randomArray = SortingAlgorithms.generateRandomArray(size);
        assertEquals(size, randomArray.length);

        // Тест генератора відсортованих даних
        int[] sortedArray = SortingAlgorithms.generateSortedArray(size);
        assertEquals(size, sortedArray.length);
        assertTrue(SortingAlgorithms.isSorted(sortedArray));

        // Тест генератора зворотно відсортованих даних
        int[] reversedArray = SortingAlgorithms.generateReversedArray(size);
        assertEquals(size, reversedArray.length);
        for (int i = 0; i < size - 1; i++) {
            assertTrue(reversedArray[i] > reversedArray[i + 1]);
        }

        // Тест генератора з повтореннями
        int[] arrayWithDuplicates = SortingAlgorithms.generateArrayWithDuplicates(size);
        assertEquals(size, arrayWithDuplicates.length);
    }

    @Test
    public void testIsSortedMethod() {
        assertTrue(SortingAlgorithms.isSorted(new int[]{1, 2, 3, 4, 5}));
        assertTrue(SortingAlgorithms.isSorted(new int[]{1, 1, 2, 2, 3}));
        assertTrue(SortingAlgorithms.isSorted(new int[]{5}));
        assertTrue(SortingAlgorithms.isSorted(new int[]{}));

        assertFalse(SortingAlgorithms.isSorted(new int[]{5, 4, 3, 2, 1}));
        assertFalse(SortingAlgorithms.isSorted(new int[]{1, 3, 2, 4, 5}));
    }

    @Test
    public void testCopyArray() {
        int[] original = {5, 3, 8, 1, 9, 2};
        int[] copy = SortingAlgorithms.copyArray(original);

        // Перевіряємо, що масиви мають однаковий вміст
        assertArrayEquals(original, copy);

        // Перевіряємо, що це різні об'єкти
        assertNotSame(original, copy);

        // Змінюємо копію та перевіряємо, щ�� оригінал не змінився
        copy[0] = 999;
        assertNotEquals(original[0], copy[0]);
    }

    @Test
    public void testPerformanceAnalyzer() {
        // Тест базової функціональності ан��лізатора продуктивності
        int[] testArray = {64, 34, 25, 12, 22, 11, 90};

        // Тестуємо вимірювання counting sort
        PerformanceAnalyzer.PerformanceResult countingResult =
            PerformanceAnalyzer.measureCountingSort(testArray, "Тестові дані");

        assertNotNull(countingResult);
        assertEquals("Counting Sort", countingResult.getAlgorithmName());
        assertEquals(testArray.length, countingResult.getArraySize());
        assertEquals("Тестові дані", countingResult.getDataType());
        assertTrue(countingResult.getExecutionTimeNs() > 0);
        assertTrue(countingResult.getExecutionTimeMs() >= 0);

        // Тестуємо вимірювання radix sort
        PerformanceAnalyzer.PerformanceResult radixResult =
            PerformanceAnalyzer.measureRadixSort(testArray, "Тестові дані");

        assertNotNull(radixResult);
        assertEquals("Radix Sort", radixResult.getAlgorithmName());
        assertEquals(testArray.length, radixResult.getArraySize());
        assertEquals("Тестові дані", radixResult.getDataType());
        assertTrue(radixResult.getExecutionTimeNs() > 0);
        assertTrue(radixResult.getExecutionTimeMs() >= 0);
    }

    @Test
    public void testPerformanceResultToString() {
        PerformanceAnalyzer.PerformanceResult result =
            new PerformanceAnalyzer.PerformanceResult("Test Sort", 1000, "Test Data", 5000000L);

        String resultString = result.toString();

        assertTrue(resultString.contains("Test Sort"));
        assertTrue(resultString.contains("1000"));
        assertTrue(resultString.contains("Test Data"));
        // Проверяем время в формате, который использует текущая локаль
        assertTrue(resultString.contains("5,000") || resultString.contains("5.000"));
    }

    @Test
    public void testLargeArrayPerformance() {
        // Тест на великих масивах для перевірки стабільності
        int size = 1000;
        int[] largeArray = SortingAlgorithms.generateRandomArray(size);

        // Тестуємо counting sort
        int[] arrayForCounting = SortingAlgorithms.copyArray(largeArray);
        SortingAlgorithms.countingSort(arrayForCounting);
        assertTrue(SortingAlgorithms.isSorted(arrayForCounting));

        // Тестуємо radix sort
        int[] arrayForRadix = SortingAlgorithms.copyArray(largeArray);
        SortingAlgorithms.radixSort(arrayForRadix);
        assertTrue(SortingAlgorithms.isSorted(arrayForRadix));

        // Результати повинні бути однаковими
        assertArrayEquals(arrayForCounting, arrayForRadix);
    }
}
