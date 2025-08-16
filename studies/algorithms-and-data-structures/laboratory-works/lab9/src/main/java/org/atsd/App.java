package org.atsd;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Лабораторна робота 2.3
 * Дослідження комбінаторних алгоритмів
 *
 * Варіант 7
 * Автор: [Ваше ім'я]
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔" + "═".repeat(48) + "╗");
        System.out.println("║     ЛАБОРАТОРНА РОБОТА 2.3                   ║");
        System.out.println("║  Дослідження комбінаторних алгоритмів        ║");
        System.out.println("║              Варіант 7                       ║");
        System.out.println("╚" + "═".repeat(48) + "╝");
        System.out.println();

        while (true) {
            System.out.println("╭" + "─".repeat(48) + "╮");
            System.out.println("│                ГОЛОВНЕ МЕНЮ                  │");
            System.out.println("├" + "─".repeat(48) + "┤");
            System.out.println("│ 1. Завдання першого рівня                    │");
            System.out.println("│ 2. Завдання другого рівня                    │");
            System.out.println("│ 3. Завдання третього рівня                   │");
            System.out.println("│ 4. Виконати всі завдання послідовно          │");
            System.out.println("│ 0. Вихід                                     │");
            System.out.println("╰" + "─".repeat(48) + "╯");
            System.out.print("Оберіть опцію (0-4): ");

            int choice = getValidIntInput(scanner, 0, 4);

            switch (choice) {
                case 1:
                    executeTask1(scanner);
                    break;
                case 2:
                    executeTask2(scanner);
                    break;
                case 3:
                    executeTask3(scanner);
                    break;
                case 4:
                    executeAllTasks(scanner);
                    break;
                case 0:
                    System.out.println("\n╔" + "═".repeat(40) + "╗");
                    System.out.println("║   Дякує��о за використання програми!    ║");
                    System.out.println("║          До побачення! 👋               ║");
                    System.out.println("╚" + "═".repeat(40) + "╝");
                    scanner.close();
                    return;
                default:
                    System.out.println("\n❌ Невірний вибір! Спробуйте ще раз.\n");
                    continue;
            }

            // Пауза перед поверненням до меню
            System.out.println("\n" + "─".repeat(50));
            System.out.println("Натисніть Enter щоб повернутися до меню...");
            waitForEnter(scanner);
            System.out.println();
        }
    }

    /**
     * Отримує валідний цілочисельний ввід від користувача
     */
    private static int getValidIntInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("❌ Введіть число від " + min + " до " + max + ": ");
                    continue;
                }

                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.print("❌ Введіть число від " + min + " до " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("❌ Введіть коректне число: ");
            } catch (Exception e) {
                System.out.print("❌ Помилка вводу! Введіть число: ");
            }
        }
    }

    /**
     * Отримує валідний позитивний цілочисельний ввід
     */
    private static int getValidPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("❌ Введіть позитивне число більше 0!");
                    continue;
                }

                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("❌ Введіть позитивне число більше 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Введіть коректне число!");
            } catch (Exception e) {
                System.out.println("❌ Помилка вводу! Спробуйте ще раз.");
            }
        }
    }

    /**
     * Чекає натискання Enter від користувача
     */
    private static void waitForEnter(Scanner scanner) {
        try {
            scanner.nextLine();
        } catch (Exception e) {
            // Ігноруємо помилки
        }
    }

    private static void executeTask1(Scanner scanner) {
        System.out.println("\n╭" + "─".repeat(50) + "╮");
        System.out.println("│           ЗАВДАННЯ ПЕРШОГО РІВНЯ             │");
        System.out.println("╰" + "─".repeat(50) + "╯");

        System.out.println("📋 Умова: Спортивний гурток містить учасників.");
        System.out.println("   Потрібно скласти команду для естафети,");
        System.out.println("   де важлива послідовність учасників.");
        System.out.println();

        int n1 = getValidPositiveInt(scanner, "👥 Введіть кількість учасників у гуртку: ");
        int k1 = getValidPositiveInt(scanner, "🏃 Введіть кількість спортсменів у команді: ");

        if (k1 > n1) {
            System.out.println("❌ Кількість спортсменів у команді не може бути більшою за кількість учасників!");
            k1 = getValidPositiveInt(scanner, "🏃 Введіть коректну кількість спортсменів у команді (≤" + n1 + "): ");
        }

        System.out.println("\n🔍 АНАЛІЗ ЗАДАЧІ:");
        System.out.println("   • Маємо " + n1 + " різних учасників");
        System.out.println("   • Потрібно вибрати " + k1 + " учасників");
        System.out.println("   • Важлива послідовність (порядок) учасників");
        System.out.println("   • Кожен учасник може бути вибраний тільки один раз");

        System.out.println("\n💡 ТИП ВИБІРКИ: РОЗМІЩЕННЯ БЕЗ ПОВТОРЕНЬ");
        System.out.println("📐 ФОРМУЛА: A(n,k) = n! / (n-k)!");

        System.out.print("\n⚙️  Обчислюємо...");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(500);
                System.out.print(".");
            } catch (InterruptedException e) {}
        }

        long result1 = calculateArrangements(n1, k1);

        System.out.println("\n\n✅ РЕЗУЛЬТАТ:");
        System.out.println("   A(" + n1 + "," + k1 + ") = " + n1 + "! / (" + n1 + "-" + k1 + ")!");
        System.out.println("   A(" + n1 + "," + k1 + ") = " + n1 + "! / " + (n1-k1) + "!");

        // Показуємо обчислення покроково
        System.out.print("   A(" + n1 + "," + k1 + ") = ");
        for (int i = n1; i > n1 - k1; i--) {
            System.out.print(i);
            if (i > n1 - k1 + 1) {
                System.out.print(" × ");
            }
        }
        System.out.println(" = " + result1);

        System.out.println("\n🎯 ВІДПОВІДЬ: " + result1 + " варіантів складу команди");
    }

    private static void executeTask2(Scanner scanner) {
        System.out.println("\n╭" + "─".repeat(50) + "╮");
        System.out.println("│           ЗАВДАННЯ ДРУГОГО РІВНЯ             │");
        System.out.println("╰" + "─".repeat(50) + "╯");

        System.out.println("📋 Умова: Цифри двійкової системи числення (0, 1).");
        System.out.println("   Потрібно визначити кількість чисел певного розміру.");
        System.out.println();

        int bits = getValidPositiveInt(scanner, "💾 Введіть кількість біт (для одного байта = 8): ");

        System.out.println("\n🔍 АНАЛІЗ ЗАДАЧІ:");
        System.out.println("   • Маємо 2 цифри двійкової системи: {0, 1}");
        System.out.println("   • Потрібно сформувати число з " + bits + " біт");
        System.out.println("   • На кожній позиції може бути будь-яка з 2 цифр");
        System.out.println("   • Цифри можуть повторюватись");

        System.out.println("\n💡 ТИП ВИБІРКИ: РОЗМІЩЕННЯ З ПОВТОРЕННЯМИ");
        System.out.println("📐 ФОРМУЛА: A'(n,k) = n^k");

        System.out.print("\n���️  Обчислюємо...");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(500);
                System.out.print(".");
            } catch (InterruptedException e) {}
        }

        long result2 = calculateArrangementsWithRepetition(2, bits);

        System.out.println("\n\n✅ РЕЗУЛЬТАТ:");
        System.out.println("   A'(2," + bits + ") = 2^" + bits + " = " + result2);

        System.out.println("\n🎯 ВІДПОВІДЬ: " + result2 + " різних чисел розміром " + bits + " біт");

        if (bits <= 4) {
            System.out.println("\n��� Приклади чисел (перші кілька):");
            showBinaryNumbers(bits, Math.min(16, (int)result2));
        }
    }

    private static void executeTask3(Scanner scanner) {
        System.out.println("\n╭" + "─".repeat(50) + "╮");
        System.out.println("│          ЗАВДАННЯ ТРЕТЬОГО РІВНЯ             │");
        System.out.println("╰" + "─".repeat(50) + "╯");

        System.out.println("📋 Умова: Записати у файл повний перелік розміщень");
        System.out.println("   для завдання першого рівня.");
        System.out.println();

        int n = getValidPositiveInt(scanner, "👥 Введіть кількість учасників у гуртку: ");
        int k = getValidPositiveInt(scanner, "🏃 Введіть кількість спортсменів у команді: ");

        if (k > n) {
            System.out.println("❌ Кількість спортсменів у команді не може бути більшою за кількість учасників!");
            k = getValidPositiveInt(scanner, "🏃 Введіть коректну кількість спортсменів у команді (≤" + n + "): ");
        }

        long totalArrangements = calculateArrangements(n, k);

        System.out.println("\n🔢 Загальна кількість розміщень A(" + n + "," + k + ") = " + totalArrangements);

        if (totalArrangements > 5000) {
            System.out.println("⚠️  ПОПЕРЕДЖЕННЯ: Велика кількість розміщень (" + totalArrangements + ")!");
            System.out.print("   Продовжити генерацію? (y/n): ");
            String answer = scanner.nextLine().trim();
            if (!answer.toLowerCase().startsWith("y")) {
                System.out.println("❌ Генерацію скасовано.");
                return;
            }
        }

        System.out.print("\n⚙️  Генеруємо всі розміщення");
        generateAndSaveArrangements(n, k);
    }

    private static void executeAllTasks(Scanner scanner) {
        System.out.println("\n╭" + "─".repeat(55) + "╮");
        System.out.println("│        ВИКОНАННЯ ВСІХ ЗАВДАНЬ ПОСЛІДОВНО        │");
        System.out.println("╰" + "���".repeat(55) + "╯");

        // Завдання 1
        System.out.println("\n🔴 Виконуємо завдання першого рівня...");
        int n1 = getValidPositiveInt(scanner, "👥 Введіть кількість учасників у гуртку: ");
        int k1 = getValidPositiveInt(scanner, "🏃 Введіть кількість спортсменів у команді: ");

        if (k1 > n1) {
            System.out.println("❌ Кількість спортсменів у команді не може бути більшою за кількість учасників!");
            k1 = getValidPositiveInt(scanner, "🏃 Введіть коректну кількість спортсменів у команді (≤" + n1 + "): ");
        }

        long result1 = calculateArrangements(n1, k1);
        System.out.println("✅ Результат завдання 1: A(" + n1 + "," + k1 + ") = " + result1);

        // Завдання 2
        System.out.println("\n🟡 Виконуємо завдання другого рівня...");
        int bits = getValidPositiveInt(scanner, "💾 Введіть кількість біт: ");

        long result2 = calculateArrangementsWithRepetition(2, bits);
        System.out.println("✅ Результат завдання 2: A'(2," + bits + ") = " + result2);

        // Завдання 3
        System.out.println("\n🟢 Виконуємо завдання третього рівня...");
        System.out.println("📝 Генеруємо файл з розміщеннями для завдання 1...");
        generateAndSaveArrangements(n1, k1);

        System.out.println("\n🎉 ВСІ ЗАВДАННЯ ВИКОНАНО УСПІШНО!");
        System.out.println("📊 Підсумок:");
        System.out.println("   • Завдання 1: " + result1 + " варіантів команд��");
        System.out.println("   • Завдання 2: " + result2 + " двійкових чисел");
        System.out.println("   • Завдання 3: Файл 'розміщення.txt' створено");
    }

    private static void showBinaryNumbers(int bits, int count) {
        System.out.print("   ");
        for (int i = 0; i < count; i++) {
            String binary = String.format("%" + bits + "s", Integer.toBinaryString(i)).replace(' ', '0');
            System.out.print(binary + " ");
            if ((i + 1) % 8 == 0) {
                System.out.println();
                System.out.print("   ");
            }
        }
        if (count % 8 != 0) {
            System.out.println();
        }
    }

    /**
     * Обчислює розміщення без повторень A(n,k) = n! / (n-k)!
     */
    public static long calculateArrangements(int n, int k) {
        if (k > n) return 0;

        long result = 1;
        for (int i = n; i > n - k; i--) {
            result *= i;
        }
        return result;
    }

    /**
     * Обчислює розміщення з повтореннями A'(n,k) = n^k
     */
    public static long calculateArrangementsWithRepetition(int n, int k) {
        return (long) Math.pow(n, k);
    }

    /**
     * Обчислює факторіал числа
     */
    public static long factorial(int n) {
        if (n <= 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Генерує всі можливі розміщення та записує їх у файл
     */
    public static void generateAndSaveArrangements(int n, int k) {
        List<List<Integer>> arrangements = new ArrayList<>();

        // Показуємо прогрес
        System.out.println();
        System.out.print("🔄 Генерація розміщень: ");

        generateArrangementsRecursive(n, k, new ArrayList<>(), new boolean[n], arrangements);

        System.out.println("✅ Згенеровано!");

        // Створюємо файл у поточній директорії проекту
        String fileName = "розміщення.txt";
        String fullPath = System.getProperty("user.dir") + "\\" + fileName;

        try (FileWriter writer = new FileWriter(fileName, java.nio.charset.StandardCharsets.UTF_8)) {
            writer.write("╔" + "═".repeat(50) + "╗\n");
            writer.write("║          ВСІ МОЖЛИВІ РОЗМІЩЕННЯ A(" + n + "," + k + ")        ║\n");
            writer.write("║            Лабораторна робота 2.3            ║\n");
            writer.write("║                 Варіант 7                    ║\n");
            writer.write("╚" + "═".repeat(50) + "╝\n\n");

            writer.write("Загальна кількість: " + arrangements.size() + "\n");
            writer.write("═".repeat(52) + "\n\n");

            int counter = 1;
            int columns = 5; // кількість колонок для красивого відображення

            for (int i = 0; i < arrangements.size(); i++) {
                List<Integer> arrangement = arrangements.get(i);
                writer.write(String.format("%4d: (", counter++));

                for (int j = 0; j < arrangement.size(); j++) {
                    writer.write(arrangement.get(j).toString());
                    if (j < arrangement.size() - 1) {
                        writer.write(", ");
                    }
                }
                writer.write(")");

                if (i % columns == columns - 1) {
                    writer.write("\n");
                } else {
                    writer.write("  ");
                }
            }

            if (arrangements.size() % columns != 0) {
                writer.write("\n");
            }

            writer.write("\n" + "═".repeat(52) + "\n");
            writer.write("Файл згенеровано: " + java.time.LocalDateTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
            ) + "\n");

            System.out.println("💾 Результати збереженя у файл '" + fileName + "'");
            System.out.println("📁 Повний шлях: " + fullPath);
            System.out.println("📊 Файл містить " + arrangements.size() + " розміщень");
        } catch (IOException e) {
            System.err.println("❌ Помилка при записі у файл: " + e.getMessage());
        }
    }

    /**
     * Рекурсивна функція для генерації розміщень
     */
    private static void generateArrangementsRecursive(int n, int k, List<Integer> current,
                                                     boolean[] used, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!used[i-1]) {
                current.add(i);
                used[i-1] = true;
                generateArrangementsRecursive(n, k, current, used, result);
                current.remove(current.size() - 1);
                used[i-1] = false;
            }
        }
    }
}
