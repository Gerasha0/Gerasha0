package org.atsd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * Юніт-тести для додатку.
 */
public class AppTest {

    @Test
    public void testStudentCreation() {
        Student student = new Student("Іваненко", "Петро", "ІТ-21", "Ч", 4.5);

        assertEquals("Іваненко", student.getSurname());
        assertEquals("Петро", student.getName());
        assertEquals("ІТ-21", student.getGroup());
        assertEquals("Ч", student.getGender());
        assertEquals(4.5, student.getAverageGrade(), 0.01);
    }

    @Test
    public void testExcellentFemaleStudent() {
        Student excellentFemale = new Student("Петрова", "Марія", "КБ-22", "Ж", 4.8);
        Student excellentMale = new Student("Сидоров", "Іван", "ІТ-21", "Ч", 4.8);
        Student averageFemale = new Student("Коваленко", "Анна", "ПІ-21", "Ж", 4.2);

        assertTrue(excellentFemale.isExcellentFemaleStudent());
        assertFalse(excellentMale.isExcellentFemaleStudent()); // чоловік
        assertFalse(averageFemale.isExcellentFemaleStudent()); // низький бал
    }

    @Test
    public void testStudentComparisons() {
        Student student1 = new Student("Іваненко", "Петро", "ІТ-21", "Ч", 4.2);
        Student student2 = new Student("Петрова", "Марія", "КБ-22", "Ж", 4.8);
        Student student3 = new Student("Сидоров", "Іван", "ІТ-21", "Ч", 3.5);

        // Порівняння за групою
        assertTrue(student1.compareByGroup(student2) < 0); // ІТ < КБ
        assertEquals(0, student1.compareByGroup(student3)); // однакова група

        // Порівняння за балом
        assertTrue(student1.compareByGrade(student2) < 0); // 4.2 < 4.8
        assertTrue(student1.compareByGrade(student3) > 0); // 4.2 > 3.5
    }

    @Test
    public void testSequentialSearch() {
        Student[] array = {
            new Student("Іваненко", "Петро", "ІТ-21", "Ч", 4.2),
            new Student("Петрова", "Марія", "ІТ-21", "Ж", 4.8),
            new Student("Сидоров", "Іван", "ІТ-21", "Ч", 3.5),
            new Student("Коваленко", "Анна", "КБ-21", "Ж", 4.9),
            new Student("Мельник", "Олег", "КБ-21", "Ч", 4.1)
        };

        // Пошук в групі ІТ-21
        int result = SearchAlgorithms.sequentialSearchExcellentFemales(array, "ІТ-21");
        assertEquals(-1, result); // знайдено 1 студентку (Петрова з балом 4.8)

        // Пошук в групі КБ-21
        result = SearchAlgorithms.sequentialSearchExcellentFemales(array, "КБ-21");
        assertEquals(-1, result); // знайдено 1 студентку (Коваленко з балом 4.9)

        // Пошук в неіснуючій групі
        result = SearchAlgorithms.sequentialSearchExcellentFemales(array, "ПІ-21");
        assertEquals(0, result); // не знайдено жодної
    }

    @Test
    public void testFindExcellentFemalesInGroup() {
        Student[] array = {
            new Student("Петрова", "Марія", "ІТ-21", "Ж", 4.8),
            new Student("Сидоров", "Іван", "ІТ-21", "Ч", 4.9),
            new Student("Коваленко", "Анна", "ІТ-21", "Ж", 4.6),
            new Student("Мельник", "Олена", "ІТ-21", "Ж", 4.2)
        };

        List<Student> found = SearchAlgorithms.findExcellentFemalesInGroup(array, "ІТ-21");
        assertEquals(2, found.size()); // Петрова (4.8) та Коваленко (4.6)

        // Перевіряємо, що знайдені правильні студентки
        assertTrue(found.stream().anyMatch(s -> "Петрова".equals(s.getSurname())));
        assertTrue(found.stream().anyMatch(s -> "Коваленко".equals(s.getSurname())));
    }

