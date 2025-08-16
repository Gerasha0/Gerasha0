package org.atsd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import java.util.Arrays;

/**
 * Юніт-тести для алгоритмів ідентифікації
 * Лабораторна робота 2.2 - Варіант 7
 */
public class AppTest {

    // =============== ТЕСТИ ДЛ�� ЗАВДАННЯ 1: РЕГУЛЯРНІ ВИРАЗИ ===============

    @Test
    @DisplayName("Тест регулярного виразу для правильних слів з цифрами")
    public void testRegexWithValidDigitWords() {
        assertTrue(TextPatternRecognizer.matchesTask1Pattern("{123}"));
        assertTrue(TextPatternRecognizer.matchesTask1Pattern("{0}"));
        assertTrue(TextPatternRecognizer.matchesTask1Pattern("{999}"));
        assertTrue(TextPatternRecognizer.matchesTask1Pattern("{123456789}"));
    }

    @Test
    @DisplayName("Тест регулярного виразу для правильних слів з великими літерами")
    public void testRegexWithValidLetterWords() {
        assertTrue(TextPatternRecognizer.matchesTask1Pattern("{ABC}"));
        assertTrue(TextPatternRecognizer.matchesTask1Pattern("{A}"));
        assertTrue(TextPatternRecognizer.matchesTask1Pattern("{XYZ}"));
        assertTrue(TextPatternRecognizer.matchesTask1Pattern("{HELLO}"));
        assertTrue(TextPatternRecognizer.matchesTask1Pattern("{WORLD}"));
    }

    @Test
    @DisplayName("Тест регулярного виразу для неправильних слів")
    public void testRegexWithInvalidWords() {
        // Змішані символи
        assertFalse(TextPatternRecognizer.matchesTask1Pattern("{12A}"));
        assertFalse(TextPatternRecognizer.matchesTask1Pattern("{A1B}"));
        assertFalse(TextPatternRecognizer.matchesTask1Pattern("{123ABC}"));

        // Малі літери
        assertFalse(TextPatternRecognizer.matchesTask1Pattern("{abc}"));
        assertFalse(TextPatternRecognizer.matchesTask1Pattern("{hello}"));

        // Без дужок
        assertFalse(TextPatternRecognizer.matchesTask1Pattern("123"));
        assertFalse(TextPatternRecognizer.matchesTask1Pattern("ABC"));

        // Неповні дужки
        assertFalse(TextPatternRecognizer.matchesTask1Pattern("{123"));
        assertFalse(TextPatternRecognizer.matchesTask1Pattern("123}"));

        // Порожні дужки
        assertFalse(TextPatternRecognizer.matchesTask1Pattern("{}"));

        // Зайві символи
        assertFalse(TextPatternRecognizer.matchesTask1Pattern("{123} "));
        assertFalse(TextPatternRecognizer.matchesTask1Pattern(" {123}"));
    }

    @Test
    @DisplayName("Тест пошуку відповідних слів у списку")
    public void testFindMatchingWords() {
        List<String> testWords = Arrays.asList(
            "{123}", "{ABC}", "{12A}", "{abc}", "123", "{}", "{HELLO}", "{999}", "ABC}"
        );

        List<String> matchingWords = TextPatternRecognizer.findMatchingWords(testWords);

        assertEquals(4, matchingWords.size());
        assertTrue(matchingWords.contains("{123}"));
        assertTrue(matchingWords.contains("{ABC}"));
        assertTrue(matchingWords.contains("{HELLO}"));
        assertTrue(matchingWords.contains("{999}"));

        assertFalse(matchingWords.contains("{12A}"));
        assertFalse(matchingWords.contains("{abc}"));
        assertFalse(matchingWords.contains("123"));
    }

    @Test
    @DisplayName("Тест з null та порожніми рядками")
    public void testRegexWithNullAndEmpty() {
        assertFalse(TextPatternRecognizer.matchesTask1Pattern(null));
        assertFalse(TextPatternRecognizer.matchesTask1Pattern(""));
        assertFalse(TextPatternRecognizer.matchesTask1Pattern("   "));
    }

