package org.atsd;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас з алгоритмами пошуку для масивів студентів
 */
public class SearchAlgorithms {

    /**
     * Послідовний пошук студенток з балом вище 4.5 у заданій групі
     * @param array масив студентів
     * @param targetGroup група для пошуку
     * @return кількість знайдених студенток (від'ємне значення)
     */
    public static int sequentialSearchExcellentFemales(Student[] array, String targetGroup) {
        int count = 0;

        for (Student student : array) {
            if (student != null && targetGroup.equals(student.getGroup()) &&
                student.isExcellentFemaleStudent()) {
                count++;
            }
        }

        return -count; // Повертаємо від'ємне значення згідно з методичними рекомендаціями
    }

    /**
     * Отримати список студенток з балом вище 4.5 у заданій групі
     */
    public static List<Student> findExcellentFemalesInGroup(Student[] array, String targetGroup) {
        List<Student> result = new ArrayList<>();

        for (Student student : array) {
            if (student != null && targetGroup.equals(student.getGroup()) &&
                student.isExcellentFemaleStudent()) {
                result.add(student);
            }
        }

        return result;
    }

    /**
     * Сортування масиву за номером групи (для упорядкованого формування)
     */
    public static void sortByGroup(Student[] array) {
        Arrays.sort(array, (s1, s2) -> {
            if (s1 == null && s2 == null) return 0;
            if (s1 == null) return 1;
            if (s2 == null) return -1;
            return s1.compareByGroup(s2);
        });
    }

    /**
     * Вставка студента в упорядкований за групою масив
     */
    public static boolean insertInOrderedArray(Student[] array, Student student, int size) {
        if (size >= array.length) {
            return false; // Масив заповнений
        }

        // Знаходимо позицію для вставки
        int insertPos = 0;
        while (insertPos < size && array[insertPos].compareByGroup(student) <= 0) {
            insertPos++;
        }

        // Зсуваємо елементи вправо
        for (int i = size; i > insertPos; i--) {
            array[i] = array[i - 1];
        }

        // Вставляємо студента
        array[insertPos] = student;

        return true;
    }

    /**
     * Виводить масив студентів у табличному вигляді
     */
    public static void displayArray(Student[] array, String title) {
        System.out.println("\n" + title);
        System.out.println(Student.getTableHeader());
        System.out.println(Student.getTableSeparator());

        boolean hasElements = false;
        for (Student student : array) {
            if (student != null) {
                System.out.println(student);
                hasElements = true;
            }
        }

        if (!hasElements) {
            System.out.println("Масив порожній");
        }
        System.out.println();
    }

    /**
     * Створює тестовий масив студентів (упорядкований за групою)
     */
    public static Student[] createTestArray() {
        Student[] array = new Student[25]; // Більше 20 елементів згідно з вимогами
        int index = 0;

        // Додаємо студентів по групах (упорядкований за номером групи)
        // Група ІТ-21
        array[index++] = new Student("Іваненко", "Олексій", "ІТ-21", "Ч", 4.2);
        array[index++] = new Student("Петрова", "Марія", "ІТ-21", "Ж", 4.8);
        array[index++] = new Student("Сидоров", "Петро", "ІТ-21", "Ч", 3.5);
        array[index++] = new Student("Коваленко", "Анна", "ІТ-21", "Ж", 4.9);
        array[index++] = new Student("Мельник", "Іван", "ІТ-21", "Ч", 3.8);

        // Група ІТ-22
        array[index++] = new Student("Шевченко", "Оксана", "ІТ-22", "Ж", 4.6);
        array[index++] = new Student("Кравченко", "Андрій", "ІТ-22", "Ч", 4.1);
        array[index++] = new Student("Лисенко", "Катерина", "ІТ-22", "Ж", 4.7);
        array[index++] = new Student("Бондаренко", "Микола", "ІТ-22", "Ч", 3.9);
        array[index++] = new Student("Ткаченко", "Віра", "ІТ-22", "Ж", 4.4);

        // Група КБ-21
        array[index++] = new Student("Морозов", "Дмитро", "КБ-21", "Ч", 4.3);
        array[index++] = new Student("Савченко", "Олена", "КБ-21", "Ж", 4.9);
        array[index++] = new Student("Гриценко", "Василь", "КБ-21", "Ч", 3.7);
        array[index++] = new Student("Романенко", "Тетяна", "КБ-21", "Ж", 4.8);
        array[index++] = new Student("Левченко", "Сергій", "КБ-21", "Ч", 4.0);

        // Група КБ-22
        array[index++] = new Student("Павленко", "Наталя", "КБ-22", "Ж", 4.5);
        array[index++] = new Student("Кузьменко", "Олег", "КБ-22", "Ч", 3.6);
        array[index++] = new Student("Дорошенко", "Ірина", "КБ-22", "Ж", 4.7);
        array[index++] = new Student("Васильєв", "Артем", "КБ-22", "Ч", 4.2);
        array[index++] = new Student("Никоненко", "Світлана", "КБ-22", "Ж", 4.6);

        // Група ПІ-21
        array[index++] = new Student("Захарченко", "Роман", "ПІ-21", "Ч", 3.9);
        array[index++] = new Student("Мартиненко", "Юлія", "ПІ-21", "Ж", 4.8);
        array[index++] = new Student("Григоренко", "Максим", "ПІ-21", "Ч", 4.1);
        array[index++] = new Student("Семененко", "Аліна", "ПІ-21", "Ж", 4.9);
        array[index++] = new Student("Козлов", "Ігор", "ПІ-21", "Ч", 3.8);

        return array;
    }

    /**
     * Підраховує кількість студентів у масиві (не null елементів)
     */
    public static int countStudents(Student[] array) {
        int count = 0;
        for (Student student : array) {
            if (student != null) {
                count++;
            }
        }
        return count;
    }
}
