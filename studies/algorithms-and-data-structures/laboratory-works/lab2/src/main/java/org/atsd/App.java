package org.atsd;

import java.util.Scanner;
import java.util.List;

/**
 * Лабораторна робота 1.2
 * Дослідження структури даних «хеш-таблиця»
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=".repeat(60));
        System.out.println("ЛАБОРАТОРНА РОБОТА 1.2");
        System.out.println("ДОСЛІДЖЕННЯ СТРУКТУРИ ДАНИХ «ХЕШ-ТАБЛИЦЯ»");
        System.out.println("=".repeat(60));

        // Завдання першого рівня
        System.out.println("\n1. ЗАВДАННЯ ПЕРШОГО РІВНЯ");
        System.out.println("Створення хеш-таблиці без колізій");
        System.out.println("-".repeat(40));

        levelOneTask();

        // Завдання другого рівня
        System.out.println("\n2. ЗАВДАННЯ ДРУГОГО РІВНЯ");
        System.out.println("Розв'язання колізій методом подвійного хешування");
        System.out.println("-".repeat(50));

        levelTwoTask();

        // Завдання третього рівня
        System.out.println("\n3. ЗАВДАННЯ ТРЕТЬОГО РІВНЯ");
        System.out.println("Видалення елементів за критерієм площі");
        System.out.println("-".repeat(40));

        levelThreeTask();

        scanner.close();
    }

    /**
     * Завдання першого рівня: створення хеш-таблиці без колізій
     */
    private static void levelOneTask() {
        System.out.print("Введіть розмір хеш-таблиці: ");
        Scanner scanner = new Scanner(System.in);
        int tableSize = scanner.nextInt();

        RhombusHashTable hashTable = new RhombusHashTable(tableSize);

        System.out.println("\nГенерація ромбів і спроба вставки без колізій:");

        int attempts = 0;
        int maxAttempts = tableSize * 3; // Обмежуємо кількість спроб

        while (hashTable.getSize() < Math.min(tableSize / 2, 10) && attempts < maxAttempts) {
            attempts++;
            Rhombus rhombus = Rhombus.createRandomRhombus();

            if (rhombus.isValidRhombus()) {
                boolean inserted = hashTable.insertWithoutCollisions(rhombus);
                System.out.printf("Спроба %d: %s - %s%n",
                    attempts,
                    inserted ? "ВСТАВЛЕНО" : "КОЛІЗІЯ",
                    rhombus);
            }
        }

        hashTable.display();
    }

    /**
     * Завдання другого рівня: розв'язання колізій подвійним хешуванням
     */
    private static void levelTwoTask() {
        System.out.print("Введіть розмір хеш-таблиці: ");
        Scanner scanner = new Scanner(System.in);
        int tableSize = scanner.nextInt();

        RhombusHashTable hashTable = new RhombusHashTable(tableSize);

        System.out.println("\nГенерація ромбів і вставка з розв'язанням колізій:");

        int elementsToInsert = Math.min(tableSize - 1, 15);

        for (int i = 0; i < elementsToInsert; i++) {
            Rhombus rhombus = Rhombus.createRandomRhombus();

            if (rhombus.isValidRhombus()) {
                boolean inserted = hashTable.insert(rhombus);
                System.out.printf("Елемент %d: %s - %s%n",
                    i + 1,
                    inserted ? "ВСТАВЛЕНО" : "НЕ ВДАЛОСЯ ВСТАВИТИ",
                    rhombus);

                if (!inserted) {
                    System.out.println("  Причина: таблиця заповнена або не вдалося знайти місце");
                }
            }
        }

        hashTable.display();
    }

    /**
     * Завдання третього рівня: видалення елементів за критерієм
     */
    private static void levelThreeTask() {
        System.out.print("Введіть розмір хеш-таблиці: ");
        Scanner scanner = new Scanner(System.in);
        int tableSize = scanner.nextInt();

        RhombusHashTable hashTable = new RhombusHashTable(tableSize);

        System.out.println("\nЗаповнення хеш-таблиці:");

        int elementsToInsert = Math.min(tableSize - 1, 12);

        for (int i = 0; i < elementsToInsert; i++) {
            Rhombus rhombus = Rhombus.createRandomRhombus();

            if (rhombus.isValidRhombus()) {
                boolean inserted = hashTable.insert(rhombus);
                if (inserted) {
                    System.out.printf("Вставлено: %s%n", rhombus);
                }
            }
        }

        hashTable.display();

        // Визначення критерію видалення
        List<Rhombus> allElements = hashTable.getAllElements();
        if (!allElements.isEmpty()) {
            double avgArea = allElements.stream()
                .mapToDouble(Rhombus::getArea)
                .average()
                .orElse(0);

            System.out.printf("%nСередня площа елементів: %.2f%n", avgArea);
            System.out.printf("Критерій видалення: площа > %.2f%n", avgArea);

            int removedCount = hashTable.removeByAreaCriterion(avgArea);

            System.out.printf("%nВидалено елементів: %d%n", removedCount);

            hashTable.display();
        } else {
            System.out.println("\nТаблиця порожня, нічого видаляти.");
        }
    }
}
