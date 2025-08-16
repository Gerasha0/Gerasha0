package org.atsd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Юніт-тести для додатку.
 */
public class AppTest {

    @Test
    public void testStudentCreation() {
        Student student = new Student("Іваненко", "Петро", 4.5, "Ч");

        assertEquals("Іваненко", student.getSurname());
        assertEquals("Петро", student.getName());
        assertEquals(4.5, student.getAverageGrade(), 0.01);
        assertEquals("Ч", student.getGender());
    }

    @Test
    public void testStudentComparison() {
        Student student1 = new Student("Петренко", "Анна", 4.2, "Ж");
        Student student2 = new Student("Сидоров", "Іван", 4.8, "Ч");
        Student student3 = new Student("Коваленко", "Марія", 4.2, "Ж");

        assertTrue(student1.compareByGrade(student2) < 0); // 4.2 < 4.8
        assertTrue(student2.compareByGrade(student1) > 0); // 4.8 > 4.2
        assertEquals(0, student1.compareByGrade(student3)); // 4.2 == 4.2
    }

    @Test
    public void testDoublyLinkedListBasicOperations() {
        DoublyLinkedList list = new DoublyLinkedList();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());

        Student student1 = new Student("Тестовий", "Студент", 4.0, "Ч");
        list.add(student1);

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }

    @Test
    public void testInsertionSortArray() {
        Student[] array = {
            new Student("Петренко", "Анна", 4.8, "Ж"),
            new Student("Сидоров", "Іван", 3.2, "Ч"),
            new Student("Коваленко", "Марія", 4.5, "Ж")
        };

        SortingAlgorithms.insertionSort(array);

        // Перевіряємо, що масив відсортовано за зростанням
        assertEquals(3.2, array[0].getAverageGrade(), 0.01);
        assertEquals(4.5, array[1].getAverageGrade(), 0.01);
        assertEquals(4.8, array[2].getAverageGrade(), 0.01);
    }

    @Test
    public void testBucketSort() {
        Student[] array = {
            new Student("Петренко", "Анна", 4.8, "Ж"),
            new Student("Сидоров", "Іван", 3.2, "Ч"),
            new Student("Коваленко", "Марія", 4.5, "Ж"),
            new Student("Мельник", "Олег", 3.8, "Ч")
        };

        Student[] originalArray = SortingAlgorithms.copyArray(array);
        SortingAlgorithms.bucketSort(array);

        // Перевіряємо, що масив відсортовано
        for (int i = 1; i < array.length; i++) {
            assertTrue(array[i-1].getAverageGrade() <= array[i].getAverageGrade());
        }

        // Порівнюємо з сортуванням вставкою
        SortingAlgorithms.insertionSort(originalArray);
        for (int i = 0; i < array.length; i++) {
            assertEquals(originalArray[i].getAverageGrade(), array[i].getAverageGrade(), 0.01);
        }
    }

    @Test
    public void testDoublyLinkedListInsertionSort() {
        DoublyLinkedList list = new DoublyLinkedList();

        list.add(new Student("Петренко", "Анна", 4.8, "Ж"));
        list.add(new Student("Сидоров", "Іван", 3.2, "Ч"));
        list.add(new Student("Коваленко", "Марія", 4.5, "Ж"));

        assertEquals(3, list.size());

        list.insertionSort();

        // Перетворюємо у масив для перевірки
        Student[] sortedArray = list.toArray();

        assertEquals(3.2, sortedArray[0].getAverageGrade(), 0.01);
        assertEquals(4.5, sortedArray[1].getAverageGrade(), 0.01);
        assertEquals(4.8, sortedArray[2].getAverageGrade(), 0.01);
    }

    @Test
    public void testSortingEmptyAndSingleElement() {
        // Тест по��ожнього масиву
        Student[] emptyArray = new Student[0];
        SortingAlgorithms.insertionSort(emptyArray);
        SortingAlgorithms.bucketSort(emptyArray);
        assertEquals(0, emptyArray.length);

        // Тест масиву з одним елементом
        Student[] singleArray = {new Student("Тест", "Студент", 4.0, "Ч")};
        SortingAlgorithms.insertionSort(singleArray);
        assertEquals(4.0, singleArray[0].getAverageGrade(), 0.01);

        SortingAlgorithms.bucketSort(singleArray);
        assertEquals(4.0, singleArray[0].getAverageGrade(), 0.01);
    }

    @Test
    public void testArrayCopy() {
        Student[] original = SortingAlgorithms.createTestArray();
        Student[] copy = SortingAlgorithms.copyArray(original);

        assertEquals(original.length, copy.length);

        for (int i = 0; i < original.length; i++) {
            assertEquals(original[i].getSurname(), copy[i].getSurname());
            assertEquals(original[i].getName(), copy[i].getName());
            assertEquals(original[i].getAverageGrade(), copy[i].getAverageGrade(), 0.01);
            assertEquals(original[i].getGender(), copy[i].getGender());
        }

        // Перевіряємо, що це різні об'єкти
        assertNotSame(original[0], copy[0]);
    }

    @Test
    public void testDoublyLinkedNode() {
        Student student = new Student("Тест", "Студент", 4.0, "Ч");
        DoublyLinkedNode node = new DoublyLinkedNode(student);

        assertEquals(student, node.getData());
        assertNull(node.getNext());
        assertNull(node.getPrev());

        DoublyLinkedNode nextNode = new DoublyLinkedNode(new Student("Наступний", "Студент", 3.5, "Ж"));
        node.setNext(nextNode);
        nextNode.setPrev(node);

        assertEquals(nextNode, node.getNext());
        assertEquals(node, nextNode.getPrev());
    }
}
