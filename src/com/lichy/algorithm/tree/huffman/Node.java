package com.lichy.algorithm.tree.huffman;

/**
 * huffman树所用节点
 *
 * @author lichy
 */
public class Node {
    /**
     * 数据
     */
    char data;
    /**
     * 权重
     */
    int weight;
    /**
     * 左节点在数组中的位置
     */
    int left;
    /**
     * 右节点在数组中的位置
     */
    int right;
    /**
     * 父节点在数组中的位置
     */
    int parent;

    public Node(char data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Node(int weight) {
        this.weight = weight;
    }
}
