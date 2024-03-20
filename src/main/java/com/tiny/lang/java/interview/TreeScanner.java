package com.tiny.lang.java.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author tiny
 */
public class TreeScanner {

    private static int maxSum;

    private static final TreeNode<Integer> TREE_NODE = Optional.of(new TreeNode<>(-11))
        .map(root -> {
            var leftChild = new TreeNode<>(2);
            var rightChild = new TreeNode<>(3);
            leftChild.left = new TreeNode<>(4);
            leftChild.right = new TreeNode<>(5);
            root.left = leftChild;
            root.right = rightChild;
            return root;
        }).get();

    public static void main(String[] args) {
        System.out.println(TREE_NODE);
        printTree();
        System.out.println(jump(4));
        System.out.println(maxSumValue(TREE_NODE));
        System.out.println(maxSum);
    }

    private static void printTree() {
        var container = new LinkedList<List<TreeNode<Integer>>>();
        container.add(Collections.singletonList(TREE_NODE));
        List<TreeNode<Integer>> last;
        while ((last = container.getLast()) != null) {
            var children = new ArrayList<TreeNode<Integer>>();
            for (TreeNode<Integer> stringNode : last) {
                if (stringNode.left != null) {
                    children.add(stringNode.left);
                }
                if (stringNode.right != null) {
                    children.add(stringNode.right);
                }
            }
            if (children.isEmpty()) {
                break;
            } else {
                container.add(children);
            }
        }
        System.out.println(last);
    }

    private static int jump(int step) {
        if (step == 1) {
            return 1;
        }

        if (step == 2) {
            return 2;
        }

        return jump(step - 1) + jump(step - 2);
    }

    public static int maxSumValue(TreeNode<Integer> tree) {
        if (tree == null || tree.value == null) {
            return 0;
        }

        var leftMax = Math.max(maxSumValue(tree.left), 0);
        var rightMax = Math.max(maxSumValue(tree.right), 0);

        var nodeMax = leftMax + rightMax + tree.value;
        maxSum = Math.max(maxSum, nodeMax);

        return tree.value + Math.max(leftMax, rightMax);
    }
}
