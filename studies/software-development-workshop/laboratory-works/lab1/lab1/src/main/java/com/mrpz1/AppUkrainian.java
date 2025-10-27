package com.mrpz1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Українська версія програми для обробки текстових даних
 * Завдання 7: видалити з тексту всі слова заданої довжини, 
 * які починаються на приголосну букву
 */
public class AppUkrainian 
{
    // Множина голосних букв українського алфавіту
    private static final String VOWELS_UKRAINIAN = "аеєиіїоуюяАЕЄИІЇОУЮЯ";
    
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        
        // Довільний текст українською мовою для обробки
        String originalText = "Це тестовий текст для демонстрації роботи програми. " +
                             "Тут є різні слова різної довжини як кіт собака дім будинок дерево. " +
                             "Деякі слова починаються на голосні а інші на приголосні букви. " +
                             "Програма має видалити слова заданої довжини що починаються на приголосні.";
        
        System.out.println("=== ОБРОБКА ТЕКСТОВИХ ДАНИХ (українською) ===");
        System.out.println("\nВихідний текст:");
        System.out.println(originalText);
        
        // Введення довжини слів для видалення
        System.out.print("\nВведіть довжину слів які потрібно видалити: ");
        int targetLength;
        try {
            targetLength = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Помилка: введіть коректне число!");
            scanner.close();
            return;
        }
        
        // Обробка тексту
        List<String> removedWords = new ArrayList<>();
        String processedText = processTextUkrainian(originalText, targetLength, removedWords);
        
        System.out.println("\nОброблений текст (видалено слова довжиною " + targetLength + 
                          " символів що починаються на приголосні):");
        System.out.println(processedText);
        
        // Виводимо видалені слова
        if (!removedWords.isEmpty()) {
            System.out.println("\nВидалені слова:");
            for (String word : removedWords) {
                System.out.println("  - " + word);
            }
        } else {
            System.out.println("\nЖодного слова не було видалено (не знайдено слів довжиною " + targetLength + 
                             ", що починаються на приголосні).");
        }
        
        scanner.close();
    }
    
    /**
     * Обробляє український текст: видаляє слова заданої довжини, що починаються на приголосні букви
     * @param text оригінальний текст
     * @param targetLength задана довжина слів для видалення
     * @param removedWords список для збереження видалених слів
     * @return оброблений текст
     */
    private static String processTextUkrainian(String text, int targetLength, List<String> removedWords) {
        // Розділяємо текст на слова зберігаючи розділові знаки
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            
            // Очищаємо слово від розділових знаків для перевірки
            String cleanWord = word.replaceAll("[^а-яА-ЯёЁ]", "");
            
            // Перевіряємо умови для видалення
            if (cleanWord.length() == targetLength && 
                !cleanWord.isEmpty() && 
                isConsonantUkrainian(cleanWord.charAt(0))) {
                
                // Слово відповідає критеріям - додаємо до списку видалених
                removedWords.add(cleanWord);
                continue;
            }
            
            // Додаємо слово до результату
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(word);
        }
        
        return result.toString();
    }
    
    /**
     * Перевіряє чи є символ приголосним в українській мові
     * @param ch символ для перевірки
     * @return true якщо символ є приголосним
     */
    private static boolean isConsonantUkrainian(char ch) {
        return Character.isLetter(ch) && VOWELS_UKRAINIAN.indexOf(ch) == -1;
    }
}
