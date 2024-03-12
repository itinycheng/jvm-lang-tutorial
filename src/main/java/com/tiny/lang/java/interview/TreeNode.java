package com.tiny.lang.java.interview;

/**
 * @author tiny
 */
public class TreeNode<T> {

    public T value;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T value) {
        this.value = value;
    }

    public int depth() {
        int deepL = 0;
        if (left != null) {
            deepL = left.depth();
        }

        int deepR = 0;
        if (right != null) {
            deepR = right.depth();
        }

        return Math.max(deepL, deepR) + 1;
    }

    @Override
    public String toString() {
        String leftStr = null;
        if (left != null) {
            leftStr = left.toString();
        }

        String rightStr = null;
        if (right != null) {
            rightStr = right.toString();
        }

        return String.join(", ", String.valueOf(value), leftStr,  rightStr);
    }

    public static void main(String[] args) {
        TreeNode<Integer> one = new TreeNode<>(1);
        one.value = 1;
        one.left = new TreeNode<>(1);
        one.right = new TreeNode<>(2);
        one.right.right = new TreeNode<>(3);
        System.out.println(one.depth());
    }
}
