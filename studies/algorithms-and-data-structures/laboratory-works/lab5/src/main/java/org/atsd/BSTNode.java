package org.atsd;

/**
 * Вузол BST-дерева для студентів
 */
public class BSTNode {
    private Student data; // Дані студента
    private BSTNode left; // Лівий нащадок
    private BSTNode right; // Правий нащадок
    private int height; // Висота вузла (для AVL-балансування)

    /**
     * Конструктор вузла
     */
    public BSTNode(Student data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;
    }

    // Геттери та сеттери
    public Student getData() {
        return data;
    }

    public void setData(Student data) {
        this.data = data;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Перевіряє, чи є вузол листком
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Обчислює баланс-фактор вузла
     */
    public int getBalanceFactor() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        return leftHeight - rightHeight;
    }

    /**
     * Оновлює висоту вузла
     */
    public void updateHeight() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        height = Math.max(leftHeight, rightHeight) + 1;
    }
}
