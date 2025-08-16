package org.atsd;

/**
 * Лабораторна робота 1.4
 * Дослідження алгоритмів сортування
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("ЛАБОРАТОРНА РОБОТА 1.4");
        System.out.println("ДОСЛІДЖЕННЯ АЛГОРИТМІВ СОРТУВАННЯ");
        System.out.println("=".repeat(60));

        // Завдання першого рівня
        System.out.println("\n1. ЗАВДАННЯ ПЕРШОГО РІВНЯ");
        System.out.println("Сортування масиву вставкою за зростанням середнього бала");
        System.out.println("-".repeat(60));

        levelOneTask();

        // Завдання другого рівня
        System.out.println("\n2. ЗАВДАННЯ ДРУГОГО РІВНЯ");
        System.out.println("Сортування двоспрямованого списку вставкою");
        System.out.println("-".repeat(50));

        levelTwoTask();

        // Завдання третього рівня
        System.out.println("\n3. ЗАВДАННЯ ТРЕТЬОГО РІВНЯ");
        System.out.println("Кишеньковий алгоритм сортування");
        System.out.println("-".repeat(40));

        levelThreeTask();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("ДЕМОНСТРАЦІЮ ЗАВЕРШЕНО");
        System.out.println("Продемонстровано алгоритми сортування:");
        System.out.println("✓ Сортування вставкою для масиву");
        System.out.println("✓ Сортування вставкою для двоспрямованого списку");
        System.out.println("✓ Кишеньковий алгоритм сортування");
        System.out.println("=".repeat(60));
    }

    /**
     * Завдання першого рівня: сортування масиву вставкою
     */
    private static void levelOneTask() {
        System.out.println("Створення та ініціювання масиву студентів:");

        // Створюємо тестовий масив студентів
        Student[] array = SortingAlgorithms.createTestArray();

        // Виводимо масив до сортування
        SortingAlgorithms.displayArray(array, "=== Масив до сортування ===");

        // Сортуємо масив вставкою
        System.out.println("Виконання сортування вставкою...");
        SortingAlgorithms.insertionSort(array);

        // Виводимо масив після сортування
        SortingAlgorithms.displayArray(array, "=== Масив після сортування вставкою ===");

        System.out.println("Пояснення: Студентів відсортовано за зростанням середнього бала");
        System.out.println("Алгоритм: Сортування вставкою, складність O(n²)");
    }

    /**
     * Завдання другого рівня: сортування двоспрямованого списку
     */
    private static void levelTwoTask() {
        System.out.println("Створення двоспрямованого списку студентів:");

        // Створюємо тестовий масив та перетворюємо у список
        Student[] testArray = SortingAlgorithms.createTestArray();
        DoublyLinkedList list = new DoublyLinkedList();

        // Додаємо студентів до списку
        for (Student student : testArray) {
            list.add(student);
        }

        System.out.printf("Додано %d студентів до двоспрямованого списку%n", list.size());

        // Виводимо список до сортування
        list.display("=== Двоспрямований список до сортування ===");

        // Сортуємо список вставкою
        System.out.println("Виконання сортування вставкою для двоспрямованого списку...");
        list.insertionSort();

        // Виводимо список після сортування
        list.display("=== Двоспрямований список після сортування ===");

        System.out.println("Пояснення: Двоспрямований список дозволяє ефективно");
        System.out.println("переміщуватися в обох напрямках при сортуванні вставкою");
    }

    /**
     * Завдання третього рівня: кишеньковий алгоритм сортування
     */
    private static void levelThreeTask() {
        System.out.println("Демонстрація кишенькового алгоритму сортування:");

        // Створюємо копію тестового масиву
        Student[] originalArray = SortingAlgorithms.createTestArray();
        Student[] bucketArray = SortingAlgorithms.copyArray(originalArray);

        // Виводимо масив до сортування
        SortingAlgorithms.displayArray(bucketArray, "=== Масив до кишенькового сортування ===");

        // Виконуємо кишенькове сортування
        System.out.println("Виконання кишенькового сортування...");
        System.out.println("Етапи кишенькового сортування:");
        System.out.println("1. Розподіл студентів по кишенях за середнім балом");
        System.out.println("2. Сортування кожної кишені вставкою");
        System.out.println("3. Збирання відсортованих елементів");

        SortingAlgorithms.bucketSort(bucketArray);

        // Виводимо масив після сортування
        SortingAlgorithms.displayArray(bucketArray, "=== Масив після кишенькового сортування ===");

        // Порівняння з сортуванням вставкою
        System.out.println("\n=== Порівняння алгоритмів ===");
        Student[] insertionArray = SortingAlgorithms.copyArray(originalArray);
        SortingAlgorithms.insertionSort(insertionArray);

        System.out.println("Результат ідентичний сортуванню вставкою: " +
            areArraysEqual(bucketArray, insertionArray));

        System.out.println("\nПереваги кишенькового сортування:");
        System.out.println("✓ Середня складність O(n + k), де k - кількість кишень");
        System.out.println("✓ Ефективний для рівномірно розподілених даних");
        System.out.println("✓ Стабільний алгоритм сортування");
    }

    /**
     * Перевіряє, чи однакові два масиви студентів за балами
     */
    private static boolean areArraysEqual(Student[] array1, Student[] array2) {
        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            if (Math.abs(array1[i].getAverageGrade() - array2[i].getAverageGrade()) > 1e-6) {
                return false;
            }
        }

        return true;
    }
}