    // =============== ТЕСТИ ДЛЯ ЗАВДАННЯ 2: СКІНЧЕННИЙ АВТОМАТ (SWITCH) ===============

    @Test
    @DisplayName("Тест автомата switch для правильних входів з цифрами")
    public void testSwitchAutomatonWithValidDigits() {
        assertTrue(TextPatternRecognizer.recognizeWithSwitchAutomaton("{123}"));
        assertTrue(TextPatternRecognizer.recognizeWithSwitchAutomaton("{0}"));
        assertTrue(TextPatternRecognizer.recognizeWithSwitchAutomaton("{999}"));
        assertTrue(TextPatternRecognizer.recognizeWithSwitchAutomaton("{123456789}"));
    }

    @Test
    @DisplayName("Тест автомата switch для правильних входів з літерами")
    public void testSwitchAutomatonWithValidLetters() {
        assertTrue(TextPatternRecognizer.recognizeWithSwitchAutomaton("{ABC}"));
        assertTrue(TextPatternRecognizer.recognizeWithSwitchAutomaton("{A}"));
        assertTrue(TextPatternRecognizer.recognizeWithSwitchAutomaton("{XYZ}"));
        assertTrue(TextPatternRecognizer.recognizeWithSwitchAutomaton("{HELLO}"));
    }

