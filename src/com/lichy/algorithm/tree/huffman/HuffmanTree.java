package com.lichy.algorithm.tree.huffman;

import java.util.Arrays;

/**
 * huffman树构造和选择
 *
 * @author lichy
 */
public class HuffmanTree {
    /**
     * 已经排序过得数据节点个数
     */
    int selectStart = 0;

    /**
     * 构造huffman树
     *
     * @param nodes 输入节点数据
     * @return huffman树节点
     */
    public Node[] buildTree(Node[] nodes) {
        int n = nodes.length; // 数据节点的数量
        int m = 2 * n - 1; // 数据节点 + 外结点的总数量
        Node[] HT = new Node[m];
        for (int i = 0; i < m; i++) HT[i] = new Node(0);
        //初始化数据节点
        for (int i = 0; i < n; i++) {
            HT[i].data = nodes[i].data;
            HT[i].weight = nodes[i].weight;
        }
        for (int i = n; i < m; i++) {
            int s1 = select(HT, i, 0); // 取得HT数组中权值最小的结点对象的下标
            int s2 = select(HT, i, 1); // 取得HT数组中权值次小的结点对象的下标
            HT[i].left = s1;
            HT[i].right = s2;
            HT[s1].parent = i;
            HT[s2].parent = i;
            HT[i].weight = HT[s1].weight + HT[s2].weight;// 计算当前外结点的权值
            selectStart += 2; // 这个变量表示之前“被删除”的最小结点的数量和
        }
        return HT;
    }

    /**
     * 返回权值排名为rank的结点对象在HT中的下标（按权值从小到大排）
     */
    private int select(Node[] HT, int range, int rank) {
        Node[] copyNodes = Arrays.copyOf(HT, range);// 将HT[0]~HT[range]拷贝到copyNodes中
        QuickSort.sort(copyNodes); // 对copyNodes进行从小到大的快速排序
        Node target = copyNodes[rank + selectStart]; // 取得“删除”后权值排名为rank的结点对象
        for (int j = 0; j < HT.length; j++) {
            if (target == HT[j]) return j; // 返回该结点对象在数组HT中的下标
        }
        return -1;
    }

    /**
     * @description: 进行赫夫曼译码
     */
    public String decode(Node[] nodes, String code) {
        StringBuilder str = new StringBuilder();
        Node[] HT = buildTree(nodes);
        int n = HT.length - 1;
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (c == '1') {
                n = HT[n].right;
            } else {
                n = HT[n].left;
            }
            if (HT[n].left == 0) {
                str.append(HT[n].data);
                n = HT.length - 1;
            }
        }
        return str.toString();
    }

    /**
     * decode方法的用例
     */
    public static void main(String[] args) {
        Node[] nodes = new Node[4];
        nodes[0] = new Node('A', 4);
        nodes[1] = new Node('B', 7);
        nodes[2] = new Node('C', 5);
        nodes[3] = new Node('D', 2);
        HuffmanTree ht = new HuffmanTree();
        // 对 010110111 进行译码
        System.out.println(ht.decode(nodes, "010110111"));
    }

}
