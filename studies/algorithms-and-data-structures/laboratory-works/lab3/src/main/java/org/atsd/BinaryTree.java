package org.atsd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Клас бінарного дерева для зберігання студентів
public class BinaryTree {
    private TreeNode root; // Корінь дерева

    // Конструктор порожнього дерева
    public BinaryTree() {
        this.root = null;
    }

    // Додає студента до дерева
    public void insert(Student student) {
        root = insertRecursive(root, student);
    }

    // Рекурсивний метод додавання елемента
    private TreeNode insertRecursive(TreeNode current, Student student) {
        // Якщо поточний вузол порожній, створюємо новий
        if (current == null) {
            return new TreeNode(student);
        }

        // Порівнюємо за номером студентського квитка
        if (student.getStudentId() < current.getData().getStudentId()) {
            current.setLeft(insertRecursive(current.getLeft(), student));
        } else if (student.getStudentId() > current.getData().getStudentId()) {
            current.setRight(insertRecursive(current.getRight(), student));
        }
        // Якщо ключі рівні, не додаємо дублікат

        return current;
    }

    // Паралельний обхід дерева (по рівнях, breadth-first)
    public void displayParallelTraversal() {
        if (root == null) {
            System.out.println("Дерево порожнє");
            return;
        }

        System.out.println("\n=== Паралельний обхід дерева (по рівнях) ===");
        System.out.println(Student.getTableHeader());
        System.out.println(Student.getTableSeparator());

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.println(current.getData());

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
        System.out.println();
    }

    // Інфіксний обхід дерева (in-order traversal) - показує відсортовані дані
    public void displayInOrderTraversal() {
        if (root == null) {
            System.out.println("Дерево порожнє");
            return;
        }

        System.out.println("\n=== Інфіксний обхід дерева (відсортовано за квитками) ===");
        System.out.println(Student.getTableHeader());
        System.out.println(Student.getTableSeparator());
        inOrderTraversalRecursive(root);
        System.out.println();
    }

    // Рекурсивний інфіксний обхід
    private void inOrderTraversalRecursive(TreeNode current) {
        if (current != null) {
            inOrderTraversalRecursive(current.getLeft());
            System.out.println(current.getData());
            inOrderTraversalRecursive(current.getRight());
        }
    }

    // Пошук студентів за критерієм: відмінники + участь у конференціях
    public List<Student> findBySearchCriteria() {
        List<Student> result = new ArrayList<>();
        findBySearchCriteriaRecursive(root, result);
        return result;
    }

    // Рекурсивний пошук за критерієм
    private void findBySearchCriteriaRecursive(TreeNode current, List<Student> result) {
        if (current != null) {
            if (current.getData().matchesSearchCriteria()) {
                result.add(current.getData());
            }
            findBySearchCriteriaRecursive(current.getLeft(), result);
            findBySearchCriteriaRecursive(current.getRight(), result);
        }
    }

    // Видалення студентів за критерієм
    public int removeBySearchCriteria() {
        List<Integer> toRemove = new ArrayList<>();
        collectStudentIdsToRemove(root, toRemove);

        int removedCount = 0;
        for (int studentId : toRemove) {
            if (remove(studentId)) {
                removedCount++;
            }
        }

        return removedCount;
    }

    // Збирає ID студентів для видалення
    private void collectStudentIdsToRemove(TreeNode current, List<Integer> toRemove) {
        if (current != null) {
            if (current.getData().matchesSearchCriteria()) {
                toRemove.add(current.getData().getStudentId());
            }
            collectStudentIdsToRemove(current.getLeft(), toRemove);
            collectStudentIdsToRemove(current.getRight(), toRemove);
        }
    }

    // Видалення студента за номером квитка
    public boolean remove(int studentId) {
        if (search(studentId) == null) {
            return false;
        }
        root = removeRecursive(root, studentId);
        return true;
    }

    // Рекурсивний метод видалення
    private TreeNode removeRecursive(TreeNode current, int studentId) {
        if (current == null) {
            return null;
        }

        if (studentId < current.getData().getStudentId()) {
            current.setLeft(removeRecursive(current.getLeft(), studentId));
        } else if (studentId > current.getData().getStudentId()) {
            current.setRight(removeRecursive(current.getRight(), studentId));
        } else {
            // Знайшли вузол для видалення

            // Випадок 1: вузол-лист
            if (current.isLeaf()) {
                return null;
            }

            // Випадок 2: вузол має лише одного нащадка
            if (current.hasOnlyOneChild()) {
                return current.getLeft() != null ? current.getLeft() : current.getRight();
            }

            // Випадок 3: вузол має двох нащадків
            // Знаходимо найменший елемент у правому піддереві (наступник)
            TreeNode successor = findMinNode(current.getRight());
            current.setData(successor.getData());
            current.setRight(removeRecursive(current.getRight(), successor.getData().getStudentId()));
        }

        return current;
    }

    // Знаходить вузол з мінімальним значенням
    private TreeNode findMinNode(TreeNode current) {
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    // Пошук студента за номером квитка
    public Student search(int studentId) {
        TreeNode node = searchRecursive(root, studentId);
        return node != null ? node.getData() : null;
    }

    // Рекурсивний пошук
    private TreeNode searchRecursive(TreeNode current, int studentId) {
        if (current == null || current.getData().getStudentId() == studentId) {
            return current;
        }

        if (studentId < current.getData().getStudentId()) {
            return searchRecursive(current.getLeft(), studentId);
        } else {
            return searchRecursive(current.getRight(), studentId);
        }
    }

    // Перевіряє, чи порожнє дерево
    public boolean isEmpty() {
        return root == null;
    }

    // Підрахунок кількості вузлів у дереві
    public int size() {
        return sizeRecursive(root);
    }

    // Рекурсивний підрахунок розміру
    private int sizeRecursive(TreeNode current) {
        if (current == null) {
            return 0;
        }
        return 1 + sizeRecursive(current.getLeft()) + sizeRecursive(current.getRight());
    }

    // Виводить знайдених студентів у табличному вигляді
    public void displaySearchResults(List<Student> students, String criteria) {
        if (students.isEmpty()) {
            System.out.println("Студентів за критерієм \"" + criteria + "\" не знайдено.");
            return;
        }

        System.out.println("\n=== Результати пошуку: " + criteria + " ===");
        System.out.println("Знайдено студентів: " + students.size());
        System.out.println(Student.getTableHeader());
        System.out.println(Student.getTableSeparator());

        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();
    }
}