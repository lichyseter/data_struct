package com.lichy.algorithm.tree.avl;

import java.util.List;

/**
 * 平衡树实现
 *
 * @author lichy
 */
public class AVLTreeImpl<T extends Comparable> implements AVLTree<T> {

    /**
     * 根节点
     */
    private AVLNode<T> root;


    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    /**
     * 返回树中元素的总个数
     *
     * @param subtree 节点
     * @return 总数
     */
    private int size(AVLNode<T> subtree) {
        if (subtree == null) {
            return 0;
        } else {
            return size(subtree.left) + 1 + size(subtree.right);
        }
    }

    @Override
    public AVLNode<T> insert(T value) {
        if (value == null) {
            throw new RuntimeException("不能将空值插入平衡树");
        }
        if (root == null) {
            root = new AVLNode<T>(value);
            return root;
        }
        root = insert(root, value);
        return root;
    }

    /**
     * 插入并调整失衡节点
     *
     * @param node  当前节点
     * @param value 待插入值
     * @return 根节点
     */
    private AVLNode<T> insert(AVLNode<T> node, T value) {
        if (node == null) {
            node = new AVLNode<T>(value);
            return node;
        }
        if (value.equals(node.value)) {
            // 如果值存在则直接返回
            return node;
        }
        if (value.compareTo(node.value) < 0) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }
        node = inputRebalanced(node, value);
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        return node;
    }

    /**
     * 调整平衡二叉树
     *
     * @param node 当前节点
     * @return 旋转后的根节点
     */
    private AVLNode<T> inputRebalanced(AVLNode<T> node, T value) {
        int balancedFactor = getBalancedFactor(node);
        if (balancedFactor > 1 && value.compareTo(node.value) < 0) {
            return rightRotation(node);
        }
        if (balancedFactor < -1 && value.compareTo(node.value) > 0) {
            return leftRotation(node);
        }
        if (balancedFactor > 1 && value.compareTo(node.left.value) > 0) {
            AVLNode<T> temp = leftRotation(node.left);
            return rightRotation(temp);
        }
        if (balancedFactor < -1 && value.compareTo(node.right.value) < 0) {
            AVLNode<T> temp = rightRotation(node.right);
            return leftRotation(temp);
        }
        return node;
    }

    /**
     * 平衡因子，左孩子的高度减去右孩子的高度。当为-1、0、1的情况是平衡二叉树
     *
     * @param node 当前节点
     * @return 平衡因子
     */
    private int getBalancedFactor(AVLNode<T> node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 获取高度
     *
     * @param node 节点
     * @return 高度
     */
    private int getHeight(AVLNode<T> node) {
        return node == null ? -1 : node.height;
    }

    @Override
    public AVLNode delete(T value) {
        if (root == null) {
            return null;
        }
        return delete(root, value);
    }

    /**
     * 删除对象
     *
     * @param node 节点
     * @param value 值
     * @return 根节点
     */
    private AVLNode<T> delete(AVLNode<T> node, T value) {
        if (node == null) {
            return null;
        }
        if (node.value.compareTo(value) > 0) {
            node.left = delete(node.left, value);
        } else if (node.value.compareTo(value) < 0) {
            node.right = delete(node.right, value);
        } else {
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.left != null && node.right == null) {
                node = node.left;
            } else if (node.left == null) {
                node = node.right;
            } else {
                AVLNode<T> temp = minOfSubtree(node.right);
                node.value = temp.value;
                node.right = delete(node.right, temp.value);
            }
        }
        if (node == null) {
            return null;
        }
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        return deleteRebalanced(node, value);
    }

    /**
     * 删除时调整平衡二叉树
     *
     * @param node 当前节点
     * @return 旋转后的根节点
     */
    private AVLNode<T> deleteRebalanced(AVLNode<T> node, T value) {
        int balancedFactor = getBalancedFactor(node);
        if (balancedFactor > 1) {
            return rightRotation(node);
        }
        if (balancedFactor < -1) {
            return leftRotation(node);
        }
        return node;
    }

    /**
     * 查找当前节点下的最小的节点
     *
     * @param node 当前节点
     * @return 最小节点
     */
    private AVLNode<T> minOfSubtree(AVLNode<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    @Override
    public AVLNode<T> search(T value) {
        return search(root,value);
    }

    /**
     * 递归搜索节点
     * @param node 节点
     * @param value 值
     * @return 节点
     */
    private AVLNode<T> search(AVLNode<T> node, T value) {
        if (node != null) {
            if (node.value.compareTo(value)==0) return node;
            if (node.value.compareTo(value)<0) return search(node.right, value);
            if (node.value.compareTo(value)>0) return search(node.left, value);
        }
        return null;
    }

    /**
     * 右旋
     *
     * @param node 当前节点
     * @return 旋转后的根节点
     */
    private AVLNode<T> rightRotation(AVLNode<T> node) {
        AVLNode<T> left = node.left;
        node.left = left.right;
        left.right = node;
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        left.height = 1 + Math.max(getHeight(left.left), getHeight(left.right));
        return left;
    }

    /**
     * 左旋
     *
     * @param node 当前节点
     * @return 旋转后的根节点
     */
    private AVLNode<T> leftRotation(AVLNode<T> node) {
        AVLNode<T> right = node.right;
        node.right = right.left;
        right.left = node;
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        right.height = 1 + Math.max(getHeight(right.left), getHeight(right.right));
        return right;
    }

    @Override
    public String preOrder() {
        return preOrder(root);
    }

    /**
     * 先序遍历节点下的所有节点
     *
     * @param node 节点
     * @return 先序遍历字符串
     */
    private String preOrder(AVLNode node) {
        StringBuilder sb = new StringBuilder();
        if (node != null) {
            //先访问根结点
            sb.append(node.value).append(",");
            //访问左子树
            sb.append(preOrder(node.left));
            //访问右子树
            sb.append(preOrder(node.right));
        }
        return sb.toString();
    }

}
