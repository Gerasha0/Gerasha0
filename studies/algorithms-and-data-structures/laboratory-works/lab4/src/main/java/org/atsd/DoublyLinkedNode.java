package org.atsd;

// Вузол двоспрямованого списку
public class DoublyLinkedNode {
    private Student data; // Дані студента
    private DoublyLinkedNode next; // Посилання на наступний вузол
    private DoublyLinkedNode prev; // Посилання на попередній вузол

    // Конструктор вузла
    public DoublyLinkedNode(Student data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    // Геттери та сеттери
    public Student getData() {
        return data;
    }

    public void setData(Student data) {
        this.data = data;
    }

    public DoublyLinkedNode getNext() {
        return next;
    }

    public void setNext(DoublyLinkedNode next) {
        this.next = next;
    }

    public DoublyLinkedNode getPrev() {
        return prev;
    }

    public void setPrev(DoublyLinkedNode prev) {
        this.prev = prev;
    }
}