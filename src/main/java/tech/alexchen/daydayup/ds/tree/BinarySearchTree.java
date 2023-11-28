package tech.alexchen.daydayup.ds.tree;

import java.util.LinkedList;

/**
 * 在二叉查找树中:
 * 若任意节点的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * 任意节点的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 * 任意节点的左、右子树也分别为二叉查找树。
 * 没有键值相等的节点。
 *
 * @author alexchen
 * @date 2022/7/2
 */
public class BinarySearchTree<T extends Comparable<T>> {

    /**
     * 跟结点
     */
    private TreeNode<T> mRoot;

    /**
     * 先序遍历
     */
    public void preOrder() {
        preOrder(mRoot);
    }

    private void preOrder(TreeNode<T> node) {
        if (node != null) {
            System.out.printf("%c ", node.key);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(mRoot);
    }

    private void inOrder(TreeNode<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.printf("%c ", node.key);
            inOrder(node.right);
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(mRoot);
    }

    private void postOrder(TreeNode<T> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.printf("%c ", node.key);
        }
    }

    /**
     * 层次遍历
     */
    public void levelTraversal() {
        LinkedList<TreeNode<T>> queue = new LinkedList<>();
        // 访问跟结点
        System.out.printf("%c ", mRoot.key);
        queue.push(mRoot);

        while (queue.size() > 0) {
        }
    }

    private static class TreeNode<T extends Comparable<T>> {
        /**
         * 信息
         */
        T key;
        /**
         * 左孩子
         */
        TreeNode<T> left;
        /**
         * 右孩子
         */
        TreeNode<T> right;
        /**
         * 父结点
         */
        TreeNode<T> parent;

        public TreeNode(T key, TreeNode<T> left, TreeNode<T> right, TreeNode<T> parent) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

}
