package com.lichy.algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 实现前序中序后序遍历。包括递归和非递归，非递归类型使用栈来。
 *
 * @author lichy
 */
public class TraverseTest {

    public static void main(String[] args) {
        TreeNode root = createTree();
        // 递归先序遍历
        System.out.println("递归先序遍历");
        recursionPreOrderTraversal(root);
        System.out.println();
        System.out.println("非递归先序遍历");
        stackRreOrderTraversal(root);
        System.out.println();
        // 递归中序遍历
        System.out.println("递归中序遍历");
        recursionMiddleOrderTraversal(root);
        System.out.println();
        System.out.println("非递归中序遍历");
        stackMiddleOrderTraversal(root);
        System.out.println();
        // 递归后序遍历
        System.out.println("递归后序遍历");
        recursionPostOrderTraversal(root);
        System.out.println();
        System.out.println("非递归后序遍历");
        stackPostOrderTraversal(root);
        System.out.println();

    }

    /**
     * 递归方式进行中序遍历
     *
     * @param root 根节点
     */
    private static void recursionMiddleOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        recursionMiddleOrderTraversal(root.left);
        System.out.print(root.val + " ");
        recursionMiddleOrderTraversal(root.right);
    }

    /**
     * 利用栈进行中遍历
     *
     * @param root 根节点
     */
    private static void stackMiddleOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        // 用栈保存过程中需要遍历的节点
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        for (Integer i : result) {
            System.out.print(i + " ");
        }
    }

    /**
     * 利用栈进行后序遍历。遍历的时候，根节点能够添加的条件是右节点已经入栈
     *
     * @param root 根节点
     */
    private static void stackPostOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        // 用栈保存过程中需要遍历的节点
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode lastVisit = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.peek();
                if (root.right == null || root.right == lastVisit) {
                    result.add(root.val);
                    root = stack.pop();
                    lastVisit = root;
                    root = null;
                } else {
                    root = root.right;
                }
            }
        }
        for (Integer i : result) {
            System.out.print(i + " ");
        }
    }

    /**
     * 递归方式进行后序遍历。
     *
     * @param root 根节点
     */
    private static void recursionPostOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        recursionPostOrderTraversal(root.left);
        recursionPostOrderTraversal(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * 递归方式进行先序遍历
     *
     * @param root 根节点
     */
    private static void recursionPreOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        recursionPreOrderTraversal(root.left);
        recursionPreOrderTraversal(root.right);
    }

    /**
     * 利用栈进行先序遍历
     *
     * @param root 根节点
     */
    private static void stackRreOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        // 用栈保存过程中需要遍历的节点
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                result.add(root.val);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                root = root.right;
            }
        }
        for (Integer i : result) {
            System.out.print(i + " ");
        }
    }

    /**
     * 创建树并返回根节点
     * <p>
     * ----------1
     * ------2------3
     * ---4------------5
     * ------6
     * ---7------8
     *
     * @return 根节点
     */
    private static TreeNode createTree() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node4.right = node6;
        node6.left = node7;
        node6.right = node8;
        node3.right = node5;
        return root;
    }

    /**
     * 节点对象
     */
    static class TreeNode {
        int val;
        //左子树
        TreeNode left;
        //右子树
        TreeNode right;

        //构造方法
        TreeNode(int x) {
            val = x;
        }
    }

}
