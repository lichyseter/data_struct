package com.lichy.algorithm.tree.avl;

import java.util.List;

/**
 * 平衡树接口
 *
 * @author lichy
 */
public interface AVLTree<T extends Comparable> {
    /**
     * 总数量是否为空
     *
     * @return 是否为空
     */
    boolean isEmpty();

    /**
     * 返回总节点数目
     *
     * @return 总节点数目
     */
    int size();

    /**
     * 插入一个对象，并返回节点
     *
     * @param value 对象的值
     * @return 插入的对象
     */
    AVLNode insert(T value);

    /**
     * 删除一个对象，并返回节点
     *
     * @param value 对象
     * @return 删除的对象
     */
    AVLNode delete(T value);

    /**
     * 查找一个对象，并返回节点
     *
     * @param value 对象value
     * @return 删除的对象
     */
    AVLNode<T> search(T value);

    /**
     * 先序遍历
     *
     * @return 先序遍历结果
     */
    String preOrder();
}
