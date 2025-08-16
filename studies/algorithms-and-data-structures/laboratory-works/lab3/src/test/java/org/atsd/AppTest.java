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
        Student student = new Student("Іваненко", 12345, 4.8, true);

        assertEquals("Іваненко", student.getSurname());
        assertEquals(12345, student.getStudentId());
        assertEquals(4.8, student.getAverageGrade(), 0.01);
        assertTrue(student.participatesInConferences());
    }

    @Test
    public void testStudentIsExcellent() {
        Student excellent = new Student("Відмінник", 11111, 4.7, true);
        Student good = new Student("Хорошист", 22222, 4.2, true);

        assertTrue(excellent.isExcellentStudent());
        assertFalse(good.isExcellentStudent());
    }

    @Test
    public void testStudentMatchesSearchCriteria() {
        Student matches = new Student("Відмінник", 11111, 4.8, true);     // відмінник + конференції
        Student noConf = new Student("Відмінник", 22222, 4.9, false);     // відмінник, але без конференцій
        Student notExcellent = new Student("Хорошист", 33333, 4.1, true); // конференції, але не відмінник

        assertTrue(matches.matchesSearchCriteria());
        assertFalse(noConf.matchesSearchCriteria());
        assertFalse(notExcellent.matchesSearchCriteria());
    }

    @Test
    public void testTreeNodeCreation() {
        Student student = new Student("Тест", 12345, 4.5, true);
        TreeNode node = new TreeNode(student);

        assertEquals(student, node.getData());
        assertNull(node.getLeft());
        assertNull(node.getRight());
        assertTrue(node.isLeaf());
    }

    @Test
    public void testTreeInsertion() {
        BinaryTree tree = new BinaryTree();

        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());

        tree.insert(new Student("Студент1", 12345, 4.5, true));
        tree.insert(new Student("Студент2", 10000, 4.2, false));
        tree.insert(new Student("Студент3", 15000, 4.8, true));

        assertFalse(tree.isEmpty());
        assertEquals(3, tree.size());
    }

    @Test
    public void testTreeSearch() {
        BinaryTree tree = new BinaryTree();
        Student student1 = new Student("Іваненко", 12345, 4.8, true);
        Student student2 = new Student("Петренко", 10000, 4.2, false);

        tree.insert(student1);
        tree.insert(student2);

        Student found = tree.search(12345);
        assertNotNull(found);
        assertEquals("Іваненко", found.getSurname());

        Student notFound = tree.search(99999);
        assertNull(notFound);
    }

    @Test
    public void testTreeSearchByCriteria() {
        BinaryTree tree = new BinaryTree();

        tree.insert(new Student("Відмінник1", 11111, 4.9, true));  // відповідає
        tree.insert(new Student("Хорошист", 22222, 4.2, true));    // не відповідає
        tree.insert(new Student("Відмінник2", 33333, 4.8, false)); // не відповідає
        tree.insert(new Student("Відмінник3", 44444, 4.7, true));  // відповідає

        List<Student> result = tree.findBySearchCriteria();
        assertEquals(2, result.size());

        // Перевіряємо, що знайдені студенти справді відповідають критерію
        for (Student student : result) {
            assertTrue(student.matchesSearchCriteria());
        }
    }

    @Test
    public void testTreeRemoval() {
        BinaryTree tree = new BinaryTree();

        tree.insert(new Student("Корінь", 50000, 4.0, false));
        tree.insert(new Student("Лівий", 30000, 4.8, true));  // для видалення
        tree.insert(new Student("Правий", 70000, 4.1, false));

        assertEquals(3, tree.size());

        // Знаходимо студентів для видалення
        List<Student> toRemove = tree.findBySearchCriteria();
        assertEquals(1, toRemove.size());

        // Видаляємо
        int removedCount = tree.removeBySearchCriteria();
        assertEquals(1, removedCount);
        assertEquals(2, tree.size());

        // Перевіряємо, що студент справді видалений
        assertNull(tree.search(30000));
    }

    @Test
    public void testNodeTypes() {
        Student student = new Student("Тест", 12345, 4.5, true);
        TreeNode node = new TreeNode(student);
        TreeNode left = new TreeNode(new Student("Лівий", 10000, 4.0, false));
        TreeNode right = new TreeNode(new Student("Правий", 15000, 4.0, false));

        // Тест листка
        assertTrue(node.isLeaf());
        assertFalse(node.hasOnlyOneChild());
        assertFalse(node.hasTwoChildren());

        // Тест вузла з одним нащадком
        node.setLeft(left);
        assertFalse(node.isLeaf());
        assertTrue(node.hasOnlyOneChild());
        assertFalse(node.hasTwoChildren());

        // Тест вузла з двома нащадками
        node.setRight(right);
        assertFalse(node.isLeaf());
        assertFalse(node.hasOnlyOneChild());
        assertTrue(node.hasTwoChildren());
    }
}
