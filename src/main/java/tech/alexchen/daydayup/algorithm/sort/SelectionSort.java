package tech.alexchen.daydayup.algorithm.sort;

import cn.hutool.core.util.RandomUtil;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author alexchen
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] ints = RandomUtil.randomInts(16);
        System.out.println("Before: " + Arrays.toString(ints));
        sort(ints);
        System.out.println("After:  " + Arrays.toString(ints));
    }

    public static void sort(int[] a) {
        int index = 0;
        // 需要选择 n - 1 次
        while (index < a.length - 1) {
            int minIndex = index;
            // 获取未排序序列中的最小值索引
            for (int i = index + 1; i < a.length; i++) {
                if (a[i] < a[minIndex]) {
                    minIndex = i;
                }
            }
            // 将最小值放入已排序序列的下一位
            if (minIndex != index) {
                int temp = a[index];
                a[index] = a[minIndex];
                a[minIndex] = temp;
            }
            index++;
        }
    }
}
