package org.atsd;

// Список з векторним способом розміщення елементів у пам'яті
// Тип елементів: цілі числа
public class VectorList {
    private int[] data;
    private int size;
    private int capacity;

    public VectorList(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
        this.size = 0;
    }

    // Перевіряє наповненість структури даних
    public boolean isFull() {
        return size == capacity;
    }

    // Вставляє елемент в кінець списку
    public boolean isEmpty() {
        return size == 0;
    }

    // Вставляє елемент в кінець списку
    public boolean add(int element) {
        if (isFull()) {
            System.out.println("Список заповнений, неможливо додати елемент: " + element);
            return false;
        }
        data[size] = element;
        size++;
        System.out.println("Додано елемент: " + element);
        return true;
    }

    // Вставляє елемент за вказаним індексом
    public boolean add(int index, int element) {
        if (isFull()) {
            System.out.println("Список заповнений, неможливо додати елемент: " + element);
            return false;
        }
        if (index < 0 || index > size) {
            System.out.println("Невірний індекс: " + index);
            return false;
        }

        // Зсуваємо елементи праворуч
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = element;
        size++;
        System.out.println("Додано елемент " + element + " за індексом " + index);
        return true;
    }

    // Видаляє елемент за індексом
    public int remove(int index) throws IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new RuntimeException("Список порожній, неможливо видалити елемент");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Невірний індекс: " + index);
        }

        int removedElement = data[index];

        // Зсуваємо елементи ліворуч
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
        System.out.println("Видалено елемент: " + removedElement + " за індексом " + index);
        return removedElement;
    }

    // Видаляє останній елемент
    public int removeLast() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("Список порожній, неможливо видалити елемент");
        }

        size--;
        int removedElement = data[size];
        System.out.println("Видалено останній елемент: " + removedElement);
        return removedElement;
    }

    // Отримує елемент за індексом
    public int get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Невірний індекс: " + index);
        }
        return data[index];
    }

    // Повертає розмір списку
    public int size() {
        return size;
    }

    // Виводить вміст списку
    public void display() {
        System.out.print("Список [" + size + "/" + capacity + "]: [");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}