package tech.alexchen.daydayup.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.cn/problems/top-k-frequent-elements/">347. 前 K 个高频元素</a>
 *
 * @author alexchen
 */
public class T347 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int[] res = new T347().topKFrequent(nums, 2);
        System.out.println(Arrays.toString(res));
    }

    public int[] topKFrequent(int[] nums, int k) {
        // 统计频率
        Map<Integer, Integer> freqCountMap = new HashMap<>();
        for (int num : nums) {
            freqCountMap.put(num, freqCountMap.getOrDefault(num, 0) + 1);
        }

        // 使用优先队列
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue().compareTo(b.getValue()));

        for (Map.Entry<Integer, Integer> entry : freqCountMap.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll().getKey();
        }
        return res;
    }

}
