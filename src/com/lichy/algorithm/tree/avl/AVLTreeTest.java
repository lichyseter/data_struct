package com.lichy.algorithm.tree.avl;

/**
 * 测试平衡二叉树类
 *
 * @author lichy
 */
public class AVLTreeTest {

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTreeImpl<>();
        for (int i = 1; i < 18; i++) {
            tree.insert(i);
        }
        int[] expectOrder = new int[]{8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 16, 15, 17};
        System.out.println("树的前序遍历" + tree.preOrder());
        StringBuilder sb = new StringBuilder();
        for (int i : expectOrder) {
            sb.append(i).append(",");
        }
        System.out.println("前序遍历是否符合预期：" + sb.toString().equals(tree.preOrder()));
        AVLNode<Integer> node = tree.search(4);
        System.out.println("搜索值为6的节点：值为【" + node.value + "】，左节点值为【" + node.left.value + "】，右节点值为【" + node.right.value + "】");
        // 删除11, 9以触发旋转平衡操作
        tree.delete(11);
        tree.delete(9);
        System.out.println("删除后树的前序遍历" + tree.preOrder());
        sb = new StringBuilder();
        int[] expectOrderDelete = new int[]{8, 4, 2, 1, 3, 6, 5, 7, 14, 12, 10, 13, 16, 15, 17};
        for (int i : expectOrderDelete) {
            sb.append(i).append(",");
        }
        System.out.println("删除后是否符合预期：" + sb.toString().equals(tree.preOrder()));


    }
}
