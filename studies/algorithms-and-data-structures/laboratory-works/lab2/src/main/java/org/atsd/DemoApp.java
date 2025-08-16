package org.atsd;

import java.util.List;

/**
 * Демонстраційна версія лабораторної роботи 1.2
 * Дослідження структури даних «хеш-таблиця»
 */
public class DemoApp {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("ЛАБОРАТОРНА РОБОТА 1.2");
        System.out.println("ДОСЛІДЖЕННЯ СТРУКТУРИ ДАНИХ «ХЕШ-ТАБЛИЦЯ»");
        System.out.println("=".repeat(60));

        // Завдання першого рівня
        System.out.println("\n1. ЗАВДАННЯ ПЕРШОГО РІВНЯ");
        System.out.println("Створення хеш-таблиці без колізій");
        System.out.println("-".repeat(40));

        levelOneTaskDemo();

        // Завдання другого рівня
        System.out.println("\n2. ЗАВДАННЯ ДРУГОГО РІВНЯ");
        System.out.println("Розв'язання колізій методом подвійного хешування");
        System.out.println("-".repeat(50));

        levelTwoTaskDemo();

        // Завдання третього рівня
        System.out.println("\n3. ЗАВДАННЯ ТРЕТЬОГО РІВНЯ");
        System.out.println("Видалення елементів за критерієм площі");
        System.out.println("-".repeat(40));

        levelThreeTaskDemo();
    }

    /**
     * Демонстрація завдання першого рівня: створення хеш-таблиці без колізій
     */
    private static void levelOneTaskDemo() {
        int tableSize = 11; // Просте число для кращого розподілу
        System.out.printf("Розмір хеш-таблиці: %d%n", tableSize);

        RhombusHashTable hashTable = new RhombusHashTable(tableSize);

        System.out.println("\nГенерація ромбів і спроба вставки без колізій:");

        int attempts = 0;
        int maxAttempts = tableSize * 2;

        while (hashTable.getSize() < Math.min(tableSize / 2, 8) && attempts < maxAttempts) {
            attempts++;
            Rhombus rhombus = Rhombus.createRandomRhombus();

            if (rhombus.isValidRhombus()) {
                boolean inserted = hashTable.insertWithoutCollisions(rhombus);
                System.out.printf("Спроба %d: %s%n",
                    attempts,
                    inserted ? "✓ ВСТАВЛЕНО" : "✗ КОЛІЗІЯ");

                if (inserted) {
                    System.out.printf("  Периметр: %.2f, Площа: %.2f, Хеш: %d%n",
                        rhombus.getPerimeter(), rhombus.getArea(),
                        (int)(rhombus.getPerimeter() * 1000) % tableSize);
                }
            }
        }

        hashTable.display();
    }

    /**
     * Демонстрація завдання другого рівня: розв'язання колізій подвійним хешуванням
     */
    private static void levelTwoTaskDemo() {
        int tableSize = 7; // Менший розмір для демонстрації колізій
        System.out.printf("Розмір хеш-таблиці: %d%n", tableSize);

        RhombusHashTable hashTable = new RhombusHashTable(tableSize);

        System.out.println("\nГенерація ромбів і вставка з розв'язанням колізій:");

        int elementsToInsert = tableSize - 1;

        for (int i = 0; i < elementsToInsert; i++) {
            Rhombus rhombus = Rhombus.createRandomRhombus();

            if (rhombus.isValidRhombus()) {
                boolean inserted = hashTable.insert(rhombus);
                System.out.printf("Елемент %d: %s%n",
                    i + 1,
                    inserted ? "✓ ВСТАВЛЕНО" : "✗ НЕ ВДАЛОСЯ ВСТАВИТИ");

                if (inserted) {
                    System.out.printf("  Периметр: %.2f, Площа: %.2f%n",
                        rhombus.getPerimeter(), rhombus.getArea());
                }
            }
        }

        hashTable.display();
    }

    /**
     * Демонстрація завдання третього рівня: видалення елементів за критерієм
     */
    private static void levelThreeTaskDemo() {
        int tableSize = 13;
        System.out.printf("Розмір хеш-таблиці: %d%n", tableSize);

        RhombusHashTable hashTable = new RhombusHashTable(tableSize);

        System.out.println("\nЗаповнення хеш-таблиці:");

        int elementsToInsert = 10;

        for (int i = 0; i < elementsToInsert; i++) {
            Rhombus rhombus = Rhombus.createRandomRhombus();

            if (rhombus.isValidRhombus()) {
                boolean inserted = hashTable.insert(rhombus);
                if (inserted) {
                    System.out.printf("Вставлено ромб з площею: %.2f%n", rhombus.getArea());
                }
            }
        }

        System.out.println("\nСтан хеш-таблиці до видалення:");
        hashTable.display();

        // Визначення критерію видалення
        List<Rhombus> allElements = hashTable.getAllElements();
        if (!allElements.isEmpty()) {
            double avgArea = allElements.stream()
                .mapToDouble(Rhombus::getArea)
                .average()
                .orElse(0);

            System.out.printf("%nСтатистика площ:%n");
            System.out.printf("Середня площа: %.2f%n", avgArea);

            double minArea = allElements.stream().mapToDouble(Rhombus::getArea).min().orElse(0);
            double maxArea = allElements.stream().mapToDouble(Rhombus::getArea).max().orElse(0);

            System.out.printf("Мінімальна площа: %.2f%n", minArea);
            System.out.printf("Максимальна площа: %.2f%n", maxArea);

            System.out.printf("%nКритерій видалення: площа > %.2f%n", avgArea);

            // Показуємо елементи, які будуть видалені
            System.out.println("\nЕлементи, що підлягають видаленню:");
            allElements.stream()
                .filter(r -> r.getArea() > avgArea)
                .forEach(r -> System.out.printf("  Площа: %.2f, Периметр: %.2f%n",
                    r.getArea(), r.getPerimeter()));

            int removedCount = hashTable.removeByAreaCriterion(avgArea);

            System.out.printf("%nВидалено елементів: %d%n", removedCount);

            System.out.println("\nСтан хеш-таблиці після видалення:");
            hashTable.display();
        } else {
            System.out.println("\nТаблиця порожня, нічого видаляти.");
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("ДЕМОНСТРАЦІЮ ЗАВЕРШЕНО");
        System.out.println("=".repeat(60));
    }
}
