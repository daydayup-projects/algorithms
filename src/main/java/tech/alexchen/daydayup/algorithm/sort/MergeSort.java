package tech.alexchen.daydayup.algorithm.sort;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author alexchen
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] ints = RandomUtil.randomInts(5);
        System.out.println("Before: " + Arrays.toString(ints));
        sort(ints);
        System.out.println("After:  " + Arrays.toString(ints));
    }

    public static void sort(int[] a) {
//        mergeSortUpToDown(a, 0, a.length - 1);
        mergeSortDownToUp(a);
    }

    /**
     * 递归法
     *
     * @param nums 待排序数组
     * @param l    数组左边界索引
     * @param r    数组右点解索引
     */
    private static void mergeSortUpToDown(int[] nums, int l, int r) {
        // 终止条件
        if (l >= r) {
            return;
        }
        // 递归划分
        int m = (l + r) / 2;
        mergeSortUpToDown(nums, l, m);
        mergeSortUpToDown(nums, m + 1, r);
        // 合并子数组
        merge(nums, l, m, r);
    }

    /**
     * 迭代法：自下而上
     *
     * @param a
     */
    public static void mergeSortDownToUp(int[] a) {
        if (a == null) {
            return;
        }
        int len = a.length;
        // gap 表示子数组长度，按 2 倍递增
        for (int gap = 1; gap < a.length; gap *= 2) {
            int l = 0;
            // m 为中间索引，第二个数组的第一个元素索引 为 m + 1，即 l + gap
            for (l = 0; l + 2 * gap - 1 < len; l += (2 * gap)) {
                // 右边界超了，只合并到末尾
                System.out.println(StrUtil.format("gap = {} ,l = {},m = {}, r = {}",
                        gap, l, l + gap - 1, l + 2 * gap - 1));
                merge(a, l, l + gap - 1, l + 2 * gap - 1);
            }
            // 如果没有多余数组，那么 l + gap - 1 应该等于 len - 1；否则还有多余子数组，需要进行合并
            if (l + gap - 1 < len - 1) {
                System.out.println(StrUtil.format("gap = {} ,l = {},m = {}, r = {}",
                        gap, l, l + gap - 1, len - 1));
                merge(a, l, l + gap - 1, len - 1);
            }
        }
    }

    /**
     * 合并数组 a 中的两个子数组
     *
     * @param a     数组 a
     * @param start 左数组起始索引
     * @param mid   中间索引，右数组索引是 mid + 1
     * @param end   末尾索引
     */
    public static void merge(int[] a, int start, int mid, int end) {
        // tmp 是汇总2个有序区的临时区域
        int[] tmp = new int[end - start + 1];
        // 第1个有序区的索引
        int i = start;
        // 第2个有序区的索引
        int j = mid + 1;
        // 临时区域的索引
        int k = 0;
        // 两个子数组都没排完
        while (i <= mid && j <= end) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }
        // 如果右数组排完了，而左数组没排完
        while (i <= mid) {
            tmp[k++] = a[i++];
        }
        // 如果左数组排完了，而右数组没排完
        while (j <= end) {
            tmp[k++] = a[j++];
        }
        // 将排序后的元素，全部都整合到数组a中。
        for (i = 0; i < k; i++) {
            a[start + i] = tmp[i];
        }
    }
}
