package business.utils;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    // Метод для збереження списку об'єктів у файл
    public static <T> void saveToFile(String fileName, List<T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
            System.out.println("Data successfully saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }

    // Метод для завантаження списку об'єктів з файлу
    @SuppressWarnings("unchecked")
    public static <T> List<T> loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) ois.readObject(); // Читаємо список із файлу
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName + ". Returning empty list.");
            return new ArrayList<>(); // Повертаємо пустий список, якщо файл відсутній
        } catch (EOFException e) {
            System.out.println("File is empty: " + fileName + ". Returning an empty list.");
            return new ArrayList<>(); // Якщо файл порожній, повертаємо порожній список
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from file: " + e.getMessage());
            return new ArrayList<>(); // У разі помилки повертаємо пустий список
        }
    }
}
