package com.tiny.lang.java.interview;

/**
 * @author tiny
 */
public class ReverseList {

    public static void main(String[] args) {
        var head = new Node<>("a");
        head.next = new Node<>("b");
        head.next.next = new Node<>("c");

        var newHead = head.reverse();
        System.out.println(newHead);
    }

    static class Node<T> {

        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        // a -> b -> c
        // a <- b <- c
        public Node<T> reverse() {
            Node<T> current = this;
            Node<T> next = null;
            while (current != null) {
                Node<T> tmp = current.next;
                current.next = next;
                next = current;
                current = tmp;
            }

            return next;
        }
    }
}
