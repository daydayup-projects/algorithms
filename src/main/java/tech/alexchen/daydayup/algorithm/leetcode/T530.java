package tech.alexchen.daydayup.algorithm.leetcode;

import java.util.Stack;

/**
 * @author alexchen
 */
public class T530 {

    public int getMinimumDifference(TreeNode root) {
        int diff = Integer.MAX_VALUE;
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()) {
            // 左
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 中
            cur = stack.pop();
            if (pre != null) {
                diff = Math.min(diff, Math.abs(cur.val - pre.val));
            }
            pre = cur;
            cur = cur.right;
        }
        return diff;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
