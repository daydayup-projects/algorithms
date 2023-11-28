package tech.alexchen.daydayup.algorithm.sort;

import cn.hutool.core.util.RandomUtil;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author alexchen
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] ints = RandomUtil.randomInts(16);
        System.out.println("Before: " + Arrays.toString(ints));
        sort(ints);
        System.out.println("After:  " + Arrays.toString(ints));
    }

    /**
     * 冒泡排序，返回升序排序后的数组
     * @param a 待排序待数组
     * @return
     */
    public static void sort(int[] a) {
        int n = a.length;
        boolean flag = false;
        // i 从 n-1 到 0，代表一次内循环确定的下标
        for (int i = n-1; i > 0; i--) {
            // j 从 0 到 i-1
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j+1]) {
                    // 交换元素
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    flag = true;
                }
            }
            // 如果没有发生交换，说明数组已经有序了
            if (!flag) {
                break;
            }
        }
    }
}
