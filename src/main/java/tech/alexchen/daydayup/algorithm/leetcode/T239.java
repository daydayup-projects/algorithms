package tech.alexchen.daydayup.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 239. 滑动窗口最大值
 *
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 *
 * @author alexchen
 */
public class T239 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] res = new T239().maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(res));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue queue = new MonotonicQueue();
        int[] res = new int[nums.length - k + 1];
        // 放入前 k 个元素
        for (int i = 0; i < k; i++) {
            queue.push(nums[i]);
        }
        res[0] = queue.max();
        for (int i = k; i < nums.length; i++) {
            queue.push(nums[i]);
            queue.pop(nums[i - k]);
            res[i - k + 1] = queue.max();
        }
        return res;
    }

    /**
     * 单调队列
     */
    static class MonotonicQueue {
        Deque<Integer> queue = new ArrayDeque<>();

        /**
         * push 方法依然在队尾添加元素，但是要把前面比自己小的元素都删掉
         */
        public void push(int val) {
            while (!queue.isEmpty() && queue.peekLast() < val) {
                queue.pollLast();
            }
            queue.offerLast(val);
        }

        public void pop(int val) {
            if (!queue.isEmpty() && val == queue.peekFirst()) {
                queue.pollFirst();
            }
        }

        public int max() {
            return queue.peekFirst();
        }
    }
}
