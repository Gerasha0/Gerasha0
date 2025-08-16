package org.atsd;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BST-дерево студентів з балансуванням
 * Ключ - середній бал студента
 */
public class StudentBST {
    private BSTNode root; // Корінь дерева

    /**
     * Конструктор порожнього дерева
     */
    public StudentBST() {
        this.root = null;
    }

    /**
     * Додає студента в корінь дерева з використанням ротацій
     */
    public void insertAtRoot(Student student) {
        root = insertAtRootRecursive(root, student);
    }

    /**
     * Рекурсивна вставка в корінь
     */
    private BSTNode insertAtRootRecursive(BSTNode node, Student student) {
        if (node == null) {
            return new BSTNode(student);
        }

        double studentGrade = student.getAverageGrade();
        double nodeGrade = node.getData().getAverageGrade();

        if (studentGrade < nodeGrade) {
            node.setLeft(insertAtRootRecursive(node.getLeft(), student));
            node = rotateRight(node);
        } else if (studentGrade > nodeGrade) {
            node.setRight(insertAtRootRecursive(node.getRight(), student));
            node = rotateLeft(node);
        }

        node.updateHeight();
        return node;
    }

    /**
     * Додає студента з балансуванням AVL
     */
    public void insertBalanced(Student student) {
        root = insertBalancedRecursive(root, student);
    }

    /**
     * Рекурсивна вставка з балансуванням
     */
    private BSTNode insertBalancedRecursive(BSTNode node, Student student) {
        // Звичайна BST вставка
        if (node == null) {
            return new BSTNode(student);
        }

        double studentGrade = student.getAverageGrade();
        double nodeGrade = node.getData().getAverageGrade();

        if (studentGrade < nodeGrade) {
            node.setLeft(insertBalancedRecursive(node.getLeft(), student));
        } else if (studentGrade > nodeGrade) {
            node.setRight(insertBalancedRecursive(node.getRight(), student));
        } else {
            // Однакові ключі - не додаємо дублікат
            return node;
        }

        // Оновлюємо висоту
        node.updateHeight();

        // Перевіряємо баланс та виконуємо ротації
        int balance = node.getBalanceFactor();

        // Лівий-лівий випадок
        if (balance > 1 && studentGrade < node.getLeft().getData().getAverageGrade()) {
            return rotateRight(node);
        }

        // Правий-правий випадок
        if (balance < -1 && studentGrade > node.getRight().getData().getAverageGrade()) {
            return rotateLeft(node);
        }

        // Лівий-правий випадок
        if (balance > 1 && studentGrade > node.getLeft().getData().getAverageGrade()) {
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        // Правий-лівий випадок
        if (balance < -1 && studentGrade < node.getRight().getData().getAverageGrade()) {
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }

    /**
     * Ротація вліво
     */
    public BSTNode rotateLeft(BSTNode y) {
        BSTNode x = y.getRight();
        BSTNode T2 = x.getLeft();

        // Виконуємо ротацію
        x.setLeft(y);
        y.setRight(T2);

        // Оновлюємо висоти
        y.updateHeight();
        x.updateHeight();

        return x;
    }

    /**
     * Ротація вправо
     */
    public BSTNode rotateRight(BSTNode x) {
        BSTNode y = x.getLeft();
        BSTNode T2 = y.getRight();

        // Виконуємо ротацію
        y.setRight(x);
        x.setLeft(T2);

        // Оновлюємо висоти
        x.updateHeight();
        y.updateHeight();

        return y;
    }

    /**
     * Пошук студента за середнім балом
     */
    public Student search(double averageGrade) {
        BSTNode node = searchRecursive(root, averageGrade);
        return node != null ? node.getData() : null;
    }

    /**
     * Рекурсивний пошук
     */
    private BSTNode searchRecursive(BSTNode node, double averageGrade) {
        if (node == null || Math.abs(node.getData().getAverageGrade() - averageGrade) < 0.001) {
            return node;
        }

        if (averageGrade < node.getData().getAverageGrade()) {
            return searchRecursive(node.getLeft(), averageGrade);
        } else {
            return searchRecursive(node.getRight(), averageGrade);
        }
    }

    /**
     * Обхід у ширину (breadth-first)
     */
    public void displayBreadthFirst(String title) {
        System.out.println("\n" + title);
        System.out.println("Обхід дерева у ширину (по рівнях):");
        System.out.println(Student.getTableHeader());
        System.out.println(Student.getTableSeparator());

        if (root == null) {
            System.out.println("Дерево порожнє");
            return;
        }

        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.printf("Рівень %d: ", level);

            for (int i = 0; i < levelSize; i++) {
                BSTNode current = queue.poll();
                System.out.println(current.getData());

                if (current.getLeft() != null) {
                    queue.add(current.getLeft());
                }
                if (current.getRight() != null) {
                    queue.add(current.getRight());
                }
            }
            level++;
        }
        System.out.println();
    }

    /**
     * Перевіряє, чи порожнє дерево
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Підраховує кількість вузлів
     */
    public int size() {
        return sizeRecursive(root);
    }

    /**
     * Рекурсивний підрахунок розміру
     */
    private int sizeRecursive(BSTNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeRecursive(node.getLeft()) + sizeRecursive(node.getRight());
    }

    /**
     * Обчислює висоту дерева
     */
    public int height() {
        return root != null ? root.getHeight() : 0;
    }

    /**
     * Перевіряє збалансованість дерева
     */
    public boolean isBalanced() {
        return isBalancedRecursive(root);
    }

    /**
     * Рекурсивна перевірка збалансованості
     */
    private boolean isBalancedRecursive(BSTNode node) {
        if (node == null) {
            return true;
        }

        int balance = node.getBalanceFactor();
        return Math.abs(balance) <= 1 &&
               isBalancedRecursive(node.getLeft()) &&
               isBalancedRecursive(node.getRight());
    }
}
