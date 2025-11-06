package org.atsd;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=".repeat(60));
        System.out.println("Лаба 1.5");
        System.out.println("Дослідження алгоритмів пошуку");
        System.out.println("=".repeat(60));

        // Завдання першого рівня
        System.out.println("\n1. Перше завдання");
        System.out.println("Послідовний пошук у масиві, упорядкованому за групою");
        System.out.println("-".repeat(55));

        levelOneTask();

        // Завдання другого рівня
        System.out.println("\n2. Друге завдання");
        System.out.println("BST-дерево з ротаціями та пошук за ключем");
        System.out.println("-".repeat(45));

        levelTwoTask();

        // Завдання третього рівня
        System.out.println("\n3. Третє завдання");
        System.out.println("BST-дерево з балансуванням (оптимізація)");
        System.out.println("-".repeat(45));

        levelThreeTask();

        scanner.close();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("Демонстрацію завершено");
        System.out.println("Продемонстровано алгоритми пошуку:");
        System.out.println("✓ Послідовний пошук у масиві");
        System.out.println("✓ BST-дерево з вставкою в корінь та ротаціями");
        System.out.println("✓ Збалансоване BST-дерево (AVL)");
        System.out.println("=".repeat(60));
    }

    // Завдання першого рівня: послідовний пошук в упорядкованому масиві
    private static void levelOneTask() {
        System.out.println("Створення масиву студентів (мін. 20 елементів):");
        System.out.println("Правило формування: упорядкований за номером групи");

        // Створюємо тестовий масив студентів
        Student[] array = SearchAlgorithms.createTestArray();
        int studentCount = SearchAlgorithms.countStudents(array);

        System.out.printf("Створено масив з %d студентів%n", studentCount);

        // Виводимо вміст масиву
        SearchAlgorithms.displayArray(array, "=== Масив студентів (упорядкований за групою) ===");

        // Виконуємо завдання: підрахунок студенток з балом > 4.5 у заданій групі
        System.out.println("=== Виконання завдання ===");
        System.out.println("Завдання: підрахувати кількість студенток із середнім балом вище 4.5");

        String[] groupsToSearch = {"ІТ-21", "КБ-21", "ПІ-21"};

        for (String group : groupsToSearch) {
            // Послідовний пошук
            int result = SearchAlgorithms.sequentialSearchExcellentFemales(array, group);
            int count = Math.abs(result); // Перетворюємо з від'ємного

            System.out.printf("%nГрупа %s:%n", group);
            System.out.printf("Знайдено студенток з балом > 4.5: %d%n", count);

            // Показуємо знайдених студенток
            List<Student> found = SearchAlgorithms.findExcellentFemalesInGroup(array, group);
            if (!found.isEmpty()) {
                System.out.println("Деталі знайдених студенток:");
                System.out.println(Student.getTableHeader());
                System.out.println(Student.getTableSeparator());
                for (Student student : found) {
                    System.out.println(student);
                }
            } else {
                System.out.println("Студенток з заданими критеріями не знайдено.");
            }
        }

        System.out.println("\nПояснення: Використано послідовний алгоритм пошуку");
        System.out.println("Складність: O(n) - перевіряємо кожен елемент масиву");
    }

    // Завдання другого рівня: BST-дерево з ротаціями
    private static void levelTwoTask() {
        System.out.println("Створення BST-дерева з середнім балом як ключем:");
        System.out.println("Метод: вставка в корінь з використанням ротацій");

        StudentBST bst = new StudentBST();

        // Створюємо набір студентів для демонстрації
        Student[] students = {
            new Student("Іваненко", "Олексій", "ІТ-21", "Ч", 4.2),
            new Student("Петрова", "Марія", "КБ-22", "Ж", 4.8),
            new Student("Сидоров", "Петро", "ПІ-21", "Ч", 3.5),
            new Student("Коваленко", "Анна", "ІТ-22", "Ж", 4.9),
            new Student("Мельник", "Іван", "КБ-21", "Ч", 3.8),
            new Student("Шевченко", "Оксана", "ПІ-22", "Ж", 4.6),
            new Student("Кравченко", "Андрій", "ІТ-21", "Ч", 4.1)
        };

        System.out.printf("Додавання %d студентів до BST-дерева:%n", students.length);

        // Додаємо студентів по одному та показуємо стан дерева
        for (int i = 0; i < students.length; i++) {
            Student student = students[i];
            System.out.printf("%n--- Додавання студента #%d (бал %.1f) ---%n",
                i + 1, student.getAverageGrade());

            bst.insertAtRoot(student);

            bst.displayBreadthFirst("Стан дерева після додавання:");
            System.out.printf("Розмір дерева: %d, Висота: %d%n", bst.size(), bst.height());
        }

        // Демонстрація пошуку
        System.out.println("\n=== Демонстрація пошуку за ключем ===");
        double[] searchKeys = {4.8, 4.1, 5.0, 3.5};

        for (double key : searchKeys) {
            Student found = bst.search(key);
            System.out.printf("Пошук студента з балом %.1f: ", key);

            if (found != null) {
                System.out.printf("ЗНАЙДЕНО - %s %s%n", found.getSurname(), found.getName());
            } else {
                System.out.println("НЕ ЗНАЙДЕНО");
            }
        }

        System.out.println("\nПояснення: BST з вставкою в корінь забезпечує швидкий доступ");
        System.out.println("до останньо доданих елементів за рахунок ротацій");
    }

    // Завдання третього рівня: збалансоване BST-дерево
    private static void levelThreeTask() {
        System.out.println("Створення збалансованого BST-дерева (AVL):");
        System.out.println("Метод балансування: оптимізація (AVL-дерево)");

        StudentBST balancedBST = new StudentBST();

        // Створюємо той самий набір студентів
        Student[] students = {
            new Student("Іваненко", "Олексій", "ІТ-21", "Ч", 4.2),
            new Student("Петрова", "Марія", "КБ-22", "Ж", 4.8),
            new Student("Сидоров", "Петро", "ПІ-21", "Ч", 3.5),
            new Student("Коваленко", "Анна", "ІТ-22", "Ж", 4.9),
            new Student("Мельник", "Іван", "КБ-21", "Ч", 3.8),
            new Student("Шевченко", "Оксана", "ПІ-22", "Ж", 4.6),
            new Student("Кравченко", "Андрій", "ІТ-21", "Ч", 4.1),
            new Student("Лисенко", "Катерина", "КБ-22", "Ж", 4.7),
            new Student("Бондаренко", "Микола", "ПІ-21", "Ч", 3.9)
        };

        System.out.printf("Додавання %d студентів до збалансованого BST:%n", students.length);

        // Додаємо студентів з автоматичним балансуванням
        for (int i = 0; i < students.length; i++) {
            Student student = students[i];
            System.out.printf("%n--- Додавання студента #%d (бал %.1f) ---%n",
                i + 1, student.getAverageGrade());

            balancedBST.insertBalanced(student);

            if ((i + 1) % 3 == 0 || i == students.length - 1) {
                // Показуємо стан дерева кожні 3 додавання
                balancedBST.displayBreadthFirst("Стан збалансованого дерева:");
                System.out.printf("Розмір: %d, Висота: %d, Збалансоване: %s%n",
                    balancedBST.size(), balancedBST.height(),
                    balancedBST.isBalanced() ? "ТАК" : "НІ");
            }
        }

        // Демонстрація пошуку в збалансованому дереві
        System.out.println("\n=== Демонстрація пошуку в збалансованому дереві ===");
        double[] searchKeys = {4.9, 3.5, 4.6, 4.0};

        for (double key : searchKeys) {
            Student found = balancedBST.search(key);
            System.out.printf("Пошук студента з балом %.1f: ", key);

            if (found != null) {
                System.out.printf("ЗНАЙДЕНО - %s %s (група %s)%n",
                    found.getSurname(), found.getName(), found.getGroup());
            } else {
                System.out.println("НЕ ЗНАЙДЕНО");
            }
        }

        // Порівняння з незбалансованим деревом
        System.out.println("\n=== Порівняння методів балансування ===");
        System.out.printf("Збалансоване дерево: висота %d, збалансованість %s%n",
            balancedBST.height(), balancedBST.isBalanced() ? "ТАК" : "НІ");

        System.out.println("\nПереваги AVL-балансування:");
        System.out.println("✓ Гарантована логарифмічна складність пошуку O(log n)");
        System.out.println("✓ Автоматичне підтримання оптимальної структури дерева");
        System.out.println("✓ Мінімальна висота дерева для заданої кількості вузлів");
    }
}