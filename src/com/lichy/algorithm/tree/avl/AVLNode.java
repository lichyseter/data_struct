package com.lichy.algorithm.tree.avl;

/**
 * 平衡树节点
 *
 * @author lichy
 */
class AVLNode<T extends Comparable> {
    /**
     * 左节点
     */
    AVLNode<T> left;
    /**
     * 右节点
     */
    AVLNode<T> right;
    /**
     * 高度
     */
    int height;
    /**
     * 关联的数据
     */
    T value;

    AVLNode(T value) {
        this.value = value;
    }
}
