package org.atsd;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=".repeat(60));
        System.out.println("Лаба 1.3");
        System.out.println("Дослідження нелінійних структур даних");
        System.out.println("=".repeat(60));

        // Завдання першого рівня
        System.out.println("\n1. Перше завдання");
        System.out.println("Створення бінарного дерева та паралельний обхід");
        System.out.println("-".repeat(50));

        levelOneTask();

        // Завдання другого рівня
        System.out.println("\n2. Друге завдання");
        System.out.println("Пошук студентів за критерієм");
        System.out.println("-".repeat(40));

        levelTwoTask();

        // Завдання третього рівня
        System.out.println("\n3. Третє завдання");
        System.out.println("Видалення студентів за критерієм");
        System.out.println("-".repeat(40));

        levelThreeTask();

        scanner.close();
    }

    // Завдання першого рівня: створення дерева та паралельний обхід
    private static void levelOneTask() {
        BinaryTree tree = new BinaryTree();

        System.out.println("Додавання студентів до дерева:");

        // Додаємо тестових студентів
        tree.insert(new Student("Іваненко", 12345, 4.8, true));
        tree.insert(new Student("Петренко", 10001, 3.5, false));
        tree.insert(new Student("Сидоренко", 15000, 4.9, true));
        tree.insert(new Student("Коваленко", 8000, 4.2, true));
        tree.insert(new Student("Мельник", 20000, 4.7, false));
        tree.insert(new Student("Шевченко", 6000, 4.6, true));
        tree.insert(new Student("Кравченко", 25000, 3.8, false));

        System.out.println("Додано 7 студентів до дерева");

        // Виводимо дерево паралельним обходом
        tree.displayParallelTraversal();
    }

    // Завдання другого рівня: пошук за критерієм
    private static void levelTwoTask() {
        BinaryTree tree = new BinaryTree();

        System.out.println("Створення дерева з різними типами студентів:");

        // Додаємо студентів з різними характеристиками
        tree.insert(new Student("Відмінник_1", 11111, 4.9, true));  // відповідає критерію
        tree.insert(new Student("Хорошист_1", 22222, 4.2, true));   // не відповідає (не відмінник)
        tree.insert(new Student("Відмінник_2", 33333, 4.8, false)); // не відповідає (не бере участь)
        tree.insert(new Student("Троєшник", 44444, 3.2, false));    // не відповідає
        tree.insert(new Student("Відмінник_3", 55555, 4.7, true));  // відповідає критерію
        tree.insert(new Student("Хорошист_2", 66666, 4.1, false));  // не відповідає
        tree.insert(new Student("Відмінник_4", 77777, 5.0, true));  // відповідає критерію

        System.out.println("Додано студентів до дерева");

        // Показуємо вміст дерева
        tree.displayParallelTraversal();

        // Шукаємо за критерієм
        List<Student> foundStudents = tree.findBySearchCriteria();
        tree.displaySearchResults(foundStudents, "відмінники, які беруть участь у конференціях");
    }

    // Завдання третього рівня: видалення за критерієм
    private static void levelThreeTask() {
        BinaryTree tree = new BinaryTree();

        System.out.println("Створення дерева для демонстрації видалення:");
        System.out.println("Студенти розміщуються для різних випадків видалення:");
        System.out.println("- Листки (без нащадків)");
        System.out.println("- Вузли з одним нащадком");
        System.out.println("- Вузли з двома нащадками");
        System.out.println("- Батьківсько-дочірні зв'язки");

        // Створюємо дерево з різними випадками розміщення вузлів
        // Корінь - відмінник (видалити, випадок: два нащадки)
        tree.insert(new Student("Бондаренко", 50000, 4.8, true)); // видалити

        // Лівий бік
        tree.insert(new Student("Ткаченко", 30000, 4.0, false)); // залишити
        tree.insert(new Student("Лисенко", 20000, 4.9, true)); // видалити (випадок: лист)
        tree.insert(new Student("Гриценко", 35000, 3.8, true)); // залишити
        tree.insert(new Student("Савченко", 32000, 4.6, true)); // видалити (випадок: один нащадок)
        tree.insert(new Student("Павленко", 31000, 3.5, false)); // залишити

        // Правий бік
        tree.insert(new Student("Морозенко", 70000, 4.7, true)); // видалити (випадок: два нащадки)
        tree.insert(new Student("Давиденко", 60000, 4.1, false)); // залишити
        tree.insert(new Student("Романенко", 80000, 5.0, true)); // видалити (випадок: лист)
        tree.insert(new Student("Васильченко", 65000, 4.3, true)); // залишити
        tree.insert(new Student("Литвиненко", 75000, 3.9, false)); // залишити
        tree.insert(new Student("Марченко", 85000, 4.4, true)); // залишити

        System.out.println("\nДодано студентів до дерева");
        System.out.printf("Загальна кількість студентів: %d%n", tree.size());

        // Показуємо початковий стан
        System.out.println("\n--- Стан дерева до видалення ---");
        tree.displayParallelTraversal();
        tree.displayInOrderTraversal();

        // Знаходимо студентів для видалення
        List<Student> toRemove = tree.findBySearchCriteria();
        tree.displaySearchResults(toRemove, "студенти для видалення");

        // Видаляємо студентів
        int removedCount = tree.removeBySearchCriteria();
        System.out.printf("Видалено студентів: %d%n", removedCount);
        System.out.printf("Залишилось у дереві: %d%n", tree.size());

        // Показуємо кінцевий стан
        System.out.println("\n--- Стан дерева після видалення ---");
        tree.displayParallelTraversal();
        tree.displayInOrderTraversal();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("ДЕМОНСТРАЦІЮ ЗАВЕРШЕНО");
        System.out.println("Продемонстровано всі випадки видалення вузлів:");
        System.out.println("✓ Листки без нащадків");
        System.out.println("✓ Вузли з одним нащадком");
        System.out.println("✓ Вузли з двома нащадками");
        System.out.println("✓ Батьківсько-дочірні відносини");
        System.out.println("=".repeat(60));
    }
}