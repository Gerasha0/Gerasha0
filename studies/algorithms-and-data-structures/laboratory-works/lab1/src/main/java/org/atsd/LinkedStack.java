package org.atsd;

/**
 * Вузол стеку для зберігання рядкового елементу
 */
class StackNode {
    String data;
    StackNode next;

    public StackNode(String data) {
        this.data = data;
        this.next = null;
    }
}

/**
 * Стек зі зв'язним способом розміщення елементів у пам'яті
 * Тип елементів: рядкові (цілі додатні числа у вісімковій системі числення)
 */
public class LinkedStack {
    private StackNode top;

    public LinkedStack() {
        this.top = null;
    }

    /**
     * Перевіряє порожність структури даних
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Поміщає елемент у стек
     */
    public void push(String element) {
        StackNode newNode = new StackNode(element);
        newNode.next = top;
        top = newNode;
        System.out.println("У стек додано елемент: " + element);
    }

    /**
     * Вилучає елемент зі стеку
     */
    public String pop() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("Стек порожній, неможливо вилучити елемент");
        }

        String poppedElement = top.data;
        top = top.next;
        System.out.println("Зі стеку вилучено елемент: " + poppedElement);
        return poppedElement;
    }

    /**
     * Повертає верхній елемент стеку без видалення
     */
    public String peek() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("Стек порожній");
        }
        return top.data;
    }

    /**
     * Виводить вміст стеку
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Стек порожній: []");
            return;
        }

        System.out.print("Стек (зверху вниз): [");
        StackNode current = top;
        boolean first = true;
        while (current != null) {
            if (!first) {
                System.out.print(", ");
            }
            System.out.print(current.data);
            current = current.next;
            first = false;
        }
        System.out.println("]");
    }

    /**
     * Повертає розмір стеку
     */
    public int size() {
        int count = 0;
        StackNode current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
