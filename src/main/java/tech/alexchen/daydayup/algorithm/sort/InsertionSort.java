package tech.alexchen.daydayup.algorithm.sort;

import cn.hutool.core.util.RandomUtil;

import java.util.Arrays;

/**
 * 直接插入排序
 *
 * 直接插入排序(Straight Insertion Sort)的基本思想是:
 * 把n个待排序的元素看成为一个有序表和一个无序表。
 * 开始时有序表中只包含1个元素，无序表中包含有n-1个元素，
 * 排序过程中每次从无序表中取出第一个元素，将它插入到有序表中的适当位置，使之成为新的有序表，重复n-1次可完成排序过程。
 *
 * @author alexchen
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] ints = RandomUtil.randomInts(16);
        System.out.println("Before: " + Arrays.toString(ints));
        sort(ints);
        System.out.println("After:  " + Arrays.toString(ints));
    }

    public static void sort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            //为a[i]在前面的a[0...i-1]有序区间中找一个合适的位置
            int position = i - 1;
            int temp = a[i];
            while (position >= 0 && temp < a[position]) {
                a[position + 1] = a[position];
                position--;
            }
            a[position + 1] = temp;
        }
    }
}