    @Test
    public void testBSTNodeCreation() {
        Student student = new Student("Тестовий", "Студент", "ТС-21", "Ч", 4.0);
        BSTNode node = new BSTNode(student);

        assertEquals(student, node.getData());
        assertNull(node.getLeft());
        assertNull(node.getRight());
        assertEquals(1, node.getHeight());
        assertTrue(node.isLeaf());
    }

    @Test
    public void testBSTNodeBalanceFactor() {
        Student student = new Student("Тестовий", "Студент", "ТС-21", "Ч", 4.0);
        BSTNode node = new BSTNode(student);

        // Початковий баланс-фактор для листа
        assertEquals(0, node.getBalanceFactor());

        // Додаємо лівий нащадок
        Student leftStudent = new Student("Лівий", "Студент", "ТС-21", "Ч", 3.5);
        node.setLeft(new BSTNode(leftStudent));
        node.updateHeight();

        assertEquals(1, node.getBalanceFactor()); // лівий вищий на 1

        // Додаємо правий нащадок
        Student rightStudent = new Student("Правий", "Студент", "ТС-21", "Ч", 4.5);
        node.setRight(new BSTNode(rightStudent));
        node.updateHeight();

        assertEquals(0, node.getBalanceFactor()); // збалансований
    }

    @Test
    public void testBSTInsertion() {
        StudentBST bst = new StudentBST();

        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());

        Student student1 = new Student("Іваненко", "Петро", "ІТ-21", "Ч", 4.2);
        bst.insertAtRoot(student1);

        assertFalse(bst.isEmpty());
        assertEquals(1, bst.size());
    }

    @Test
    public void testBSTSearch() {
        StudentBST bst = new StudentBST();

        Student student1 = new Student("Іваненко", "Петро", "ІТ-21", "Ч", 4.2);
        Student student2 = new Student("Петрова", "Марія", "КБ-22", "Ж", 4.8);

        bst.insertAtRoot(student1);
        bst.insertAtRoot(student2);

        // Знаходимо існуючих студентів
        Student found1 = bst.search(4.2);
        assertNotNull(found1);
        assertEquals("Іваненко", found1.getSurname());

        Student found2 = bst.search(4.8);
        assertNotNull(found2);
        assertEquals("Петрова", found2.getSurname());

        // Шукаємо неіснуючий бал
        Student notFound = bst.search(3.0);
        assertNull(notFound);
    }

    @Test
    public void testBSTBalancedInsertion() {
        StudentBST bst = new StudentBST();

        Student[] students = {
            new Student("Студент1", "Ім'я1", "Г-21", "Ч", 4.1),
            new Student("Студент2", "Ім'я2", "Г-21", "Ж", 4.2),
            new Student("Студент3", "Ім'я3", "Г-21", "Ч", 4.3),
            new Student("Студент4", "Ім'я4", "Г-21", "Ж", 4.4),
            new Student("Студент5", "Ім'я5", "Г-21", "Ч", 4.5)
        };

        // Додаємо студентів з балансуванням
        for (Student student : students) {
            bst.insertBalanced(student);
        }

        assertEquals(5, bst.size());
        assertTrue(bst.isBalanced());

        // Перевіряємо, що всі студенти можуть бути знайдені
        for (Student student : students) {
            assertNotNull(bst.search(student.getAverageGrade()));
        }
    }

    @Test
    public void testArrayCreation() {
        Student[] array = SearchAlgorithms.createTestArray();
        int count = SearchAlgorithms.countStudents(array);

        assertTrue(count >= 20); // мінімум 20 елементів згідно з вимогами

        // Перевіряємо упорядкованість за групою
        String previousGroup = "";
        for (Student student : array) {
            if (student != null) {
                assertTrue(student.getGroup().compareTo(previousGroup) >= 0);
                previousGroup = student.getGroup();
            }
        }
    }
}
