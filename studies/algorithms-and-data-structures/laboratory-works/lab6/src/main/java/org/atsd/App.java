package org.atsd;

import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("Лаба 1.6");
        System.out.println("Дослідження методу аналізу алгоритмів");
        System.out.println("=".repeat(70));

        // Завдання першого рівня
        System.out.println("\n1. Перше завдання завдання");
        System.out.println("Дослідження сортування розподіленого підрахунку");
        System.out.println("-".repeat(50));

        levelOneTask();

        // Завдання другого рівня
        System.out.println("\n2. Друге завдання");
        System.out.println("Порівняння двох алгоритмів сортування");
        System.out.println("-".repeat(40));

        levelTwoTask();

        // Завдання третього рівня
        System.out.println("\n3. Третє завдання");
        System.out.println("Дослідження впливу структурованості даних");
        System.out.println("-".repeat(45));

        levelThreeTask();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("Демо завершено.");
        System.out.println("Всі результати готові для побудови графіків у Excel");
        System.out.println("=".repeat(70));
    }

    // Завдання першого рівня: дослідження counting sort
    private static void levelOneTask() {
        System.out.println("Алгоритм: Сортування розподіленого підрахунку (Counting Sort)");
        System.out.println("Структура даних: Одновимірний масив");
        System.out.println("Розміри для тестування: N=100, N²=10000, N³=1000000");
        System.out.println();

        final int N = 100;

        // Дослідження для різних розмірів
        List<PerformanceAnalyzer.PerformanceResult> results =
            PerformanceAnalyzer.analyzePerformance(N);

        // Аналіз складності
        PerformanceAnalyzer.analyzeComplexity(results);

        // Виводимо дані для Excel
        System.out.println("\n--- Дані для першого графіку (Excel) ---");
        System.out.println("Розмір масиву\tЧас виканання (мс)");

        results.stream()
            .filter(r -> "Counting Sort".equals(r.getAlgorithmName()))
            .forEach(r -> System.out.printf("%d\t%.3f%n",
                r.getArraySize(), r.getExecutionTimeMs()));
    }

    // Завдання другого рівня: порівняння counting sort та radix sort
    private static void levelTwoTask() {
        System.out.println("Алгоритми для порівняння:");
        System.out.println("1. Сортування розподіленого підрахунку (Counting Sort)");
        System.out.println("2. Порозрядне сортування (Radix Sort)");
        System.out.println("Структура даних: Одновимірний масив");
        System.out.println();

        final int N = 100;

        // Повторюємо дослідження з першого рівня для отримання даних обох алгоритмів
        List<PerformanceAnalyzer.PerformanceResult> results =
            PerformanceAnalyzer.analyzePerformance(N);

        // Порівняльний аналіз
        System.out.println("=== Порівняльний аналіз алгоритмів ===");

        // Групуємо результати за розміром масиву
        int[] sizes = {N, N * N, N * N * N};

        for (int size : sizes) {
            System.out.printf("%nРозмір масиву: %d%n", size);

            PerformanceAnalyzer.PerformanceResult countingResult = results.stream()
                .filter(r -> "Counting Sort".equals(r.getAlgorithmName()) && r.getArraySize() == size)
                .findFirst().orElse(null);

            PerformanceAnalyzer.PerformanceResult radixResult = results.stream()
                .filter(r -> "Radix Sort".equals(r.getAlgorithmName()) && r.getArraySize() == size)
                .findFirst().orElse(null);

            if (countingResult != null && radixResult != null) {
                double ratio = (double) radixResult.getExecutionTimeNs() / countingResult.getExecutionTimeNs();

                System.out.printf("  Counting Sort: %10.3f мс%n", countingResult.getExecutionTimeMs());
                System.out.printf("  Radix Sort:    %10.3f мс%n", radixResult.getExecutionTimeMs());
                System.out.printf("  Співвідношення: %.2f (Radix/Counting)%n", ratio);

                if (ratio < 1.0) {
                    System.out.println("  → Radix Sort швидший");
                } else if (ratio > 1.0) {
                    System.out.println("  → Counting Sort швидший");
                } else {
                    System.out.println("  → Алгоритми показують однакову швидкість");
                }
            }
        }

        // Виводимо дані для Excel
        System.out.println("\n--- Дані для другого графіку (Excel) ---");
        System.out.println("Розмір\tCounting Sort (мс)\tRadix Sort (мс)");

        for (int size : sizes) {
            PerformanceAnalyzer.PerformanceResult countingResult = results.stream()
                .filter(r -> "Counting Sort".equals(r.getAlgorithmName()) && r.getArraySize() == size)
                .findFirst().orElse(null);

            PerformanceAnalyzer.PerformanceResult radixResult = results.stream()
                .filter(r -> "Radix Sort".equals(r.getAlgorithmName()) && r.getArraySize() == size)
                .findFirst().orElse(null);

            if (countingResult != null && radixResult != null) {
                System.out.printf("%d\t%.3f\t%.3f%n",
                    size, countingResult.getExecutionTimeMs(), radixResult.getExecutionTimeMs());
            }
        }
    }

    // Завдання третього рівня: вплив структурованості даних
    private static void levelThreeTask() {
        System.out.println("Дослідження впливу послідовності розташування елементів");
        System.out.println("Розмір масиву: 10000 елементів");
        System.out.println("Типи послідовностей:");
        System.out.println("- Відсортовані (найкращий випадок)");
        System.out.println("- Зворотно відсортовані (найгірший випадок)");
        System.out.println("- З повтореннями (середній випадок)");
        System.out.println("- Випадкові (типовий випадок)");
        System.out.println();

        final int TEST_SIZE = 10000;

        List<PerformanceAnalyzer.PerformanceResult> results =
            PerformanceAnalyzer.analyzeDataStructureImpact(TEST_SIZE);

        // Аналізуємо найкращі та найгірші випадки
        System.out.println("\n=== Аналіз найкращих та найгірших випадків ===");

        String[] algorithms = {"Counting Sort", "Radix Sort"};

        for (String algorithm : algorithms) {
            System.out.printf("%n%s:%n", algorithm);

            List<PerformanceAnalyzer.PerformanceResult> algorithmResults = results.stream()
                .filter(r -> algorithm.equals(r.getAlgorithmName()))
                .sorted((r1, r2) -> Long.compare(r1.getExecutionTimeNs(), r2.getExecutionTimeNs()))
                .toList();

            if (!algorithmResults.isEmpty()) {
                PerformanceAnalyzer.PerformanceResult fastest = algorithmResults.get(0);
                PerformanceAnalyzer.PerformanceResult slowest = algorithmResults.get(algorithmResults.size() - 1);

                System.out.printf("  Найшвидший: %-20s (%.3f мс)%n",
                    fastest.getDataType(), fastest.getExecutionTimeMs());
                System.out.printf("  Найповільніший: %-20s (%.3f мс)%n",
                    slowest.getDataType(), slowest.getExecutionTimeMs());

                double difference = (double) slowest.getExecutionTimeNs() / fastest.getExecutionTimeNs();
                System.out.printf("  Різниця: %.2fx%n", difference);
            }
        }

        // Виводимо дані для Excel
        System.out.println("\n--- Дані для третього графіку (Excel) ---");
        System.out.println("Тип даних\tCounting Sort (мс)\tRadix Sort (мс)");

        String[] dataTypes = {"Відсортовані", "Зворотно відсортовані", "З повтореннями", "Випадкові"};

        for (String dataType : dataTypes) {
            PerformanceAnalyzer.PerformanceResult countingResult = results.stream()
                .filter(r -> "Counting Sort".equals(r.getAlgorithmName()) && dataType.equals(r.getDataType()))
                .findFirst().orElse(null);

            PerformanceAnalyzer.PerformanceResult radixResult = results.stream()
                .filter(r -> "Radix Sort".equals(r.getAlgorithmName()) && dataType.equals(r.getDataType()))
                .findFirst().orElse(null);

            if (countingResult != null && radixResult != null) {
                System.out.printf("%s\t%.3f\t%.3f%n",
                    dataType, countingResult.getExecutionTimeMs(), radixResult.getExecutionTimeMs());
            }
        }

        // CSV вивід для зручності
        System.out.println("\n--- Повний CSV вивід для Excel ---");
        PerformanceAnalyzer.printCSVResults(results);
    }
}