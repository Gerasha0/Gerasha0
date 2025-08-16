package org.atsd;

/**
 * Лабораторна робота 1.1 - Дослідження лінійних структур даних
 * Варіант 7
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНА РОБОТА 1.1 ===");
        System.out.println("Дослідження лінійних структур даних");
        System.out.println("Варіант 7\n");

        // Завдання першого рівня
        task1();

        // Завдання другого рівня
        task2();

        // Завдання третього рівня
        task3();
    }

    /**
     * Завдання першого рівня: Список з векторним способом розміщення
     * Тип елементів: цілі числа
     */
    private static void task1() {
        System.out.println("=== ЗАВДАННЯ ПЕРШОГО РІВНЯ ===");
        System.out.println("Список з векторним способом розміщення елементів");
        System.out.println("Тип елементів: цілі числа\n");

        // Створюємо екземпляр структури даних
        VectorList list = new VectorList(10);
        System.out.println("Створено список з ємністю 10 елементів");
        list.display();

        // Вставляємо елементи в структуру даних
        System.out.println("\n--- Вставка елементів ---");
        list.add(5);
        list.add(-3);
        list.add(12);
        list.add(0);
        list.add(-7);
        list.add(8);
        list.display();

        // Вставляємо елемент за індексом
        list.add(2, 99);
        list.display();

        // Видаляємо кілька елементів
        System.out.println("\n--- Видалення елементів ---");
        try {
            list.remove(1); // Видаляємо елемент за індексом 1
            list.display();

            list.removeLast(); // Видаляємо останній елемент
            list.display();

            list.remove(0); // Видаляємо перший елемент
            list.display();
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        System.out.println("Завдання першого рівня виконано!\n");
    }

    /**
     * Завдання другого рівня: Стек зі зв'язним способом розміщення
     * Тип елементів: рядкові (цілі додатні числа у вісімковій системі)
     */
    private static void task2() {
        System.out.println("=== ЗАВДАННЯ ДРУГОГО РІВНЯ ===");
        System.out.println("Стек зі зв'язним способом розміщення елементів");
        System.out.println("Тип елементів: рядкові (цілі додатні числа у вісімковій системі)\n");

        // Створюємо екземпляр структури даних
        LinkedStack stack = new LinkedStack();
        System.out.println("Створено порожній стек");
        stack.display();

        // Вставляємо елементи в структуру даних (вісімкові числа)
        System.out.println("\n--- Додавання елементів у стек ---");
        stack.push("17");   // 15 у десятковій
        stack.push("25");   // 21 у десятковій
        stack.push("77");   // 63 у десятковій
        stack.push("123");  // 83 у десятковій
        stack.push("456");  // 302 у десятковій
        stack.display();

        // Видаляємо кілька елементів
        System.out.println("\n--- Вилучення елементів зі стеку ---");
        try {
            stack.pop();
            stack.display();

            stack.pop();
            stack.display();

            System.out.println("Верхній елемент стеку: " + stack.peek());
            stack.display();
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        System.out.println("Завдання другого рівня виконано!\n");
    }

    /**
     * Завдання третього рівня: Робота з двома структурами даних
     * Видалити зі списку від'ємні елементи, а решту перетворити
     * у вісімкову систему та перемістити у стек
     */
    private static void task3() {
        System.out.println("=== ЗАВДАННЯ ТРЕТЬОГО РІВНЯ ===");
        System.out.println("Видалення від'ємних елементів зі списку та переміщення");
        System.out.println("додатних елементів у вісімковій системі у стек\n");

        // Створюємо екземпляри структур даних
        VectorList sourceList = new VectorList(15);
        LinkedStack targetStack = new LinkedStack();

        System.out.println("Створено список та стек");

        // Заповнюємо першу структуру даних різними числами
        System.out.println("\n--- Заповнення вихідного списку ---");
        int[] testData = {15, -8, 23, -5, 0, 42, -12, 7, 19, -3, 64, 8};
        for (int value : testData) {
            sourceList.add(value);
        }
        sourceList.display();

        // Обробляємо список згідно із завданням
        System.out.println("\n--- Обробка списку ---");
        System.out.println("Видаляємо від'ємні елементи та перетворюємо додатні у вісімкову систему:");

        // Створюємо тимчасовий список для додатних елементів
        VectorList positiveList = new VectorList(15);

        for (int i = 0; i < sourceList.size(); i++) {
            int element = sourceList.get(i);
            if (element > 0) {
                positiveList.add(element);
                String octalValue = NumberConverter.toOctal(element);
                System.out.println(element + " (десяткова) -> " + octalValue + " (вісімкова)");
                targetStack.push(octalValue);
            } else if (element < 0) {
                System.out.println("Видаляємо від'ємний елемент: " + element);
            } else {
                System.out.println("Нуль пропускаємо (не додатний)");
            }
        }

        // Виводимо результати
        System.out.println("\n--- Результати обробки ---");
        System.out.println("Вихідний список:");
        sourceList.display();

        System.out.println("Додатні елементи (будуть видалені зі списку):");
        positiveList.display();

        // Видаляємо додатні елементи з вихідного списку (йдемо з кінця)
        for (int i = sourceList.size() - 1; i >= 0; i--) {
            int element = sourceList.get(i);
            if (element > 0) {
                sourceList.remove(i);
            }
        }

        System.out.println("Список після видалення додатніх елементів:");
        sourceList.display();

        System.out.println("Стек з додатніми елементами у вісімковій системі:");
        targetStack.display();

        System.out.println("\n--- Демонстрація роботи зі стеком ---");
        System.out.println("Вилучаємо кілька елементів зі стеку:");
        try {
            targetStack.pop();
            targetStack.pop();
            targetStack.display();
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        System.out.println("Завдання третього рівня виконано!");
        System.out.println("\n=== ЛАБОРАТОРНА РОБОТА ЗАВЕРШЕНА ===");
    }
}
