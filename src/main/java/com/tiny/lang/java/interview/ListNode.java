package com.tiny.lang.java.interview;

public class ListNode<T> {

    T value;

    ListNode<T> next;

    ListNode<T> prev;

    int size;

    public boolean hasCycle() {
        ListNode<T> slow = this;
        ListNode<T> fast = this.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }

            slow = this.next;
            fast = fast.next.next;
        }
        
        return true;
    }

}
