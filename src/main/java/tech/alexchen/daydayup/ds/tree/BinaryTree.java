package tech.alexchen.daydayup.ds.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树
 *
 * @author alexchen
 * @date 2022/6/30
 */
public class BinaryTree<T> {

    /**
     * 根结点
     */
    private TreeNode<T> root = null;

    public BinaryTree() {
        root = new TreeNode<>();
    }

    public BinaryTree(T data) {
        root = new TreeNode<>(data);
    }

    /**
     * 判断跟节点是否为空
     *
     * @return 返回根节点是否为空
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 返回树的结点个数
     *
     * @return 树的结点个数
     */
    public int size() {
        return size(root);
    }

    /**
     * 返回树的结点个数
     *
     * @param root 根结点
     * @return 树的结点个数
     */
    private int size(TreeNode<T> root) {
        return root == null ? 0 : 1 + size(root.left) + size(root.right);
    }

    /**
     * 返回树的深度
     *
     * @return 树的深度
     */
    public int depth() {
        return depth(root);
    }

    /**
     * 返回指定树的深度
     *
     * @param root 指定根结点
     * @return 树的深度
     */
    private int depth(TreeNode<T> root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(depth(root.left), depth(root.right)) + 1;
        }
    }

    /**
     * 先序遍历
     */
    public List<T> preorderTraversal(TreeNode<T> root) {
        List<T> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.add(root.value);
        res.addAll(preorderTraversal(root.left));
        res.addAll(preorderTraversal(root.right));
        return res;
    }



    static class TreeNode<T> {
        /**
         * 值
         */
        T value;

        /**
         * 是否已遍历
         */
        final boolean isVisited = false;
        /**
         * 左儿子节点
         */
        TreeNode<T> left;
        /**
         * 右儿子节点
         */
        TreeNode<T> right;

        /**
         * 默认构造方法
         */
        public TreeNode() {}

        /**
         * @param value 数据域
         */
        public TreeNode(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
