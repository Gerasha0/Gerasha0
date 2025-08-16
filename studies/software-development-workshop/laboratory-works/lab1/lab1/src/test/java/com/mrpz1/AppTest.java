package com.mrpz1;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit тести для App
 */
public class AppTest 
{
    /**
     * Тест для перевірки функції isConsonant через рефлексію
     */
    @Test
    public void testIsConsonant() throws Exception
    {
        Method isConsonantMethod = App.class.getDeclaredMethod("isConsonant", char.class);
        isConsonantMethod.setAccessible(true);
        
        // Тестуємо приголосні
        assertTrue("B повинно бути приголосним", (Boolean) isConsonantMethod.invoke(null, 'B'));
        assertTrue("D повинно бути приголосним", (Boolean) isConsonantMethod.invoke(null, 'D'));
        assertTrue("C повинно бути приголосним", (Boolean) isConsonantMethod.invoke(null, 'C'));
        
        // Тестуємо голосні
        assertTrue("A повинно бути голосним", !((Boolean) isConsonantMethod.invoke(null, 'A')));
        assertTrue("U повинно бути голосним", !((Boolean) isConsonantMethod.invoke(null, 'U')));
        assertTrue("O повинно бути голосним", !((Boolean) isConsonantMethod.invoke(null, 'O')));
    }
    
    /**
     * Тест для перевірки функції processText через рефлексію
     */
    @Test
    public void testProcessText() throws Exception
    {
        Method processTextMethod = App.class.getDeclaredMethod("processText", String.class, int.class, List.class);
        processTextMethod.setAccessible(true);
        
        String testText = "This cat and dog are near the home.";
        List<String> removedWords = new ArrayList<>();
        
        // Видаляємо слова довжиною 3 символи, що починаються на приголосні (cat, dog)
        String result = (String) processTextMethod.invoke(null, testText, 3, removedWords);
        assertTrue("Слово 'cat' повинно бути видалено", !result.contains("cat"));
        assertTrue("Слово 'dog' повинно бути видалено", !result.contains("dog"));
        assertTrue("Слово 'and' повинно залишитися (починається на голосну)", result.contains("and"));
        assertTrue("Слово 'are' повинно залишитися (починається на голосну)", result.contains("are"));
        
        // Перевіряємо що видалені слова збережено
        assertTrue("У списку видалених слів повинно бути 2 елементи", removedWords.size() == 2);
        assertTrue("Список повинен містити 'cat'", removedWords.contains("cat"));
        assertTrue("Список повинен містити 'dog'", removedWords.contains("dog"));
    }
    
    /**
     * Базовий тест
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
