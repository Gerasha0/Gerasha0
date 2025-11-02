package org.atsd;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Головний клас для демонстрації алгоритмів ідентифікації
 * Лабораторна робота 2.2 - Варіант 7
 */
public class App {

    private static Scanner scanner = new Scanner(System.in);
    private static final String WORDS_FILE = "words.txt";
    private static final String DELIMITED_TEXT_FILE = "delimited_text.txt";

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║         ЛАБОРАТОРНА РОБОТА 2.2 - ВАРІАНТ 7                     ║");
        System.out.println("║        ДОСЛІДЖЕННЯ АЛГОРИТМІВ ІДЕНТИФІКАЦІЇ                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Створюємо тестові файли якщо вони не існують
        createTestFilesIfNotExist();

        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = getIntInput("Виберіть завдання (1-5): ");

            switch (choice) {
                case 1:
                    performTask1();
                    break;
                case 2:
                    performTask2();
                    break;
                case 3:
                    performTask3();
                    break;
                case 4:
                    demonstrateAllTasks();
                    break;
                case 5:
                    showAutomatonGraphs();
                    break;
                case 0:
                    running = false;
                    System.out.println("До побачення!");
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте знову.");
            }
        }

        scanner.close();
    }

    private static void showMainMenu() {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│                        ГОЛОВНЕ МЕНЮ                         │");
        System.out.println("├─────────────────────────────────────────────────────────────┤");
        System.out.println("│ 1. Завдання 1 - Пошук слів регулярними виразами             │");
        System.out.println("│    Шаблон: {цифри} або {великі літери}                      │");
        System.out.println("├─────────────────────────────────────────────────────────────┤");
        System.out.println("│ 2. Завдання 2 - Скінченний автомат (switch)                 │");
        System.out.println("│    Розпізнавання: \\{(\\d+|[A-Z]+)\\}                       │");
        System.out.println("├─────────────────────────────────────────────────────────────┤");
        System.out.println("│ 3. Завдання 3 - Автомат з таблицею переходів (for)          │");
        System.out.println("│    Слова з роздільниками: !content&                         │");
        System.out.println("├─────────────────────────────────────────────────────────────┤");
        System.out.println("│ 4. Демонстрація всіх завдань                                │");
        System.out.println("│ 5. Показати графи автоматів та таблиці переходів            │");
        System.out.println("│ 0. Вихід                                                    │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");
    }

    // =============== ЗАВДАННЯ 1: РЕГУЛЯРНІ ВИРАЗИ ===============

    private static void performTask1() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("             ЗАВДАННЯ 1: ПОШУК СЛІВ РЕГУЛЯРНИМИ ВИРАЗАМИ");
        System.out.println("               Шаблон: {цифри} або {великі літери}");
        System.out.println("═".repeat(60));

        try {
            // Читаємо слова з файлу
            List<String> words = readWordsFromFile(WORDS_FILE);
            if (words.isEmpty()) {
                System.out.println("Файл " + WORDS_FILE + " порожній або не існує.");
                System.out.println("Створюю тестові дані...");
                createWordsFile();
                words = readWordsFromFile(WORDS_FILE);
            }

            System.out.println("Прочитано " + words.size() + " слів з файлу " + WORDS_FILE);
            System.out.println("\nВсі слова:");
            for (int i = 0; i < words.size(); i++) {
                System.out.printf("%2d. %s%n", i + 1, words.get(i));
            }

            // Шукаємо слова, що відповідають шаблону
            List<String> matchingWords = TextPatternRecognizer.findMatchingWords(words);

            System.out.println("\n" + "─".repeat(50));
            System.out.println("РЕЗУЛЬТАТИ ПОШУКУ:");
            if (matchingWords.isEmpty()) {
                System.out.println("❌ Не знайдено жодного слова, що відповідає шаблону");
            } else {
                System.out.println("✅ Знайдено " + matchingWords.size() + " слів, що відповідають шаблону:");
                for (String word : matchingWords) {
                    System.out.println("   → " + word);
                }
            }

            // Детальний аналіз кожного слова
            System.out.println("\n" + "─".repeat(50));
            System.out.println("ДЕТАЛЬНИЙ АНАЛІЗ:");
            TextPatternRecognizer.demonstrateRegexMatching(words);

        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
        }

        waitForEnter();
    }

    // =============== ЗАВДАННЯ 2: СКІНЧЕННИЙ АВТОМАТ (SWITCH) ===============

    private static void performTask2() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("        ЗАВДАННЯ 2: СКІНЧЕННИЙ АВТОМАТ (SWITCH)");
        System.out.println("           Регулярний вираз: \\{(\\d+|[A-Z]+)\\}");
        System.out.println("═".repeat(60));

        // Демонструємо роботу автомата на тестових даних
        List<String> testInputs = Arrays.asList(
            "{123}",      // Правильний (цифри)
            "{ABC}",      // Правильний (великі літери)
            "{XYZ456}",   // Неправильний (змішані)
            "123}",       // Неправильний (без відкриваючої дужки)
            "{123",       // Неправильний (без закриваючої дужки)
            "{}",         // Неправильний (порожній)
            "{abc}",      // Неправильний (малі літери)
            "{12A}",      // Неправильний (змішані символи)
            "{A1B}",      // Неправильний (змішані символи)
            "{}ABC"       // Неправильний (зайві символи)
        );

        TextPatternRecognizer.demonstrateAutomatonRecognition(testInputs);

        // Інтерактивне введення
        System.out.println("🔍 ІНТЕРАКТИВНЕ ТЕСТУВАННЯ:");
        System.out.println("Введіть рядки для розпізнавання (порожній рядок для завершення):");

        while (true) {
            System.out.print("Введіть рядок: ");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                break;
            }

            boolean recognized = TextPatternRecognizer.recognizeWithSwitchAutomaton(input);
            System.out.printf("Результат: %s%n%n",
                recognized ? "✅ РОЗПІЗНАНО" : "❌ ВІДХИЛЕНО");
        }

        waitForEnter();
    }

    // =============== ЗАВДАННЯ 3: АВТОМАТ З ТАБЛИЦЕЮ ПЕРЕХОДІВ ===============

    private static void performTask3() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("     ЗАВДАННЯ 3: АВТОМАТ З ТАБЛИЦЕЮ ПЕРЕХОДІВ (FOR)");
        System.out.println("              Слова з роздільниками: !content&");
        System.out.println("═".repeat(60));

        try {
            // Читаємо текст з файлу
            String text = readTextFromFile(DELIMITED_TEXT_FILE);
            if (text.isEmpty()) {
                System.out.println("Файл " + DELIMITED_TEXT_FILE + " порожній або не існує.");
                System.out.println("Створюю тестові дані...");
                createDelimitedTextFile();
                text = readTextFromFile(DELIMITED_TEXT_FILE);
            }

            System.out.println("Прочитаний текст:");
            System.out.println("\"" + text + "\"");
            System.out.println();

            // Виділяємо слова з роздільниками
            List<String> extractedWords = TextPatternRecognizer.extractWordsWithDelimiters(text);

            System.out.println("─".repeat(50));
            System.out.println("ВИДІЛЕНІ СЛОВА З РОЗДІЛЬНИКАМИ:");
            if (extractedWords.isEmpty()) {
                System.out.println("❌ Не знайдено слів з роздільниками !...&");
            } else {
                for (int i = 0; i < extractedWords.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, extractedWords.get(i));
                }
            }

            // Перевіряємо кожне слово автоматом
            System.out.println("\n" + "─".repeat(50));
            System.out.println("АНАЛІЗ АВТОМАТОМ З ТАБЛИЦЕЮ ПЕРЕХОДІВ:");

            for (String word : extractedWords) {
                boolean recognized = TextPatternRecognizer.recognizeWithTableAutomaton(word);
                System.out.printf("%-20s -> %s%n",
                    word, recognized ? "✅ РОЗПІЗНАНО" : "❌ ВІДХИЛЕНО");
            }

            // Тестування додаткових випадків
            System.out.println("\n" + "─".repeat(50));
            System.out.println("ТЕСТУВАННЯ ГРАНИЧНИХ ВИПАДКІВ:");

            List<String> testCases = Arrays.asList(
                "!Hello&",           // Правильне слово
                "!123&",             // Цифри
                "!Hello World&",     // З пробілом
                "!&",                // Порожнє слово
                "Hello&",            // Без початкового роздільника
                "!Hello",            // Без кінцевого роздільника
                "!Hello!World&",     // З внутрішнім !
                "!Hello&World",      // З символами після &
                ""                   // Порожній рядок
            );

            TextPatternRecognizer.demonstrateTableAutomaton(testCases);

        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
        }

        waitForEnter();
    }

    // =============== ДЕМОНСТРАЦІЯ ВСІХ ЗАВДАНЬ ===============

    private static void demonstrateAllTasks() {
        System.out.println("\n" + "═".repeat(80));
        System.out.println("                    ДЕМОНСТРАЦІЯ ВСІХ ЗАВДАНЬ");
        System.out.println("═".repeat(80));

        // Завдання 1
        System.out.println("\n🔍 ЗАВДАННЯ 1: Регулярні вирази");
        List<String> task1TestWords = Arrays.asList(
            "{123}", "{ABC}", "{XYZ}", "{999}", "{hello}", "123", "{12A}", "{}", "ABC}"
        );
        TextPatternRecognizer.demonstrateRegexMatching(task1TestWords);

        // Завдання 2
        System.out.println("🤖 ЗАВДАННЯ 2: Скінченний автомат (switch)");
        List<String> task2TestInputs = Arrays.asList(
            "{123}", "{ABC}", "{XYZ456}", "123}", "{123", "{}", "{abc}"
        );
        TextPatternRecognizer.demonstrateAutomatonRecognition(task2TestInputs);

        // Завдання 3
        System.out.println("📊 ЗАВДАННЯ 3: Автомат з таблицею переходів (for)");
        List<String> task3TestInputs = Arrays.asList(
            "!Hello&", "!123&", "!Hello World&", "!&", "Hello&", "!Hello"
        );
        TextPatternRecognizer.demonstrateTableAutomaton(task3TestInputs);

        System.out.println("═".repeat(80));
        System.out.println("                        ДЕМОНСТРАЦІЮ ЗАВЕРШЕНО");
        System.out.println("═".repeat(80));

        waitForEnter();
    }

    // =============== ГРАФИ ТА ТАБЛИЦІ ПЕРЕХОДІВ ===============

    private static void showAutomatonGraphs() {
        System.out.println("\n" + "═".repeat(80));
        System.out.println("            ГРАФИ СКІНЧЕННИХ АВТОМАТІВ ТА ТАБЛИЦІ ПЕРЕХОДІВ");
        System.out.println("═".repeat(80));

        System.out.println(TextPatternRecognizer.getAutomatonDescription());

        waitForEnter();
    }

    // =============== РОБОТА З ФАЙЛАМИ ===============

    private static void createTestFilesIfNotExist() {
        File wordsFile = new File(WORDS_FILE);
        File delimitedFile = new File(DELIMITED_TEXT_FILE);

        try {
            if (!wordsFile.exists()) {
                createWordsFile();
            }
            if (!delimitedFile.exists()) {
                createDelimitedTextFile();
            }
        } catch (IOException e) {
            System.err.println("Помилка створення тестових файлів: " + e.getMessage());
        }
    }

    private static void createWordsFile() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(WORDS_FILE, false))) {
            // Правильні слова (відповідають шаблону)
            writer.println("{123}");
            writer.println("{ABC}");
            writer.println("{999}");
            writer.println("{XYZ}");
            writer.println("{777}");
            writer.println("{HELLO}");

            // Неправильні слова
            writer.println("{12A}");     // Змішані символи
            writer.println("{abc}");     // Малі літери
            writer.println("123}");      // Без відкриваючої дужки
            writer.println("{123");      // Без закриваючої дужки
            writer.println("{}");        // Порожні дужки
            writer.println("ABC");       // Без дужок взагалі
            writer.println("{Hello}");   // Змішаний регістр
            writer.println("{12 34}");   // З пробілом
            writer.println("{A1B2}");    // Чергування літер і цифр
        }

        System.out.println("✅ Створено файл " + WORDS_FILE + " з тестовими словами");
    }

    private static void createDelimitedTextFile() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DELIMITED_TEXT_FILE, false))) {
            writer.print("Це приклад тексту з !словами& різного типу. ");
            writer.print("Тут є !правильні слова& та !123& і навіть !Hello World&. ");
            writer.print("Але також є неправильні: слово& без початку та !слово без кінця. ");
            writer.print("Ще один !тест& та !порожнє&.");
        }

        System.out.println("✅ Створено файл " + DELIMITED_TEXT_FILE + " з тестовим текстом");
    }

    private static List<String> readWordsFromFile(String filename) throws IOException {
        List<String> words = new ArrayList<>();
        File file = new File(filename);

        if (!file.exists()) {
            return words;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    words.add(line);
                }
            }
        }

        return words;
    }

    private static String readTextFromFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            return "";
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(" ");
            }
        }

        return content.toString().trim();
    }

    // =============== ДОПОМІЖНІ МЕТОДИ ===============

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // Споживаємо залишковий символ нового рядка
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Помилка: введіть ціле число.");
                scanner.nextLine(); // Очищаємо некоректний ввід
            }
        }
    }

    private static void waitForEnter() {
        System.out.println("\nНатисніть Enter, щоб продовжити...");
        scanner.nextLine();
    }
}
