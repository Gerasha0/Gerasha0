package org.atsd;

import java.util.ArrayList;
import java.util.List;

// Клас для дослідження продуктивності алгоритмів сортування
public class PerformanceAnalyzer {

    // Результат вимірювання продуктивності
    public static class PerformanceResult {
        private final String algorithmName;
        private final int arraySize;
        private final String dataType;
        private final long executionTimeNs;

        public PerformanceResult(String algorithmName, int arraySize, String dataType, long executionTimeNs) {
            this.algorithmName = algorithmName;
            this.arraySize = arraySize;
            this.dataType = dataType;
            this.executionTimeNs = executionTimeNs;
        }

        public String getAlgorithmName() { return algorithmName; }
        public int getArraySize() { return arraySize; }
        public String getDataType() { return dataType; }
        public long getExecutionTimeNs() { return executionTimeNs; }
        public double getExecutionTimeMs() { return executionTimeNs / 1_000_000.0; }

        @Override
        public String toString() {
            return String.format("%s | Розмір: %-8d | Тип: %-15s | Час: %10.3f мс (%12d нс)",
                algorithmName, arraySize, dataType, getExecutionTimeMs(), executionTimeNs);
        }
    }

    // Кількість прогонів для усереднення результатів
    private static final int WARMUP_RUNS = 3;
    private static final int MEASUREMENT_RUNS = 5;

    // Вимірює час виконання counting sort
    public static PerformanceResult measureCountingSort(int[] array, String dataType) {
        return measureAlgorithm("Counting Sort", array, dataType, SortingAlgorithms::countingSort);
    }

    // Вимірює час виконання radix sort
    public static PerformanceResult measureRadixSort(int[] array, String dataType) {
        return measureAlgorithm("Radix Sort", array, dataType, SortingAlgorithms::radixSort);
    }

    // Загальний метод для вимірювання часу виконання алгоритму
    private static PerformanceResult measureAlgorithm(String algorithmName, int[] array,
                                                     String dataType, SortingAlgorithm algorithm) {
        int size = array.length;

        // Прогріваючі прогони (JVM warming up)
        for (int i = 0; i < WARMUP_RUNS; i++) {
            int[] copy = SortingAlgorithms.copyArray(array);
            algorithm.sort(copy);
        }

        // Вимірювання часу виконання
        List<Long> times = new ArrayList<>();
        for (int i = 0; i < MEASUREMENT_RUNS; i++) {
            int[] copy = SortingAlgorithms.copyArray(array);

            long startTime = System.nanoTime();
            algorithm.sort(copy);
            long endTime = System.nanoTime();

            times.add(endTime - startTime);

            // Перевіряємо коректність сортування
            if (!SortingAlgorithms.isSorted(copy)) {
                throw new RuntimeException("Алгоритм " + algorithmName + " не відсортував масив коректно!");
            }
        }

        // Обчислюємо середній час
        long averageTime = times.stream().mapToLong(Long::longValue).sum() / times.size();

        return new PerformanceResult(algorithmName, size, dataType, averageTime);
    }

    // Функціональний інтерфейс для алгоритмів сортування
    @FunctionalInterface
    private interface SortingAlgorithm {
        void sort(int[] array);
    }

    // Проводить повне дослідження продуктивності для заданих розмірів
    public static List<PerformanceResult> analyzePerformance(int baseSize) {
        List<PerformanceResult> results = new ArrayList<>();

        int[] sizes = {baseSize, baseSize * baseSize, baseSize * baseSize * baseSize};
        String[] sizeLabels = {"N", "N²", "N³"};

        System.out.println("=== Дослідження продуктивності алгоритмів ===");
        System.out.printf("Базовий розмір N = %d%n", baseSize);
        System.out.println();

        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            String sizeLabel = sizeLabels[i];

            System.out.printf("Тестування розміру %s (%d елементів):%n", sizeLabel, size);

            // Генеруємо випадкові дані
            int[] randomData = SortingAlgorithms.generateRandomArray(size);

            // Тестуємо counting sort
            PerformanceResult countingResult = measureCountingSort(randomData, "Випадкові дані");
            results.add(countingResult);
            System.out.println("  " + countingResult);

            // Тестуємо radix sort
            PerformanceResult radixResult = measureRadixSort(randomData, "Випадкові дані");
            results.add(radixResult);
            System.out.println("  " + radixResult);

            System.out.println();
        }