    @Test
    @DisplayName("Тест автомата switch для неправильних входів")
    public void testSwitchAutomatonWithInvalidInputs() {
        // Змішані символи
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton("{12A}"));
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton("{A1B}"));
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton("{123ABC}"));

        // Малі літери
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton("{abc}"));
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton("{hello}"));

        // Неповні дужки
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton("{123"));
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton("123}"));

        // Порожні дужки
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton("{}"));

        // Зайві символи
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton("{123}ABC"));
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton("ABC{123}"));
    }

    @Test
    @DisplayName("Тест автомата switch з граничними випадками")
    public void testSwitchAutomatonBoundaryConditions() {
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton(null));
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton(""));
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton("{"));
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton("}"));
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton("{ }"));
        assertFalse(TextPatternRecognizer.recognizeWithSwitchAutomaton("{-123}"));
    }

    // =============== ТЕСТИ ДЛЯ ЗАВДАННЯ 3: АВТОМАТ З ТАБЛИЦЕЮ ПЕРЕХОДІВ ===============

    @Test
    @DisplayName("Тест автомата з таблицею переходів для правильних входів")
    public void testTableAutomatonWithValidInputs() {
        assertTrue(TextPatternRecognizer.recognizeWithTableAutomaton("!Hello&"));
        assertTrue(TextPatternRecognizer.recognizeWithTableAutomaton("!123&"));
        assertTrue(TextPatternRecognizer.recognizeWithTableAutomaton("!ABC&"));
        assertTrue(TextPatternRecognizer.recognizeWithTableAutomaton("!Hello World&"));
        assertTrue(TextPatternRecognizer.recognizeWithTableAutomaton("!Test123&"));
        assertTrue(TextPatternRecognizer.recognizeWithTableAutomaton("!&")); // Порожнє слово
    }

    @Test
    @DisplayName("Тест автомата �� таблицею переходів для неправильних входів")
    public void testTableAutomatonWithInvalidInputs() {
        // Без початкового роздільника
        assertFalse(TextPatternRecognizer.recognizeWithTableAutomaton("Hello&"));
        assertFalse(TextPatternRecognizer.recognizeWithTableAutomaton("123&"));

        // Без кінцевого роздільника
        assertFalse(TextPatternRecognizer.recognizeWithTableAutomaton("!Hello"));
        assertFalse(TextPatternRecognizer.recognizeWithTableAutomaton("!123"));

        // Без роздільників взагалі
        assertFalse(TextPatternRecognizer.recognizeWithTableAutomaton("Hello"));
        assertFalse(TextPatternRecognizer.recognizeWithTableAutomaton("123"));

        // Зайві символи після кінця
        assertFalse(TextPatternRecognizer.recognizeWithTableAutomaton("!Hello&World"));
        assertFalse(TextPatternRecognizer.recognizeWithTableAutomaton("!123&456"));

        // Внутрішні роздільники
        assertFalse(TextPatternRecognizer.recognizeWithTableAutomaton("!Hello!World&"));
    }

    @Test
    @DisplayName("Тест автомата з таблицею переходів з граничними випадками")
    public void testTableAutomatonBoundaryConditions() {
        assertFalse(TextPatternRecognizer.recognizeWithTableAutomaton(null));
        assertFalse(TextPatternRecognizer.recognizeWithTableAutomaton(""));
        assertFalse(TextPatternRecognizer.recognizeWithTableAutomaton("!"));
        assertFalse(TextPatternRecognizer.recognizeWithTableAutomaton("&"));
        assertFalse(TextPatternRecognizer.recognizeWithTableAutomaton("!&!"));
        assertFalse(TextPatternRecognizer.recognizeWithTableAutomaton("&!"));
    }

    @Test
    @DisplayName("Тест витягування слів з роздільниками")
    public void testExtractWordsWithDelimiters() {
        String text = "Це приклад тексту з !словами& різного типу. Тут є !правильні слова& та !123& і навіть !Hello World&.";

        List<String> extractedWords = TextPatternRecognizer.extractWordsWithDelimiters(text);

        assertEquals(4, extractedWords.size());
        assertTrue(extractedWords.contains("!словами&"));
        assertTrue(extractedWords.contains("!правильні слова&"));
        assertTrue(extractedWords.contains("!123&"));
        assertTrue(extractedWords.contains("!Hello World&"));
    }

    @Test
    @DisplayName("Тест витягування слів з порожнього тексту")
    public void testExtractWordsFromEmptyText() {
        assertTrue(TextPatternRecognizer.extractWordsWithDelimiters("").isEmpty());
        assertTrue(TextPatternRecognizer.extractWordsWithDelimiters(null).isEmpty());
        assertTrue(TextPatternRecognizer.extractWordsWithDelimiters("Текст без роздільників").isEmpty());
    }

    // =============== ІНТЕГРАЦІЙНІ ТЕСТИ ===============

    @Test
    @DisplayName("Інтеграційний тест: порівняння regex та switch автомата")
    public void testRegexAndSwitchAutomatonConsistency() {
        List<String> testCases = Arrays.asList(
            "{123}", "{ABC}", "{999}", "{HELLO}",
            "{12A}", "{abc}", "{}", "123}", "{123", "ABC"
        );

        for (String testCase : testCases) {
            boolean regexResult = TextPatternRecognizer.matchesTask1Pattern(testCase);
            boolean automatonResult = TextPatternRecognizer.recognizeWithSwitchAutomaton(testCase);

            assertEquals(regexResult, automatonResult,
                "Регулярний вираз та автомат повинні давати однакові результати для: " + testCase);
        }
    }

    @Test
    @DisplayName("Тест продуктивності regex")
    public void testRegexPerformance() {
        String testWord = "{ABCDEFGHIJKLMNOPQRSTUVWXYZ}";

        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            TextPatternRecognizer.matchesTask1Pattern(testWord);
        }
        long endTime = System.nanoTime();

        long duration = endTime - startTime;

        // Тест повинен виконуватися за розумний час (менше 100мс)
        assertTrue(duration < 100_000_000L,
            "Регулярний вираз повинен працювати швидко");
    }

    @Test
    @DisplayName("Тест продуктивності автомата switch")
    public void testSwitchAutomatonPerformance() {
        String testInput = "{ABCDEFGHIJKLMNOPQRSTUVWXYZ}";

        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            TextPatternRecognizer.recognizeWithSwitchAutomaton(testInput);
        }
        long endTime = System.nanoTime();

        long duration = endTime - startTime;

        // Тест повинен виконуватися за розумний час (менше 50мс)
        assertTrue(duration < 50_000_000L,
            "Скінченний автомат повинен працювати швидко");
    }

    @Test
    @DisplayName("Тест продуктивності автомата з таблицею переходів")
    public void testTableAutomatonPerformance() {
        String testInput = "!ABCDEFGHIJKLMNOPQRSTUVWXYZ&";

        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            TextPatternRecognizer.recognizeWithTableAutomaton(testInput);
        }
        long endTime = System.nanoTime();

        long duration = endTime - startTime;

        // Тест повинен виконуватися за розумний час (менше 100мс)
        assertTrue(duration < 100_000_000L,
            "Автомат з таблицею переходів повинен працювати швидко");
    }

    // =============== ТЕСТИ СТАНІВ АВТОМАТА ===============

    @Test
    @DisplayName("Тест перелічуваного типу AutomatonState")
    public void testAutomatonStateEnum() {
        // Перевіряємо, що всі необхідні стани присутні
        TextPatternRecognizer.AutomatonState[] states = TextPatternRecognizer.AutomatonState.values();

        assertTrue(states.length >= 6, "Повинно бути принаймні 6 станів");

        // Перевіряємо наявність основних станів
        boolean hasStart = false, hasAccept = false, hasReject = false;
        for (TextPatternRecognizer.AutomatonState state : states) {
            if (state.name().contains("START")) hasStart = true;
            if (state.name().contains("ACCEPT") || state.name().contains("CLOSE_BRACE")) hasAccept = true;
            if (state.name().contains("REJECT")) hasReject = true;
        }

        assertTrue(hasStart, "Повинен бути початковий стан");
        assertTrue(hasAccept, "Повинен бути стан прийняття");
        assertTrue(hasReject, "Повинен бути стан відхилення");
    }

    @Test
    @DisplayName("Тест перелічуваного типу Task3State")
    public void testTask3StateEnum() {
        // Перевіряємо, що всі необхідні стани присутні
        TextPatternRecognizer.Task3State[] states = TextPatternRecognizer.Task3State.values();

        assertTrue(states.length >= 4, "Повинно бути принаймні 4 стани для завдання 3");

        // Перевіряємо наявність основних станів
        boolean hasS0 = false, hasError = false;
        for (TextPatternRecognizer.Task3State state : states) {
            if (state.name().equals("S0")) hasS0 = true;
            if (state.name().equals("ERROR")) hasError = true;
        }

        assertTrue(hasS0, "Повинен бути початковий стан S0");
        assertTrue(hasError, "Повинен бути стан ERROR");
    }

    // =============== ТЕСТИ ДОПОМІЖНИХ МЕТОДІВ ===============

    @Test
    @DisplayName("Тест опису автомата")
    public void testAutomatonDescription() {
        String description = TextPatternRecognizer.getAutomatonDescription();

        assertNotNull(description);
        assertFalse(description.isEmpty());

        // Перевіряємо наявність ключових елементів опису
        assertTrue(description.contains("ГРАФ СКІНЧЕННОГО АВТОМАТА"));
        assertTrue(description.contains("ТАБЛИЦЯ ПЕРЕХОДІВ"));
        assertTrue(description.contains("START"));
        assertTrue(description.contains("ACCEPT"));
    }

    @Test
    @DisplayName("Комплексний тест всіх компонентів")
    public void testComplexScenario() {
        // Створюємо комплексний тестовий сценарій
        List<String> mixedWords = Arrays.asList(
            "{123}", "{ABC}", "{12A}", "!Hello&", "!123&", "invalid"
        );

        // Тестуємо regex
        List<String> regexMatches = TextPatternRecognizer.findMatchingWords(mixedWords);
        assertEquals(2, regexMatches.size()); // {123} та {ABC}

        // Тестуємо switch автомат
        int switchMatches = 0;
        for (String word : mixedWords) {
            if (TextPatternRecognizer.recognizeWithSwitchAutomaton(word)) {
                switchMatches++;
            }
        }
        assertEquals(2, switchMatches); // {123} та {ABC}

        // Тестуємо table автомат
        int tableMatches = 0;
        for (String word : mixedWords) {
            if (TextPatternRecognizer.recognizeWithTableAutomaton(word)) {
                tableMatches++;
            }
        }
        assertEquals(2, tableMatches); // !Hello& та !123&
    }
}
