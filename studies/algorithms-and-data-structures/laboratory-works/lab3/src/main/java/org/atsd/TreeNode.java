package org.atsd;

/**
 * Клас вузла бінарного дерева
 */
public class TreeNode {
    private Student data; // Дані студента
    private TreeNode left; // Лівий дочірній вузол
    private TreeNode right; // Правий дочірній вузол

    /**
     * Конструктор вузла
     */
    public TreeNode(Student data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // Геттери та сеттери
    public Student getData() {
        return data;
    }

    public void setData(Student data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    /**
     * Перевіряє, чи є вузол листком
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Перевіряє, чи має вузол лише одного нащадка
     */
    public boolean hasOnlyOneChild() {
        return (left == null && right != null) || (left != null && right == null);
    }

    /**
     * Перевіряє, чи має вузол двох нащадків
     */
    public boolean hasTwoChildren() {
        return left != null && right != null;
    }
}
