package tech.alexchen.daydayup.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author alexchen
 */
public class T502 {

    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        int count = 0;
        int maxCount = 0;
        List<Integer> res = new ArrayList<>();
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // left
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // mid
            cur = stack.pop();
            if (pre == null || cur.val != pre.val) {
                count = 1;
            } else {
                count++;
            }
            if (count > maxCount) {
                maxCount = count;
                res.clear();
                res.add(cur.val);
            } else if (count == maxCount) {
                res.add(cur.val);
            }
            pre = cur;
            cur = cur.right;
        }
        return res.stream().mapToInt(x -> x).toArray();
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
