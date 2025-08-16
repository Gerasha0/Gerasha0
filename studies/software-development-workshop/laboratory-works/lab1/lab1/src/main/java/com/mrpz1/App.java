package com.mrpz1;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Програма для обробки текстових даних
 * Завдання 7: видалити з тексту всі слова заданої довжини, 
 * які починаються на приголосну букву
 */
public class App 
{
    // Множина голосних букв (англійські та українські)
    private static final String VOWELS = "aeiouAEIOUаеєиіїоуюяАЕЄИІЇОУЮЯ";
    
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        
        // Довільний текст для обробки (використовуємо латинські літери для коректної роботи)
        String originalText = "This is a test text for demonstration of program work. " +
                             "Here are different words of different length: cat, dog, home, house, tree. " +
                             "Some words start with vowels and others with consonants. " +
                             "Program should remove words of given length that start with consonants.";
        
        System.out.println("=== TEXT DATA PROCESSING ===");
        System.out.println("\nOriginal text:");
        System.out.println(originalText);
        
        // Введення довжини слів для видалення
        System.out.print("\nEnter the length of words to remove: ");
        int targetLength;
        try {
            targetLength = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: enter a valid number!");
            scanner.close();
            return;
        }
        
        // Обробка тексту
        List<String> removedWords = new ArrayList<>();
        String processedText = processText(originalText, targetLength, removedWords);
        
        // Виводимо результат
        System.out.println("\nProcessed text (removed words of length " + targetLength + 
                          " that start with consonants):");
        System.out.println(processedText);
        
        // Виводимо видалені слова
        if (!removedWords.isEmpty()) {
            System.out.println("\nRemoved words:");
            for (String word : removedWords) {
                System.out.println("  - " + word);
            }
        } else {
            System.out.println("\nNo words were removed (no words of length " + targetLength + 
                             " starting with consonants found).");
        }
        
        scanner.close();
    }
    
    /**
     * Обробляє текст: видаляє слова заданої довжини, що починаються на приголосні букви
     * @param text оригінальний текст
     * @param targetLength задана довжина слів для видалення
     * @param removedWords список для збереження видалених слів
     * @return оброблений текст
     */
    private static String processText(String text, int targetLength, List<String> removedWords) {
        // Розділяємо текст на слова, зберігаючи розділові знаки
        String[] parts = text.split("(?<=\\W)|(?=\\W)");
        StringBuilder result = new StringBuilder();
        
        for (String part : parts) {
            // Перевіряємо, чи є частина словом (містить літери)
            if (part.matches(".*[a-zA-Zа-яА-Я].*")) {
                // Очищаємо слово від розділових знаків для перевірки
                String cleanWord = part.replaceAll("[^a-zA-Zа-яА-Я]", "");
                
                // Перевіряємо умови для видалення
                if (cleanWord.length() == targetLength && 
                    !isEmpty(cleanWord) && 
                    isConsonant(cleanWord.charAt(0))) {
                    
                    // Слово відповідає критеріям - додаємо до списку видалених
                    removedWords.add(cleanWord);
                    
                    // Але зберігаємо розділові знаки, якщо вони є
                    String punctuation = part.replaceAll("[a-zA-Zа-яА-Я]", "");
                    if (!punctuation.isEmpty()) {
                        result.append(punctuation);
                    }
                } else {
                    // Слово не відповідає критеріям - зберігаємо
                    result.append(part);
                }
            } else {
                // Не слово (розділові знаки, пробіли) - завжди зберігаємо
                result.append(part);
            }
        }
        
        // Очищаємо зайві пробіли
        return result.toString().replaceAll("\\s+", " ").trim();
    }
    
    /**
     * Перевіряє, чи є символ приголосним
     * @param ch символ для перевірки
     * @return true, якщо символ є приголосним
     */
    private static boolean isConsonant(char ch) {
        return Character.isLetter(ch) && VOWELS.indexOf(ch) == -1;
    }
    
    /**
     * Перевіряє, чи є рядок порожнім
     * @param str рядок для перевірки
     * @return true, якщо рядок порожній або null
     */
    private static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
