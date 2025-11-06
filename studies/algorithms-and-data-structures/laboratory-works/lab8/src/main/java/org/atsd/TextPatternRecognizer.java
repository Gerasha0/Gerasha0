package org.atsd;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class TextPatternRecognizer {

    // Завдання 1: Регулярні вирази

    // Регулярний вираз для варіанта 7:
    // Слово обов'язково починається символом «{», потім можуть йти послідовність із символів «0÷9»
    // або «A÷Z», закінчується слово символом «}»
    private static final Pattern TASK1_PATTERN = Pattern.compile("^\\{([0-9]+|[A-Z]+)\\}$");

    // Перевіряє слово на відповідність регулярному виразу завдання 1
    public static boolean matchesTask1Pattern(String word) {
        if (word == null) return false;
        return TASK1_PATTERN.matcher(word).matches(); // Прибрав .trim()
    }

    // Знаходить усі слова у тексті, що відповідають регулярному виразу завдання 1
    public static List<String> findMatchingWords(List<String> words) {
        List<String> matchingWords = new ArrayList<>();
        for (String word : words) {
            if (matchesTask1Pattern(word)) {
                matchingWords.add(word);
            }
        }
        return matchingWords;
    }

    // Завдання 2: Скінченний автомат на основі switch

    // Стани скінченного автомата для завдання 2
    // Регулярний вираз: \{(\d+|[A-Z]+)\}
    public enum AutomatonState {
        START,          // Початковий стан
        OPEN_BRACE,     // Після відкриваючої дужки {
        DIGITS,         // Читаємо цифри
        LETTERS,        // Читаємо великі літери
        CLOSE_BRACE,    // Після закриваючої дужки }
        ACCEPT,         // Фінальний стан (прийняття)
        REJECT          // Стан відхилення
    }

    // Синтаксичний аналізатор на основі скінченного автомата (switch)
    // Розпізнає текстові образи типу {123} або {ABC}
    public static boolean recognizeWithSwitchAutomaton(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        AutomatonState currentState = AutomatonState.START;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            switch (currentState) {
                case START:
                    if (ch == '{') {
                        currentState = AutomatonState.OPEN_BRACE;
                    } else {
                        currentState = AutomatonState.REJECT;
                    }
                    break;

                case OPEN_BRACE:
                    if (Character.isDigit(ch)) {
                        currentState = AutomatonState.DIGITS;
                    } else if (Character.isUpperCase(ch)) {
                        currentState = AutomatonState.LETTERS;
                    } else {
                        currentState = AutomatonState.REJECT;
                    }
                    break;

                case DIGITS:
                    if (Character.isDigit(ch)) {
                        // Залишаємося в стані DIGITS
                        currentState = AutomatonState.DIGITS;
                    } else if (ch == '}') {
                        currentState = AutomatonState.CLOSE_BRACE;
                    } else {
                        currentState = AutomatonState.REJECT;
                    }
                    break;

                case LETTERS:
                    if (Character.isUpperCase(ch)) {
                        // Залишаємося в стані LETTERS
                        currentState = AutomatonState.LETTERS;
                    } else if (ch == '}') {
                        currentState = AutomatonState.CLOSE_BRACE;
                    } else {
                        currentState = AutomatonState.REJECT;
                    }
                    break;

                case CLOSE_BRACE:
                    // Після закриваючої дужки не повинно бути інших символів
                    currentState = AutomatonState.REJECT;
                    break;

                case REJECT:
                    return false; // Одразу повертаємо false

                default:
                    currentState = AutomatonState.REJECT;
                    break;
            }
        }

        // Перевіряємо, чи закінчили в правильному стані
        return currentState == AutomatonState.CLOSE_BRACE;
    }

    // Завдання 3: Скінченний автомат на основі таблиці переходів

    // Стани скінченного автомата для завдання 3
    // Слова з роздільниками: починаються з '!', закінчуються '&'
    public enum Task3State {
        S0,     // Початковий стан
        S1,     // Після символу '!'
        S2,     // Читаємо вміст слова
        S3,     // Фінальний стан після '&'
        ERROR   // Стан помилки
    }

    // Таблиця переходів для скінченного автомата завдання 3
    // Ключ: поточний стан + символ → новий стан
    private static final Map<String, Task3State> TRANSITION_TABLE = new HashMap<>();

    static {
        // Заповнюємо таблицю переходів
        // З початкового стану S0
        TRANSITION_TABLE.put("S0_!", Task3State.S1);

        // З стану S1 (після '!')
        // Можемо читати будь-які символи крім '&' та '!'
        for (char c = 'A'; c <= 'Z'; c++) {
            TRANSITION_TABLE.put("S1_" + c, Task3State.S2);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            TRANSITION_TABLE.put("S1_" + c, Task3State.S2);
        }
        for (char c = '0'; c <= '9'; c++) {
            TRANSITION_TABLE.put("S1_" + c, Task3State.S2);
        }
        TRANSITION_TABLE.put("S1_ ", Task3State.S2); // пробіл

        // З стану S2 (читаємо вміст)
        for (char c = 'A'; c <= 'Z'; c++) {
            TRANSITION_TABLE.put("S2_" + c, Task3State.S2);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            TRANSITION_TABLE.put("S2_" + c, Task3State.S2);
        }
        for (char c = '0'; c <= '9'; c++) {
            TRANSITION_TABLE.put("S2_" + c, Task3State.S2);
        }
        TRANSITION_TABLE.put("S2_ ", Task3State.S2); // пробіл
        TRANSITION_TABLE.put("S2_&", Task3State.S3); // кінець слова

        // З стану S1 можемо одразу перейти до кінця
        TRANSITION_TABLE.put("S1_&", Task3State.S3);
    }

    // Синтаксичний аналізатор на основі таблиці переходів (for)
    // Розпізнає слова типу !content&
    public static boolean recognizeWithTableAutomaton(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        Task3State currentState = Task3State.S0;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            String transitionKey = currentState.name() + "_" + ch;

            Task3State nextState = TRANSITION_TABLE.get(transitionKey);
            if (nextState != null) {
                currentState = nextState;
            } else {
                currentState = Task3State.ERROR;
                break;
            }
        }

        return currentState == Task3State.S3;
    }

    // Розбиває текст на слова, що розділяються символами '!' та '&'
    public static List<String> extractWordsWithDelimiters(String text) {
        List<String> words = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            return words;
        }

        // Шукаємо всі підрядки виду !...&
        Pattern pattern = Pattern.compile("!([^!&]*)&");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            // Додаємо повний збіг разом з роздільниками
            words.add(matcher.group(0));
        }

        return words;
    }

    // Допоміжні методи

    // Створює візуальне представлення переходів автомата
    public static String getAutomatonDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ГРАФ СКІНЧЕННОГО АВТОМАТА ===\n");
        sb.append("Регулярний вираз: \\{(\\d+|[A-Z]+)\\}\n\n");
        sb.append("Стани:\n");
        sb.append("START -> { -> OPEN_BRACE\n");
        sb.append("OPEN_BRACE -> [0-9] -> DIGITS\n");
        sb.append("OPEN_BRACE -> [A-Z] -> LETTERS\n");
        sb.append("DIGITS -> [0-9] -> DIGITS\n");
        sb.append("DIGITS -> } -> CLOSE_BRACE (ACCEPT)\n");
        sb.append("LETTERS -> [A-Z] -> LETTERS\n");
        sb.append("LETTERS -> } -> CLOSE_BRACE (ACCEPT)\n");
        sb.append("* -> інший символ -> REJECT\n\n");

        sb.append("=== ТАБЛИЦЯ ПЕРЕХОДІВ (завдання 3) ===\n");
        sb.append("Слова з роздільниками !...&\n");
        sb.append("S0 --!--> S1\n");
        sb.append("S1 --[A-Za-z0-9 ]--> S2\n");
        sb.append("S1 --&--> S3 (ACCEPT)\n");
        sb.append("S2 --[A-Za-z0-9 ]--> S2\n");
        sb.append("S2 --&--> S3 (ACCEPT)\n");

        return sb.toString();
    }

    // Демонстрація роботи регулярних виразів
    public static void demonstrateRegexMatching(List<String> testWords) {
        System.out.println("=== ДЕМОНСТРАЦІЯ РЕГУЛЯРНИХ ВИРАЗІВ ===");
        System.out.println("Шаблон: слова виду {цифри} або {великі літери}");
        System.out.println();

        for (String word : testWords) {
            boolean matches = matchesTask1Pattern(word);
            System.out.printf("%-15s -> %s\n", word, matches ? "✓ ВІДПОВІДАЄ" : "✗ НЕ ВІДПОВІДАЄ");
        }
        System.out.println();
    }

    // Демонстрація роботи скінченного автомата
    public static void demonstrateAutomatonRecognition(List<String> testInputs) {
        System.out.println("=== ДЕМОНСТРАЦІЯ СКІНЧЕННОГО АВТОМАТА ===");
        System.out.println("Автомат на основі switch для розпізнавання {цифри} або {літери}");
        System.out.println();

        for (String input : testInputs) {
            boolean recognized = recognizeWithSwitchAutomaton(input);
            System.out.printf("%-15s -> %s\n", input, recognized ? "✓ РОЗПІЗНАНО" : "✗ ВІДХИЛЕНО");
        }
        System.out.println();
    }

    // Демонстрація роботи автомата з таблицею переходів
    public static void demonstrateTableAutomaton(List<String> testInputs) {
        System.out.println("=== ДЕМОНСТРАЦІЯ АВТОМАТА З ТАБЛИЦЕЮ ПЕРЕХОДІВ ===");
        System.out.println("Розпізнавання слів виду !content&");
        System.out.println();

        for (String input : testInputs) {
            boolean recognized = recognizeWithTableAutomaton(input);
            System.out.printf("%-20s -> %s\n", input, recognized ? "✓ РОЗПІЗНАНО" : "✗ ВІДХИЛЕНО");
        }
        System.out.println();
    }
}