        return results;
    }

    // Досліджує вплив структурованості даних на продуктивність
    public static List<PerformanceResult> analyzeDataStructureImpact(int size) {
        List<PerformanceResult> results = new ArrayList<>();

        System.out.printf("=== Вплив структурованості даних (розмір %d) ===%n", size);

        // Типи даних для тестування
        String[] dataTypes = {"Відсортовані", "Зворотно відсортовані", "З повтореннями", "Випадкові"};
        int[][] testData = {
            SortingAlgorithms.generateSortedArray(size),
            SortingAlgorithms.generateReversedArray(size),
            SortingAlgorithms.generateArrayWithDuplicates(size),
            SortingAlgorithms.generateRandomArray(size)
        };

        for (int i = 0; i < dataTypes.length; i++) {
            String dataType = dataTypes[i];
            int[] data = testData[i];

            System.out.printf("%nТип даних: %s%n", dataType);

            // Тестуємо counting sort
            PerformanceResult countingResult = measureCountingSort(data, dataType);
            results.add(countingResult);
            System.out.println("  " + countingResult);

            // Тестуємо radix sort
            PerformanceResult radixResult = measureRadixSort(data, dataType);
            results.add(radixResult);
            System.out.println("  " + radixResult);
        }

        return results;
    }

    // Виводить результати у форматі CSV для Excel
    public static void printCSVResults(List<PerformanceResult> results) {
        System.out.println("\n=== Результати у форматі CSV для Excel ===");
        System.out.println("Алгоритм,Розмір,Тип_даних,Час_нс,Час_мс");

        for (PerformanceResult result : results) {
            System.out.printf("%s,%d,%s,%d,%.3f%n",
                result.getAlgorithmName().replace(" ", "_"),
                result.getArraySize(),
                result.getDataType().replace(" ", "_"),
                result.getExecutionTimeNs(),
                result.getExecutionTimeMs());
        }
    }

    // Аналізує складність алгоритмів
    public static void analyzeComplexity(List<PerformanceResult> results) {
        System.out.println("\n=== Аналіз складності алгоритмів ===");

        // Групуємо результати за алгоритмом та типом даних
        results.stream()
            .filter(r -> "Випадкові дані".equals(r.getDataType()))
            .collect(java.util.stream.Collectors.groupingBy(PerformanceResult::getAlgorithmName))
            .forEach((algorithm, algorithmResults) -> {
                System.out.printf("%n%s:%n", algorithm);

                algorithmResults.sort((r1, r2) -> Integer.compare(r1.getArraySize(), r2.getArraySize()));

                for (int i = 0; i < algorithmResults.size(); i++) {
                    PerformanceResult result = algorithmResults.get(i);

                    if (i > 0) {
                        PerformanceResult prev = algorithmResults.get(i - 1);
                        double ratio = (double) result.getExecutionTimeNs() / prev.getExecutionTimeNs();
                        double sizeRatio = (double) result.getArraySize() / prev.getArraySize();

                        System.out.printf("  Розмір: %8d, Час: %10.3f мс, Коефіцієнт росту: %.2fx (розмір збільшився у %.0fx)%n",
                            result.getArraySize(), result.getExecutionTimeMs(), ratio, sizeRatio);
                    } else {
                        System.out.printf("  Розмір: %8d, Час: %10.3f мс%n",
                            result.getArraySize(), result.getExecutionTimeMs());
                    }
                }
            });
    }
}