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

    public TreeNode<T> smallestCommonParent(TreeNode<T> childA, TreeNode<T> childB) {
        if (childA == null || childB == null) {
            return null;
        }

        if (this == childA && this == childB) {
            return this;
        }

        TreeNode<T> parent = null;
        if (this.contains(childA) && this.contains(childB)) {
            parent = this;
        }

        if (parent == null) {
            return null;
        }

        while (parent.left != null || parent.right != null) {
            boolean flag = false;
            if (parent.left != null
                && parent.left.contains(childA)
                && parent.left.contains(childB)) {
                parent = parent.left;
                flag = true;
            } else if (parent.right != null
                && parent.right.contains(childA)
                && parent.right.contains(childB)) {
                parent = parent.right;
                flag = true;
            }

            if (!flag) {
                break;
            }
        }

        return parent;
    }

    private boolean contains(TreeNode<T> node) {
        if (node == this) {
            return true;
        }

        boolean flag = false;
        if (this.left != null) {
            flag = this.left.contains(node);
        }

        if (!flag && this.right != null) {
            flag = this.right.contains(node);
        }

        return flag;
    }

    public void flip() {
        var tree = this;

        if (tree.left == null && tree.right == null) {
            return;
        }

        var left = tree.left;
        if (left != null) {
            left.flip();
        }

        var right = tree.right;
        if (right != null) {
            right.flip();
        }

        tree.left = right;
        tree.right = left;
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

        return String.join(", ", String.valueOf(value), leftStr, rightStr);
    }

    public static void main(String[] args) {
        TreeNode<Integer> one = new TreeNode<>(1);
        one.value = 1;
        one.left = new TreeNode<>(2);
        one.right = new TreeNode<>(3);
        one.right.left = new TreeNode<>(4);
        one.right.right = new TreeNode<>(5);
        one.right.right.left = new TreeNode<>(6);
        System.out.println(one.depth());

        // one.flip();
        // System.out.println(one);

        TreeNode<Integer> integerTreeNode = one.smallestCommonParent(one.right.left, one.right.right.left);
        System.out.println(integerTreeNode.value);
    }
}
