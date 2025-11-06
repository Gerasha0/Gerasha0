package org.atsd;

import java.util.ArrayList;
import java.util.List;

// Двоспрямований список студентів з алгоритмами сортування
public class DoublyLinkedList {
    private DoublyLinkedNode head; // Голова списку
    private DoublyLinkedNode tail; // Хвіст списку
    private int size; // Розмір списку

    // Конструктор порожнього списку
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Додає студента в кінець списку
    public void add(Student student) {
        DoublyLinkedNode newNode = new DoublyLinkedNode(student);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
    }

    // Перевіряє, чи порожній список
    public boolean isEmpty() {
        return head == null;
    }

    // Повертає розмір списку
    public int size() {
        return size;
    }

    // Виводить вміст списку у табличному вигляді
    public void display(String title) {
        System.out.println("\n" + title);
        System.out.println(Student.getTableHeader());
        System.out.println(Student.getTableSeparator());

        if (isEmpty()) {
            System.out.println("Список порожній");
            return;
        }

        DoublyLinkedNode current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
        System.out.println();
    }

    // Сортування вставкою за середнім балом (зростання)
    public void insertionSort() {
        if (head == null || head.getNext() == null) {
            return; // Список порожній або має лише один елемент
        }

        DoublyLinkedNode current = head.getNext();

        while (current != null) {
            DoublyLinkedNode nextNode = current.getNext();
            Student currentData = current.getData();

            // Знаходимо позицію для вставки
            DoublyLinkedNode insertPos = current.getPrev();
            while (insertPos != null && insertPos.getData().getAverageGrade() > currentData.getAverageGrade()) {
                insertPos = insertPos.getPrev();
            }

            // Видаляємо поточний вузол з його позиції
            if (current.getNext() != null) {
                current.getNext().setPrev(current.getPrev());
            } else {
                tail = current.getPrev();
            }
            current.getPrev().setNext(current.getNext());

            // Вставляємо вузол у нову позицію
            if (insertPos == null) {
                // Вставляємо на початок списку
                current.setPrev(null);
                current.setNext(head);
                head.setPrev(current);
                head = current;
            } else {
                // Вставляємо після insertPos
                current.setPrev(insertPos);
                current.setNext(insertPos.getNext());
                if (insertPos.getNext() != null) {
                    insertPos.getNext().setPrev(current);
                }
                insertPos.setNext(current);
            }

            current = nextNode;
        }
    }

    // Перетворює список у масив для кишенькового сортування
    public Student[] toArray() {
        Student[] array = new Student[size];
        DoublyLinkedNode current = head;
        int index = 0;

        while (current != null) {
            array[index++] = current.getData();
            current = current.getNext();
        }

        return array;
    }

    // Створює список з масиву
    public static DoublyLinkedList fromArray(Student[] array) {
        DoublyLinkedList list = new DoublyLinkedList();
        for (Student student : array) {
            list.add(student);
        }
        return list;
    }
}