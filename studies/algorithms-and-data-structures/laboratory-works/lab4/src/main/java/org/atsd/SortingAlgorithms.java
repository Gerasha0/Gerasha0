package org.atsd;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас з алгоритмами сортування для масивів студентів
 */
public class SortingAlgorithms {

    /**
     * Сортування вставкою за середнім балом (зростання)
     */
    public static void insertionSort(Student[] array) {
        for (int i = 1; i < array.length; i++) {
            Student key = array[i];
            int j = i - 1;

            // Зсуваємо елементи, які більші за ключ, на одну позицію вправо
            while (j >= 0 && array[j].getAverageGrade() > key.getAverageGrade()) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }

    /**
     * Кишеньковий алгоритм сортування (Bucket Sort) за середнім балом
     * Використовує сортування вставкою для кожної кишені
     */
    public static void bucketSort(Student[] array) {
        if (array.length <= 1) {
            return;
        }

        // Знаходимо мінімальний та максимальний бали
        double minGrade = array[0].getAverageGrade();
        double maxGrade = array[0].getAverageGrade();

        for (Student student : array) {
            if (student.getAverageGrade() < minGrade) {
                minGrade = student.getAverageGrade();
            }
            if (student.getAverageGrade() > maxGrade) {
                maxGrade = student.getAverageGrade();
            }
        }

        // Створюємо кишені (відра) - по одній на кожен можливий бал
        int bucketCount = 10; // Для балів від 0 до 5 з кроком 0.5
        List<List<Student>> buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // Розподіляємо студентів по кишенях
        double range = maxGrade - minGrade;
        if (range == 0) {
            // Всі студенти мають однаковий бал
            return;
        }

        for (Student student : array) {
            int bucketIndex = (int) ((student.getAverageGrade() - minGrade) / range * (bucketCount - 1));
            // Обмежуємо індекс в межах масиву
            if (bucketIndex >= bucketCount) {
                bucketIndex = bucketCount - 1;
            }
            buckets.get(bucketIndex).add(student);
        }

        // Сортуємо кожну кишеню за допомогою сортування вставкою
        for (List<Student> bucket : buckets) {
            if (!bucket.isEmpty()) {
                Student[] bucketArray = bucket.toArray(new Student[0]);
                insertionSort(bucketArray);
                bucket.clear();
                for (Student student : bucketArray) {
                    bucket.add(student);
                }
            }
        }

        // Збираємо відсортовані елементи назад в масив
        int index = 0;
        for (List<Student> bucket : buckets) {
            for (Student student : bucket) {
                array[index++] = student;
            }
        }
    }

    /**
     * Виводить масив студентів у табличному вигляді
     */
    public static void displayArray(Student[] array, String title) {
        System.out.println("\n" + title);
        System.out.println(Student.getTableHeader());
        System.out.println(Student.getTableSeparator());

        if (array.length == 0) {
            System.out.println("Масив порожній");
            return;
        }

        for (Student student : array) {
            System.out.println(student);
        }
        System.out.println();
    }

    /**
     * Створює масив тестових студентів
     */
    public static Student[] createTestArray() {
        return new Student[] {
            new Student("Петренко", "Олександр", 4.2, "Ч"),
            new Student("Іванова", "Марія", 4.8, "Ж"),
            new Student("Сидоров", "Петро", 3.5, "Ч"),
            new Student("Коваленко", "Анна", 4.9, "Ж"),
            new Student("Мельник", "Іван", 3.2, "Ч"),
            new Student("Шевченко", "Оксана", 4.5, "Ж"),
            new Student("Кравченко", "Андрій", 3.8, "Ч"),
            new Student("Лисенко", "Катерина", 4.7, "Ж"),
            new Student("Бондаренко", "Микола", 4.1, "Ч"),
            new Student("Ткаченко", "Віра", 4.6, "Ж")
        };
    }

    /**
     * Створює копію масиву
     */
    public static Student[] copyArray(Student[] original) {
        Student[] copy = new Student[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = new Student(original[i].getSurname(), original[i].getName(),
                                original[i].getAverageGrade(), original[i].getGender());
        }
        return copy;
    }
}
