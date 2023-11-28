package tech.alexchen.daydayup.algorithm.sort;

import cn.hutool.core.util.RandomUtil;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author alexchen
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] ints = RandomUtil.randomInts(16);
        System.out.println("Before: " + Arrays.toString(ints));
        sort(ints);
        System.out.println("After:  " + Arrays.toString(ints));
    }

    public static void sort(int[] a) {
        int n = a.length;
        // gap 依次除以 2，直至等于 1
        for (int gap = n >>> 1; gap > 0; gap = gap >>> 1) {
            // 对每个步长使用直接插入排序
            for (int i = 0; i < gap; i++) {
                // 插入排序，从 i+gap 开始，每个元素索引加 gap 位
                for (int j = i + gap; j < n; j += gap) {
                    int temp = a[j];
                    int position = j - gap;
                    while (position >= i && a[position] > temp) {
                        a[position + gap] = a[position];
                        position -= gap;
                    }
                    a[position + gap] = temp;
                }
            }
        }
    }
}
